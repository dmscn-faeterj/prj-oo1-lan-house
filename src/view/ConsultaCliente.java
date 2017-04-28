package view;

import java.util.Optional;

import controller.DAOClientes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import modelo.Cliente;

public class ConsultaCliente extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ConsultaClientes.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Cliente");
			dialog.setHeaderText(null);
			dialog.setContentText("Entre com o código do cliente: ");
			
			Optional<String> result = dialog.showAndWait();
			
			if(result.isPresent()) {
				Cliente cliente = DAOClientes.getCliente(Integer.parseInt(result.toString()));
				ConsultaClienteController controller = new ConsultaClienteController();
				controller.setLabels(cliente);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Codigo Invalido");
				alert.setHeaderText(null);
				alert.setContentText("Codigo não existe! Por favor tente novamente com um código válido");
				alert.showAndWait();
			}
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
