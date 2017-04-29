package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConsultaComputador extends Application {
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ConsultaComputador.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
}