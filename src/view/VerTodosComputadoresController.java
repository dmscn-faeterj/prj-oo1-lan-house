package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.DAOClientes;
import controller.DAOComputadores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Computador;

public class VerTodosComputadoresController implements Initializable {
	
	@FXML public TableView<Computador> tablePcs;
	@FXML public TableColumn<Computador, Integer> cod;
	@FXML public TableColumn<Computador, String> so;
	@FXML public TableColumn<Computador, Integer> horasLigado;
	@FXML public TableColumn<Computador, String> ultCliente;
	@FXML public TableColumn<Computador, Boolean> ativo;
	
	private ObservableList<Computador> tableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fill();
		
		tablePcs.setItems(tableList);
		cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
		so.setCellValueFactory(new PropertyValueFactory<>("so"));
		horasLigado.setCellValueFactory(new PropertyValueFactory<>("horasLigado"));
		ultCliente.setCellValueFactory(new PropertyValueFactory<>("ultCliente"));
		ativo.setCellValueFactory(new PropertyValueFactory<>("ativo"));
	}
	
	public void fill() {
		tableList.removeAll(tableList);
		for(Computador computador : DAOComputadores.getAll()) {
			tableList.add(computador);
		}
	}
	
	public void addNew(ActionEvent e) {
		DAOComputadores.addComputador("novo");
		fill();
	}
}
