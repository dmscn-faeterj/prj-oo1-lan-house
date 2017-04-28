package view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Computador;


public class MenuController implements Initializable {
	// declaraçoes da tabela
	
	@FXML public TableView<Cliente> tablePcs;
	@FXML public TableColumn<Cliente, Integer> cod;
	@FXML public TableColumn<Cliente, String> nome;
	@FXML public TableColumn<Cliente, String> tel;
	@FXML public TableColumn<Cliente, String> email;
	@FXML public TableColumn<Cliente, Integer> horasCompradas;
	@FXML public TableColumn<Cliente, Integer> horaInicial;
	@FXML public TableColumn<Cliente, Integer> ativo;
	@FXML public TableColumn<Cliente, Integer> computador;
	
	private ObservableList<Cliente> tableList = FXCollections.observableArrayList();
	
	@FXML public TextField txtNome;
	@FXML public TextField txtTel;
	@FXML public TextField txtEmail;
	@FXML public TextField txtHoras;
	@FXML public TextField txtPc;
	@FXML public TextField txtSo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fill();
		
		tablePcs.setItems(tableList);
		cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		horasCompradas.setCellValueFactory(new PropertyValueFactory<>("horasCompradas"));
		horaInicial.setCellValueFactory(new PropertyValueFactory<>("horaInicial"));
		ativo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
		computador.setCellValueFactory(new PropertyValueFactory<>("computador"));
	}
	
	public void fill() {
		tableList.removeAll(tableList);
		for(Cliente cliente : DAOClientes.getAll()) {
			tableList.add(cliente);
		}
	}
	
	public void cadastraCliente(ActionEvent e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Campo Vazio");
		alert.setContentText("Por favor, preencha todos os campos antes de prosseguir.");
		
		if(txtNome.getText() == null || txtNome.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Nome' vazio");
			alert.showAndWait();
			limpaCampos();
			return;
		}
		
		if(txtTel.getText() == null || txtTel.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Tel' vazio");
			alert.showAndWait();
			limpaCampos();
			return;
		}
		
		if(txtEmail.getText() == null || txtEmail.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Email' vazio");
			alert.showAndWait();
			limpaCampos();
			return;
		}
		
		if(txtHoras.getText() == null || txtHoras.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Horas' vazio");
			alert.showAndWait();
			limpaCampos();
			return;
		}
		
		if(txtPc.getText() == null || txtPc.getText().isEmpty()) {
			alert.setHeaderText("Erro. Campo 'Maquina' vazio");
			alert.showAndWait();
			limpaCampos();
			return;
		} else if(DAOComputadores.getComputador(Integer.parseInt(txtPc.getText())) == null) {
			alert.setTitle("Erro");
			alert.setHeaderText("Erro. Este computador não existe");
			alert.setContentText("Por favor, preencha com um valor valido antes de prosseguir.");
			alert.showAndWait();
			limpaCampos();
			return;
		}
		
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Confirmação de Cadastro");
		confirm.setHeaderText("Confirmar Cadastro");
		confirm.setContentText("Deseja continuar?");
		
		ButtonType btnOk = new ButtonType("Ok");
		ButtonType btnCancel = new ButtonType("Cancel");
		
		confirm.getButtonTypes().setAll(btnOk, btnCancel);
		
		Optional<ButtonType> result = confirm.showAndWait();
		
		if(result.get() == btnOk) {
			DAOClientes.addCliente(txtNome.getText(),
					txtTel.getText(), 
					txtEmail.getText(), 
					Integer.parseInt(txtHoras.getText()), 
					Integer.parseInt(txtPc.getText()));
			
			Alert success = new Alert(AlertType.INFORMATION);
			success.setTitle("Sucesso");
			success.setHeaderText(null);
			success.setContentText("Cliente Cadastrado com sucesso!");
			success.showAndWait();
			limpaCampos();
			fill();
		} else {
			return;
		}
		
	}
	
	public void cadastraComputador(ActionEvent e) {
		if(txtSo.getText() == null || txtSo.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campo Vazio");
			alert.setHeaderText("Campo 'Sistema Operacional' vazio");
			alert.setContentText("Por favor, preencha todos os campos antes de prosseguir.");
			alert.showAndWait();
			limpaCampos();
			return;
		} else {
			DAOComputadores.addComputador(txtSo.getText().toString());
			Alert success = new Alert(AlertType.INFORMATION);
			success.setTitle("Sucesso");
			success.setHeaderText(null);
			success.setContentText("Computador Cadastrado com sucesso!");
			success.showAndWait();
			limpaCampos();
			fill();
		}
	}
	
	public void limpaCampos() {
		txtNome.setText(null);
		txtTel.setText(null);
		txtEmail.setText(null);
		txtHoras.setText(null);
		txtPc.setText(null);
		txtSo.setText(null);
	}
	
	public void abrirClientes(ActionEvent e) {
		ShowClientes classe = new ShowClientes();
		Stage stage = null;
		classe.start(stage);
	}
	
	public void consultarCliente(ActionEvent e) {
		ConsultaCliente classe = new ConsultaCliente();
		Stage stage = null;
		classe.start(stage);
	}
	
	public void desligarCliente(ActionEvent e) {
		
	}
	
	public void verTodosComputadores(ActionEvent e) {
		VerTodosComputadores classe = new VerTodosComputadores();
		Stage stage = new Stage();
		classe.start(stage);
	}
	
	public void save(ActionEvent e) {
		SimpleDateFormat df = new SimpleDateFormat("dd MMM");
		Date dia = new Date();
		String strDia = df.format(dia);
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Salvar");
		confirm.setHeaderText("Relatorio " + strDia);
		confirm.setContentText("Deseja continuar e salvar?");
		
		ButtonType btnOk = new ButtonType("Ok");
		ButtonType btnCancel = new ButtonType("Cancel");
		
		confirm.getButtonTypes().setAll(btnOk, btnCancel);
		
		Optional<ButtonType> result = confirm.showAndWait();
		
		if(result.get() == btnOk) {
			DAOClientes.salvar();
			Alert success = new Alert(AlertType.INFORMATION);
			success.setTitle("Sucesso");
			success.setHeaderText(null);
			success.setContentText("Relatorio criado com sucesso!");
			success.showAndWait();
		} else {
			return;
		}
	}

}
