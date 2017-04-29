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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Computador;


public class MenuController implements Initializable {
	// declaraçoes da tabela
	
	@FXML public TableView<Cliente> tableCli;
	@FXML public TableColumn<Cliente, Integer> codCli;
	@FXML public TableColumn<Cliente, String> nome;
	@FXML public TableColumn<Cliente, String> tel;
	@FXML public TableColumn<Cliente, String> email;
	@FXML public TableColumn<Cliente, Integer> horasCompradas;
	@FXML public TableColumn<Cliente, Integer> horaInicial;
	@FXML public TableColumn<Cliente, Integer> statusCli;
	@FXML public TableColumn<Cliente, Integer> computador;
	
	@FXML public TableView<Computador> tableCom;
	@FXML public TableColumn<Computador, Integer> codCom;
	@FXML public TableColumn<Computador, String> so;
	@FXML public TableColumn<Computador, Integer> horasLigado;
	@FXML public TableColumn<Computador, String> ultCliente;
	@FXML public TableColumn<Computador, Boolean> statusCom;
	
	private ObservableList<Cliente> listCli = FXCollections.observableArrayList();
	private ObservableList<Computador> listCom = FXCollections.observableArrayList();
	
	@FXML public TextField txtNome;
	@FXML public TextField txtTel;
	@FXML public TextField txtEmail;
	@FXML public TextField txtHoras;
	@FXML public TextField txtPc;
	@FXML public TextField txtSo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillCli();
		
		tableCli.setItems(listCli);
		codCli.setCellValueFactory(new PropertyValueFactory<>("cod"));
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		horasCompradas.setCellValueFactory(new PropertyValueFactory<>("horasCompradas"));
		horaInicial.setCellValueFactory(new PropertyValueFactory<>("horaInicial"));
		statusCli.setCellValueFactory(new PropertyValueFactory<>("ativo"));
		computador.setCellValueFactory(new PropertyValueFactory<>("computador"));
		
		fillCom();
		
		tableCom.setItems(listCom);
		codCom.setCellValueFactory(new PropertyValueFactory<>("cod"));
		so.setCellValueFactory(new PropertyValueFactory<>("so"));
		horasLigado.setCellValueFactory(new PropertyValueFactory<>("horasLigado"));
		ultCliente.setCellValueFactory(new PropertyValueFactory<>("ultCliente"));
		statusCom.setCellValueFactory(new PropertyValueFactory<>("ativo"));
	}
	
	public void fillCli() {
		listCli.removeAll(listCli);
		for(Cliente cliente : DAOClientes.getAll()) {
			listCli.add(cliente);
		}
	}
	
	public void fillCom() {
		listCom.removeAll(listCom);
		for(Computador computador : DAOComputadores.getAll()) {
			listCom.add(computador);
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
			fillCli();
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
			success.setContentText("Computador cadastrado com sucesso!");
			success.showAndWait();
			limpaCampos();
			fillCom();
		}
	}
	
	public void consultarCliente(ActionEvent e) {
		ConsultaCliente classe = new ConsultaCliente();
		Stage stage = new Stage();
		classe.start(stage);
	}
	
	public void desligarCliente(ActionEvent e) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Cliente");
		dialog.setHeaderText(null);
		dialog.setContentText("Entre com o código do cliente: ");
		
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent()) {
			DAOClientes.dropCliente(Integer.parseInt(result.get().toString()));
			Alert success = new Alert(AlertType.INFORMATION);
			success.setTitle("Sucesso");
			success.setHeaderText(null);
			success.setContentText("Cliente desligado com sucesso!");
			success.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Codigo Invalido");
			alert.setHeaderText(null);
			alert.setContentText("Codigo não existe! Por favor tente novamente com um código válido");
			alert.showAndWait();
		}
		fillCli();
	}
	
	public void consultarComputador(ActionEvent e) {
		ConsultaComputador classe = new ConsultaComputador();
		Stage stage = new Stage();
		classe.start(stage);
	}
	
	public void desligarComputador(ActionEvent e) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Computador");
		dialog.setHeaderText(null);
		dialog.setContentText("Entre com o código do computador: ");
		
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent()) {
			DAOComputadores.dropComputador(Integer.parseInt(result.get().toString()));
			Alert success = new Alert(AlertType.INFORMATION);
			success.setTitle("Sucesso");
			success.setHeaderText(null);
			success.setContentText("Computador delisgado com sucesso!");
			success.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Codigo Invalido");
			alert.setHeaderText(null);
			alert.setContentText("Codigo não existe! Por favor tente novamente com um código válido");
			alert.showAndWait();
		}
		fillCom();
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
