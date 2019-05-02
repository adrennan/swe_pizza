package pizzaProject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLDrinkController implements Initializable
{
	@FXML ImageView MoreFood;
	@FXML ImageView Checkout;	
	@FXML RadioButton Small;
	@FXML RadioButton Medium;
	@FXML RadioButton Large;
	@FXML RadioButton Coke;
	@FXML RadioButton Sprite;
	@FXML RadioButton mDew;
	@FXML RadioButton dPepper;
	private static String Drink = " ";
	private static double DrinkPrice = 0;
	
	public static String getDrink()
	{
		return Drink;
	}
	
	public static double getDrinkPrice()
	{
		return DrinkPrice;
	}
	private void checkSelections()
	{
		if(Small.isSelected())
		{
			Drink += "Small ";
			DrinkPrice += 1.50;
		}
		else if(Medium.isSelected())
		{
			Drink += "Medium ";
			DrinkPrice += 2.00;
		}
		else if(Large.isSelected())
		{
			Drink += "Large ";
			DrinkPrice += 2.50;
		}
		
		if(Coke.isSelected())
			Drink += "Coke";
		if(Sprite.isSelected())
			Drink += "Sprite";
		if(mDew.isSelected())
			Drink += "Mt. Dew";
		if(dPepper.isSelected())
			Drink += "Dr. Pepper";
	}
	
	public void checkoutClick() throws IOException
	{
		checkSelections();
		System.out.println("CHECKOUT WAS CLICKED");
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
		System.out.println("ORDER PIZZA WAS CLICKED");
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/pizzaProject//PizzaChoices.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage stage = (Stage) ((Checkout.getScene()).getWindow());
		stage.hide();
		stage.setScene(home_page_scene);
		stage.setResizable(false);
	    stage.setTitle("My Pizza Shop");
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Drinks loaded");		
	}

}
