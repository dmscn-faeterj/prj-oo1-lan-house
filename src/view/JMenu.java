package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DAOClientes;
import controller.DAOComputadores;
import modelo.Computador;
import java.awt.event.ActionListener;

public class JMenu extends JFrame {

	private JPanel contentPane;
	private final Action action = new ActionAddComputador();
	private final Action action_1 = new ActionCriaCliente();
	private final Action action_2 = new HorasComputadas();
	private final Action action_3 = new AddHoras();
	private final Action action_4 = new ConsultaAtivos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenu frame = new JMenu();
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
	public JMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddPc = new JButton();
		btnAddPc.setAction(action);
		btnAddPc.setBounds(81, 39, 300, 25);
		contentPane.add(btnAddPc);
		
		JButton btnAddCliente = new JButton();
		btnAddCliente.setAction(action_1);
		btnAddCliente.setBounds(81, 88, 300, 25);
		contentPane.add(btnAddCliente);
		
		JButton btnHoras = new JButton();
		btnHoras.setAction(action_2);
		btnHoras.setBounds(81, 135, 300, 25);
		contentPane.add(btnHoras);
		
		JButton btnAddHoras = new JButton();
		btnAddHoras.setAction(action_3);
		btnAddHoras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddHoras.setBounds(81, 182, 300, 25);
		contentPane.add(btnAddHoras);
		
		JButton ConsultaAtivos = new JButton();
		ConsultaAtivos.setAction(action_4);
		ConsultaAtivos.setBounds(81, 226, 300, 25);
		contentPane.add(ConsultaAtivos);
	}
	
	private class ActionAddComputador extends AbstractAction {
		public ActionAddComputador() {
			putValue(NAME, "Adicionar Computador");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			int qtd = DAOComputadores.getQtdComp();
			
			String so = JOptionPane.showInputDialog("Sistema Operacional: ");
			
			Computador comp = new Computador(qtd, so);
			DAOComputadores.addComputador(comp);
		}
	}
	private class ActionCriaCliente extends AbstractAction {
		public ActionCriaCliente() {
			putValue(NAME, "Cadastrar Cliente");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JCadastroCliente frame = new JCadastroCliente();
			frame.setVisible(true);
		}
	}
	private class HorasComputadas extends AbstractAction {
		public HorasComputadas() {
			putValue(NAME, "Horas Computadas");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JHoras frame = new JHoras();
			frame.setVisible(true);
		}
	}
	private class AddHoras extends AbstractAction {
		public AddHoras() {
			putValue(NAME, "Acrescentar Horas");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			int codCliente = Integer.parseInt(JOptionPane.showInputDialog("Entre com o codigo do cliente: "));
			int horas = Integer.parseInt(JOptionPane.showInputDialog("Entre com o numero de horas: "));
			
			DAOClientes.addHoras(codCliente, horas);
		}
	}
	private class ConsultaAtivos extends AbstractAction {
		public ConsultaAtivos() {
			putValue(NAME, "Consulta Ativos");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
