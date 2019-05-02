package pizzaProject;

import javafx.fxml.Initializable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FXMLOrderOnWay implements Initializable {
	@FXML Label DeliveryAddress;
	@FXML Button PrintReceiptButton;
	@FXML Label lOrderText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(FXMLDocumentController.isPickup())
		{
			lOrderText.setVisible(false);
			DeliveryAddress.setText("Our stores location is at \n1100 South Marietta PKWY, Marietta, GA\n"
					+ "We will have your order ready in 30 minutes or your money back!");
		}
		else
		{
			DeliveryAddress.setText(FXMLDocumentController.getDeliveryDetails());	
		}
		
	}
	
	public void printReceipt()
	{
		try 
		{
			File f = storeToFile();
			if (Desktop.isDesktopSupported()) 
				Desktop.getDesktop().open(f);
		}
		catch (IOException ioe) 
		{			
			ioe.printStackTrace();
		}
	}
	
	private File storeToFile() throws IOException
	{
		int i = 0;
		File f = new File("src/pizzaProject/docs/Receipts/" + FXMLDocumentController.getName() + ".txt");
		while(f.exists())
		{
			f = new File("src/pizzaProject/docs/Receipts/" + FXMLDocumentController.getName() + i + ".txt");
			++i;
		}
		System.out.println("We got a file : " + f);
		
		System.out.println(f.exists());
		System.out.println("Is directory? : " + f.isDirectory());
		createDocument(f);
		return f;
	}
	
	private void createDocument(File f)
	{
		try
		(
			FileWriter fileWriter = new FileWriter(f, false);
			BufferedWriter buffer = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(buffer);
		)
		{
			printWriter.println("		  The Pizza Shop");
			printWriter.println("		Love at First Slice");
			printWriter.println("---------------------------------------------------");
			printWriter.println("Todays Order : ");
			printWriter.println(FXMLPizzaChoicesController.getOrder());
			printWriter.println(FXMLDrinkController.getDrink());

			if(FXMLDocumentController.isPickup())
			{
				printWriter.println("Pickup Order");
			}
			else
				printWriter.println("Delivery Order");
			
			printWriter.println("Customer Name : " + FXMLDocumentController.getName());
			printWriter.println("Phone Number  : " + FXMLDocumentController.getPhone());
			printWriter.println("Address       : " + FXMLDocumentController.getAddress());
			printWriter.println("                " + FXMLDocumentController.getCityZip());
			printWriter.println();			
			printWriter.println("Nearest Intersection : " + FXMLDocumentController.getNeartestIntersection());
			printWriter.println("Special Instructions : " + FXMLDocumentController.getSpecialInstructions());
			
			printWriter.println("Payment Method : " + FXMLOrderPayment.paymentType);
			if(FXMLOrderPayment.isCard())
			{
				printWriter.println(FXMLOrderPayment.getCardHolderName());
				printWriter.println(FXMLOrderPayment.getCardNumber());			
			}
			printWriter.println("---------------------------------------------------");
			printWriter.println("Order: $" + FXMLOrderPayment.getTotal() + "0");
			printWriter.println("Tip  : ____________________________________________");
			printWriter.println("Total: ____________________________________________");
			printWriter.println();
			printWriter.println("Sign Here : _______________________________________");
			printWriter.println("		   Thank you for your business!");
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			System.out.println("Error exception found in receipt createDocument method");
		}
		System.out.println("Completed Receipt createDocument successfully");
	}


}
