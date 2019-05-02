package pizzaProject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLPizzaChoicesController implements Initializable
{
	@FXML ImageView MoreFood;
	@FXML ImageView Checkout;	
	@FXML CheckBox XCheese;
	@FXML CheckBox Pepperoni;
	@FXML CheckBox Sausage;
	@FXML CheckBox Mushrooms;
	@FXML CheckBox Onions;
	@FXML CheckBox BlackOlives;
	@FXML RadioButton Small;
	@FXML RadioButton Medium;
	@FXML RadioButton Large;
	@FXML RadioButton Normal;
	@FXML RadioButton Thin;
	@FXML RadioButton Stuffed;
	private static String order = "";
	private static double orderPrice = 0;
	
	public static String getOrder()
	{
		return order;
	}
	
	public static double getOrderPrice()
	{
		return orderPrice;
	}

	private void checkSelections()
	{
		if(Small.isSelected())			//Pizza Size
		{
			order += "Small ";
			orderPrice += 10.00;
		}
		else if(Medium.isSelected())
		{
			order += "Medium ";
			orderPrice += 13.50;
		}
		else if(Large.isSelected())
		{
			order += "Large ";
			orderPrice += 15.00;
		}
		
		if(Normal.isSelected())
		{
			order += "Pan Pizza";
			
		}
		else if(Thin.isSelected())
		{
			order += "Thin Pizza";
			orderPrice += 2.00;
		}
		else if(Stuffed.isSelected())
		{
			order += "Stuffed Pizza";
			orderPrice += 3.00;
		}
			
		
		order += " with :\n\tCheese\n\tHouse Tomato Sauce\n";
		
		if(XCheese.isSelected())
		{
			order += "\tExtra Cheese\n";
			orderPrice += .50;
		}
		if(Pepperoni.isSelected())
		{
			order += "\tPepperoni\n";
			orderPrice += .50;
		}
		if(Sausage.isSelected())
		{
			order += "\tSausage\n";
			orderPrice += .50;
		}
		if(Onions.isSelected())
		{
			order += "\tOnions\n";
			orderPrice += .50;
		}
		
		if(Mushrooms.isSelected())
		{
			order += "\tMushrooms\n";
			orderPrice += .50;
		}
		if(BlackOlives.isSelected())
		{
			order += "\tBlack Olives\n";
			orderPrice += .50;
		}
	}
	
	public void checkoutClick() throws IOException
	{
	
		checkSelections();
		
		System.out.println(order);
		//System.out.println("CHECKOUT WAS CLICKED");
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/pizzaProject//InformationPage.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage stage = (Stage) ((Checkout.getScene()).getWindow());
		stage.hide();
		stage.setScene(home_page_scene);
		stage.setResizable(false);
		stage.setTitle("My Pizza Shop");
		stage.show();
	}
	
	public void orderMore() throws IOException
	{
		
		checkSelections();
		System.out.println("ORDER DRINK WAS CLICKED");
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/pizzaProject//DrinkOrder.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage stage = (Stage) ((Checkout.getScene()).getWindow());
		stage.hide();
		stage.setScene(home_page_scene);
		stage.setResizable(false);
		stage.setTitle("My Pizza Shop");
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		System.out.println("Pizza Choices loaded");		
	}

}
