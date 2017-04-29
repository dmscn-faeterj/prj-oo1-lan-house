package view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.DAOComputadores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import modelo.Computador;

public class ConsultaComputadorController implements Initializable{
	@FXML Label lblCod;
	@FXML Label lblSo;
	@FXML Label lblHoras;
	@FXML Label lblUltCliente;
	@FXML Label lblStatus;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Computador c = new Computador();
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Computador");
		dialog.setHeaderText(null);
		dialog.setContentText("Entre com o código do computador: ");
		
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent()) {
			c = DAOComputadores.getComputador(Integer.parseInt(result.get().toString()));
			lblCod.setText(Integer.toString(c.getCod()));
			lblSo.setText(c.getSo());
			lblHoras.setText(Integer.toString(c.getHorasLigado()));
			lblUltCliente.setText(c.getUltCliente());
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
		ConsultaComputador.getStage().close();
	}
}