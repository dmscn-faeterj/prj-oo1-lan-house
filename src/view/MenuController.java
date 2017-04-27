package view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.DAOClientes;
import controller.DAOComputadores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Computador;

public class MenuController implements Initializable {
	// declaraçoes da tabela
	@FXML public TableView<Computador> tablePcs;
	@FXML public TableColumn<Computador, Integer> cod;
	@FXML public TableColumn<Computador, String> so;
	@FXML public TableColumn<Computador, Integer> horasLigado;
	@FXML public TableColumn<Computador, Boolean> ativo;
	
	public ObservableList<Computador> list = FXCollections.observableArrayList(DAOComputadores.getAll());
	
	@FXML public TextField txtNome;
	@FXML public TextField txtTel;
	@FXML public TextField txtEmail;
	@FXML public TextField txtHoras;
	@FXML public TextField txtPc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cod.setCellValueFactory(new PropertyValueFactory<Computador, Integer>("id"));
		so.setCellValueFactory(new PropertyValueFactory<Computador, String>("so"));
		horasLigado.setCellValueFactory(new PropertyValueFactory<Computador, Integer>("horasLigado"));
		ativo.setCellValueFactory(new PropertyValueFactory<Computador, Boolean>("ativo"));
		tablePcs.setItems(list);
	}
	
	public void cadastraCliente(ActionEvent e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Campo Vazio");
		alert.setContentText("Por favor, preencha todos os campos antes de prosseguir.");
		
		if(txtNome.getText() == null || txtNome.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Nome' vazio");
			alert.showAndWait();
			return;
		}
		
		if(txtTel.getText() == null || txtTel.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Tel' vazio");
			alert.showAndWait();
			return;
		}
		
		if(txtEmail.getText() == null || txtEmail.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Email' vazio");
			alert.showAndWait();
			return;
		}
		
		if(txtHoras.getText() == null || txtHoras.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Horas' vazio");
			alert.showAndWait();
			return;
		}
		
		if(txtPc.getText() == null || txtPc.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Maquina' vazio");
			alert.showAndWait();
			return;
		}
		
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação de Cadastro");
		alert.setHeaderText("Confirmar Cadastro");
		alert.setContentText("Deseja continuar?");
		
		ButtonType btnOk = new ButtonType("Ok");
		ButtonType btnCancel = new ButtonType("Cancel");
		
		alert.getButtonTypes().setAll(btnOk, btnCancel);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == btnOk) {
			DAOClientes.addCliente(txtNome.getText(), txtTel.getText(), txtEmail.getText(), Integer.parseInt(txtHoras.getText()), Integer.parseInt(txtPc.getText()));
			Alert success = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso");
			//alert.setHeaderText(null);
			alert.setContentText("Cliente Cadastrado com sucesso!");
		} else {
			return;
		}
		
	}

}
