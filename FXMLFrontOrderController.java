package pizzaProject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLFrontOrderController implements Initializable{
	@FXML ImageView Pizza;
	@FXML ImageView Drinks;
	@FXML ImageView Checkout;
	
	public void checkoutClick() throws IOException
	{
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
	
	public void pizzaClick() throws IOException
	{
		System.out.println("PIZZA WAS CLICKED");
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("/pizzaProject//PizzaChoices.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage stage = (Stage) ((Checkout.getScene()).getWindow());
		stage.hide();
		stage.setScene(home_page_scene);
		stage.setResizable(false);
		stage.setTitle("My Pizza Shop");
		stage.show();
	}
	
	public void drinkClick() throws IOException
	{
		System.out.println("DRINK WAS CLICKED");
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
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Front order loaded");
		Checkout.setDisable(true);
		Checkout.setVisible(false);
		//FXMLDocumentController test = new FXMLDocumentController(); 
	}

}
