package pizzaProject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable{
	
	@FXML TextField PhoneNumber;
	@FXML TextField Name;
	@FXML TextField Address;
	@FXML TextField City;
	@FXML TextField ZipCode;
	@FXML TextField NearestIntersection;
	@FXML TextArea SpecialInstructions;
	@FXML Button OKButton;
	@FXML ImageView MoreFood;
	@FXML ImageView PlaceOrder;
	@FXML Label ErrorLabel;
	@FXML RadioButton Pickup;
	@FXML RadioButton Delivery;
	private static String DeliveryDetails = "";
	private static boolean isPickup = false;
	private static String sName = "";
	private static String sPhone = "";
	private static String sAddress = "";
	private static String sCityZip = "";
	private static String sNearestIntersection = "";
	private static String sSpecialInstructions = "";
	private static boolean prefilled = false;
	
	public static boolean isPickup()
	{
		return isPickup;
	}
	
	public static String getDeliveryDetails()
	{
		return DeliveryDetails;
	}
	
	public static String getName()
	{
		return sName;
	}
	
	public static String getPhone()
	{
		return sPhone;
	}
	
	public static String getAddress()
	{
		return sAddress;
	}
	
	public static String getCityZip()
	{
		return sCityZip;
	}
	
	public static String getNeartestIntersection()
	{
		return sNearestIntersection;
	}
	
	public static String getSpecialInstructions()
	{
		return sSpecialInstructions;
	}
	
	public void PressOKButton() throws IOException
	{
		
		/*Check if phone number is in log
		 * yes - prefill the customer info from log, and remove the disabled property
		 * no - remove disabled property and store new info into log after place order is pressed
		 */
		if ((PhoneNumber.getText().length() != 10))
		{
			ErrorLabel.setText("Phone number is invalid. Please enter a 10 digit number without spaces or dashes");
		}
		else
		{
			ErrorLabel.setText("");
			PhoneNumber.setDisable(true);
			Name.setDisable(false);
			Address.setDisable(false);
			City.setDisable(false);
			ZipCode.setDisable(false);
			NearestIntersection.setDisable(false);
			SpecialInstructions.setDisable(false);
			PlaceOrder.setDisable(false);
			prefillCustomerInfo();
		}
		
	}
	
	public void OrderMore() throws IOException
	{
		System.out.println("ORDER MORE WAS CLICKED");
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/pizzaProject//FrontOrder.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage stage = (Stage) ((MoreFood.getScene()).getWindow());
		stage.hide();
		stage.setScene(home_page_scene);
		stage.setResizable(false);
		stage.setTitle("My Pizza Shop");		
		stage.show();
	}
	
	public void PlaceOrder() throws IOException
	{

		/*Check the name/address/city/zip for validity and near our location
		 * if valid and this is a new phone number store to IO and move to next screen
		 * if invalid, store what is invalid in the array and print it in the error label
		 */
		String Errors = "";
		boolean hasErrors = false;
		if(Name.getText().length() < 5)
		{
			Errors += "Name ";
			hasErrors = true;
		}
		if(Address.getText().length() < 8)
		{
			Errors += "Address ";
			hasErrors = true;
		}
		if(City.getText().length() < 3)
		{
			Errors += "City ";
			hasErrors = true;
		}
		if(ZipCode.getText().length() != 5)
		{
			Errors += "ZipCode ";
			hasErrors = true;
		}
		
		
		if(hasErrors)
		{
			System.out.println("Had these errors : " + Errors);
			ErrorLabel.setText("Please fix the errors with : " + Errors);
		}
		else
		{
			DeliveryDetails += "Customer Name : " + Name.getText() + "\n"
					+ "Phone Number : " + PhoneNumber.getText() + "\n"
					+ "Address : " + Address.getText() + "\n\t"
					+ City.getText() + " " + ZipCode.getText() + "\n"
					+ "Nearest Intersection : " + NearestIntersection.getText() + "\n"
					+ "Special Instructions : " + SpecialInstructions.getText() + "\n";
			isPickup = Pickup.isSelected();
			
			if(!prefilled)
			{
				storeToFile();
			}
			sName = Name.getText();
			sPhone = PhoneNumber.getText();
			sAddress = Address.getText();
			sCityZip = City.getText() + ", " + ZipCode.getText();
			sNearestIntersection = NearestIntersection.getText();
			sSpecialInstructions = SpecialInstructions.getText();
					
			System.out.println("PLACE ORDER WAS CLICKED WITH VALID CREDENTIALS -> LOAD PAYMENT PAGE");
			Parent home_page_parent = FXMLLoader.load(getClass().getResource("/pizzaProject//OrderPayment.fxml"));
			Scene home_page_scene = new Scene(home_page_parent);
			Stage stage = (Stage) ((PlaceOrder.getScene()).getWindow());
			stage.hide();
			stage.setScene(home_page_scene);
			stage.setResizable(false);
			stage.setTitle("My Pizza Shop");			
			stage.show();
		}
		
	}
	private void storeToFile() throws IOException
	{
		File f = new File("src/pizzaProject/docs/CustomerInfo.txt");
		System.out.println("We got a file : " + f);
		
		System.out.println(f.exists());
		System.out.println("Is directory? : " + f.isDirectory());
		
		
		if (f.exists())
			appendDocument(f);
		else
			System.out.println("Error file not found");
	}
	
	private void appendDocument(File f)
	{
		try
		(
			FileWriter fileWriter = new FileWriter(f, true);
			BufferedWriter buffer = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(buffer);
		)
		{
			
			printWriter.println(PhoneNumber.getText());
			printWriter.println(Name.getText());
			printWriter.println(Address.getText());
			printWriter.println(City.getText());
			printWriter.println(ZipCode.getText());
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			System.out.println("Error exception found in AppendDocument method");
		}
		System.out.println("Completed appendDocument successfully");
	}
	
	@SuppressWarnings("unused")
	public void prefillCustomerInfo()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("src/pizzaProject/docs/CustomerInfo.txt")));
			
			while(true)
			{
				String current = reader.readLine();
				if(current.equals(PhoneNumber.getText()))
				{
					System.out.println("Found phone number");
					Name.setText(reader.readLine());
					Address.setText(reader.readLine());
					City.setText(reader.readLine());
					ZipCode.setText(reader.readLine());
					prefilled = true;
					break;
				}
				
				if(current == null)
				{
					prefilled = false;
					break;
				}
			}
			reader.close();	
		}
		catch(Exception ex)
		{
		
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ErrorLabel.setText("");
		Name.setDisable(true);
		Address.setDisable(true);
		City.setDisable(true);
		ZipCode.setDisable(true);
		NearestIntersection.setDisable(true);
		SpecialInstructions.setDisable(true);
		PlaceOrder.setDisable(true);
	}

}
