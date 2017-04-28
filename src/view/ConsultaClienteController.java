package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelo.Cliente;

public class ConsultaClienteController {
	@FXML Label lblCod;
	@FXML Label lblNome;
	@FXML Label lblTelefone;
	@FXML Label lblEmail;
	@FXML Label lblHoras;
	@FXML Label lblHoraInicial;
	@FXML Label lblStatus;
	@FXML Label lblComputador;
	
	public void setLabels(Cliente c) {
		lblCod.setText(Integer.toString(c.getCod()));
		lblNome.setText(c.getNome());
		lblTelefone.setText(c.getTel());
		lblEmail.setText(c.getEmail());
		lblHoras.setText(Integer.toString(c.getHorasCompradas()));
		lblHoraInicial.setText(c.getHoraInicial());
		lblStatus.setText(Boolean.toString(c.getAtivo()));
	}

}
