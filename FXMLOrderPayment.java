package pizzaProject;

import java.io.IOException;
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
import javafx.stage.Stage;

public class FXMLOrderPayment implements Initializable{

	@FXML TextArea OrderInfo;
	@FXML Label Price;
	@FXML Label PaymentInfo;
	@FXML RadioButton CashButton;
	@FXML RadioButton CardButton;
	@FXML RadioButton CheckButton;
	@FXML TextField CardName;
	@FXML TextField CardNumber;
	@FXML TextField CVVNumber;
	@FXML Button ConfirmPayBtn;
	@FXML Label ErrorLabel;
	
	public static String paymentType = "Paying in store";
	private static String sTotal = "";
	private static String sCardHolderName = "";
	private static String sCardNumber = "";
	private static String sCVVNumber = "";
	
	public static boolean isCard()
	{
		if(paymentType.equals("Card") == false)
			return false;
		else
			return true;
	}
	public static String getCardHolderName()
	{
		return sCardHolderName;
	}
	public static String getCardNumber()
	{
		int l = sCardNumber.length();
		String secureCard = "";
		for(int i = 0; i < l - 4; ++i)
			secureCard += "X";
		
		secureCard += sCardNumber.substring(l-4, l);
		System.out.println(sCardNumber);
		return secureCard;
	}
	public static String getCVVNumber()
	{
		return sCVVNumber;
	}
	
	public void pressConfirmBtn() throws IOException
	{
		String errors;
		errors = checkValid();
		
		System.out.println(paymentType + " " + errors);
		boolean valid = false;
		if(paymentType.equals("Card") == false)
		{
			valid = true;
		}
		if(errors.equals("") || FXMLDocumentController.isPickup() || valid)
		{
			System.out.println("ORDER MORE WAS CLICKED");
			Parent home_page_parent = FXMLLoader.load(getClass().getResource("/pizzaProject//OrderOnWay.fxml"));
			Scene home_page_scene = new Scene(home_page_parent);
			Stage stage = (Stage) ((ConfirmPayBtn.getScene()).getWindow());
			stage.hide();
			stage.setScene(home_page_scene);
			stage.setResizable(false);
			stage.setTitle("My Pizza Shop");
			stage.show();
			sCardHolderName = CardName.getText();
			sCardNumber = CardNumber.getText();
			sCVVNumber = CVVNumber.getText();
		}
		else
		{
			ErrorLabel.setText("Please fix: " + errors);
		}
	}
	
	public void disableOptions(boolean disableAll)
	{
		if(disableAll)
		{
			CashButton.setDisable(true);
			CardButton.setDisable(true);
			CheckButton.setDisable(true);	
		}
		
		CardName.setDisable(true);
		CardNumber.setDisable(true);
		CVVNumber.setDisable(true);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String order = FXMLPizzaChoicesController.getOrder();
		String drink = FXMLDrinkController.getDrink();
		double orderPrice = FXMLPizzaChoicesController.getOrderPrice();
		double drinkPrice = FXMLDrinkController.getDrinkPrice();
		double total = orderPrice + drinkPrice;
		sTotal = Double.toString(total);
		String fullOrder = "";
		ErrorLabel.setText("");
		if((drinkPrice > 0) && (orderPrice > 0))
		{
			fullOrder = order + "+\n" + drink;
		}
		else if (drinkPrice == 0 && orderPrice == 0)
			System.out.println("Didnt order anything...Need to remove checkout straight from front order...");
		else if (orderPrice == 0)
			fullOrder = drink;
		else
			fullOrder = order;
		OrderInfo.setText(fullOrder);
		Price.setText("$" + sTotal + "0");
		
		PaymentInfo.setText("Please have your cash ready for the delivery driver. Don't forget to include a tip for excellent service!");
		if(FXMLDocumentController.isPickup())
		{
			PaymentInfo.setText("We should have your order done in no less than 30 minutes for your pickup order. "
					+ "Please bring your preferred method of payment when you come to the store! We accept card, check or cash");
			disableOptions(true);
		}
	}
	
	public void cardSelect()
	{
		if(!FXMLDocumentController.isPickup())
			PaymentInfo.setText("Please input your card info. Our delivery drivers will bring a receipt for signature."
				+ "Don't forget to tip your Delivery Drivers for excellent service!");
		CardName.setDisable(false);
		CardNumber.setDisable(false);
		CVVNumber.setDisable(false);
		paymentType = "Card";
	}
	
	public void checkSelect()
	{
		if(!FXMLDocumentController.isPickup())
		{
			disableOptions(false);
			PaymentInfo.setText("Please have your check ready for the delivery driver. Don't forget to include a tip for excellent service");
		}
		paymentType = "Check";
	}
	
	public void cashSelect()
	{
		if(!FXMLDocumentController.isPickup())
		{
			disableOptions(false);
			PaymentInfo.setText("Please have your cash ready for the delivery driver. Don't forget to include a tip for excellent service!");
		}
		paymentType = "Cash";
	}

	public String checkValid()
	{
		String errors = "";
		if(CardName.getText().length() < 4)
		{
			errors += "CardName ";
		}
		if ((CardNumber.getText().length() < 18) || (CardNumber.getText().length() > 20))
		{
			errors += "CardNumber ";
		}
		if(CVVNumber.getText().length() != 3)
		{
			errors += "CVV Number ";
		}
		return errors;
	}
	public static String getTotal()
	{
		return sTotal;
	}
	

}
