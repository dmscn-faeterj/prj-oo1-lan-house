package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.DAOClientes;
import modelo.Cliente;

public class JAtivos extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAtivos frame = new JAtivos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JAtivos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		String [] colunas = {"Código", "Nome", "Email", "Horas Contratadas", "Início", "Computador"};
		List<Cliente> clientes = DAOClientes.getAll();
		Object [][] dados = null;
		int i = 0;
		
		for(Cliente cliente : clientes) {
			dados [i][0] = cliente.getCod();
			dados [i][1] = cliente.getNome();
			dados [i][2] = cliente.getEmail();
			dados [i][3] = cliente.getHorasCompradas();
			dados [i][4] = cliente.getHoraInicial();
			dados [i][5] = cliente.getComputador().getCod();
			i++;
		}
		
		table = new JTable(dados, colunas);
		table.setBounds(28, 39, 432, 218);
		contentPane.add(table);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(343, 269, 117, 25);
		contentPane.add(btnOk);
	}
}
