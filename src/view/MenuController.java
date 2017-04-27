package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.DAOComputadores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
		Cliente cliente = new Cliente()
	}

}
