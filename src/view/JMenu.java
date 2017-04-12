package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DAOComputadores;
import modelo.Computador;

public class JMenu extends JFrame {

	private JPanel contentPane;
	private final Action action = new ActionAddComputador();
	private final Action action_1 = new ActionCriaCliente();

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setAction(action);
		btnNewButton.setBounds(97, 84, 300, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.setBounds(97, 146, 300, 25);
		contentPane.add(btnNewButton_1);
	}
	
	private class ActionAddComputador extends AbstractAction {
		public ActionAddComputador() {
			putValue(NAME, "Adicionar Computador");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			int qtd = DAOComputadores.getQtdComp();
			Computador comp = new Computador(qtd, "Linux");
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
}
