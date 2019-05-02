package pizzaProject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPizzaApp extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
	      String fxmlResource = "/pizzaProject//FrontOrder.fxml";
	      Parent panel;
	      FXMLLoader loader = new FXMLLoader();
	      panel = FXMLLoader.load(getClass().getResource(fxmlResource));
	      loader.setController(new FXMLDocumentController());
	      
	      Scene scene = new Scene(panel);
	      Stage stage = new Stage();
	      stage.setScene(scene);
	      stage.setResizable(false);	
	      stage.setTitle("My Pizza Shop");
	      stage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
