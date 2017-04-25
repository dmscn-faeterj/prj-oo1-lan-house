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
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class JAtivos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private final Action action = new ActOk();

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
		setBounds(100, 100, 532, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		String [] colunas = {"Código", "Nome", "Email", "Horas Contratadas", "Início", "Computador", "Ativo"};
		List<Cliente> clientes = DAOClientes.getAll();
		Object [][] dados = new Object[clientes.size()+1][7];
		int i = 1;
		
		dados[0] = colunas;
		
		for(Cliente cliente : clientes) {
			dados [i][0] = cliente.getCod();
			dados [i][1] = cliente.getNome();
			dados [i][2] = cliente.getEmail();
			dados [i][3] = cliente.getHorasCompradas();
			dados [i][4] = cliente.getHoraInicial();
			dados [i][5] = cliente.getComputador().getCod();
			
			if(cliente.getAtivo() == true) {
				dados [i][6] = "On";
			} else {
				dados [i][6] = "Off";
			}
			
			i++;
		}
		
		table = new JTable(dados, colunas);
		table.setBounds(28, 39, 477, 227);
		contentPane.add(table);
		
		JButton btnOk = new JButton();
		btnOk.setAction(action);
		btnOk.setBounds(379, 269, 81, 25);
		contentPane.add(btnOk);
	}
	
	private class ActOk extends AbstractAction {
		public ActOk() {
			putValue(NAME, "Ok");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			JMenu frame = new JMenu();
			frame.setVisible(true);
		}
	}
}
