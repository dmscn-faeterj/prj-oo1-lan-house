package view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.DAOClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import modelo.Cliente;

public class ConsultaClienteController implements Initializable {
	@FXML Label lblCod;
	@FXML Label lblNome;
	@FXML Label lblTelefone;
	@FXML Label lblEmail;
	@FXML Label lblHoras;
	@FXML Label lblHoraInicial;
	@FXML Label lblStatus;
	@FXML Label lblComputador;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Cliente c = new Cliente();
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Cliente");
		dialog.setHeaderText(null);
		dialog.setContentText("Entre com o código do cliente: ");
		
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent()) {
			c = DAOClientes.getCliente(Integer.parseInt(result.get().toString()));
			lblCod.setText(Integer.toString(c.getCod()));
			lblNome.setText(c.getNome());
			lblTelefone.setText(c.getTel());
			lblEmail.setText(c.getEmail());
			lblHoras.setText(Integer.toString(c.getHorasCompradas()));
			lblHoraInicial.setText(c.getHoraInicial());
			lblStatus.setText(Boolean.toString(c.getAtivo()));
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Codigo Invalido");
			alert.setHeaderText(null);
			alert.setContentText("Codigo não existe! Por favor tente novamente com um código válido");
			alert.showAndWait();
		}
	}
	
	public void voltar(ActionEvent e) {
		ConsultaCliente.getStage().close();
	}
}
