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
	private final Action addPc = new ActionAddComputador();
	private final Action addCliente = new ActionCriaCliente();
	private final Action showHoras = new HorasComputadas();
	private final Action addHoras = new AddHoras();
	private final Action consultaAtivos = new ConsultaAtivos();
	private final Action desligaCliente = new DesligaCliente();
	private final Action action = new ConsultaCliente();

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
		setBounds(100, 100, 470, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddPc = new JButton();
		btnAddPc.setAction(addPc);
		btnAddPc.setBounds(81, 39, 300, 25);
		contentPane.add(btnAddPc);
		
		JButton btnAddCliente = new JButton();
		btnAddCliente.setAction(addCliente);
		btnAddCliente.setBounds(81, 88, 300, 25);
		contentPane.add(btnAddCliente);
		
		JButton btnHoras = new JButton();
		btnHoras.setAction(showHoras);
		btnHoras.setBounds(81, 135, 300, 25);
		contentPane.add(btnHoras);
		
		JButton btnAddHoras = new JButton();
		btnAddHoras.setAction(addHoras);
		btnAddHoras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddHoras.setBounds(81, 182, 300, 25);
		contentPane.add(btnAddHoras);
		
		JButton ConsultaAtivos = new JButton();
		ConsultaAtivos.setAction(consultaAtivos);
		ConsultaAtivos.setBounds(81, 226, 300, 25);
		contentPane.add(ConsultaAtivos);
		
		JButton btnDesligaCliente = new JButton();
		btnDesligaCliente.setAction(desligaCliente);
		btnDesligaCliente.setBounds(81, 316, 300, 25);
		contentPane.add(btnDesligaCliente);
		
		JButton cosultaCliente = new JButton();
		cosultaCliente.setAction(action);
		cosultaCliente.setBounds(81, 272, 300, 25);
		contentPane.add(cosultaCliente);
	}
	
	private class ActionAddComputador extends AbstractAction {
		public ActionAddComputador() {
			putValue(NAME, "Adicionar Computador");
			putValue(SHORT_DESCRIPTION, "Cria uma nova máquina");
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
			putValue(SHORT_DESCRIPTION, "Cria um novo cliente");
		}
		public void actionPerformed(ActionEvent e) {
			JCadastroCliente frame = new JCadastroCliente();
			dispose();
			frame.setVisible(true);
		}
	}
	
	private class HorasComputadas extends AbstractAction {
		public HorasComputadas() {
			putValue(NAME, "Horas Computadas");
			putValue(SHORT_DESCRIPTION, "Mostra as horas totais utilizadas");
		}
		public void actionPerformed(ActionEvent e) {
			JHoras frame = new JHoras();
			dispose();
			frame.setVisible(true);
		}
	}
	
	private class AddHoras extends AbstractAction {
		public AddHoras() {
			putValue(NAME, "Acrescentar Horas");
			putValue(SHORT_DESCRIPTION, "Acrescenta mais horas para um cliente");
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
			putValue(SHORT_DESCRIPTION, "Consulta os computadores e clientes ativos e se ainda estão no tempo permitido");
		}
		public void actionPerformed(ActionEvent e) {
			JAtivos frame = new JAtivos();
			dispose();
			frame.setVisible(true);
		}
	}
	
	private class ConsultaCliente extends AbstractAction {
		public ConsultaCliente() {
			putValue(NAME, "Consulta Cliente");
			putValue(SHORT_DESCRIPTION, "Recupera código do cliente por nome");
		}
		public void actionPerformed(ActionEvent e) {
			String nome = JOptionPane.showInputDialog("Entre com o nome do cliente: ");
			JOptionPane.showMessageDialog(getParent(), DAOClientes.getCodByName(nome));
		}
	}
	
	private class DesligaCliente extends AbstractAction {
		public DesligaCliente() {
			putValue(NAME, "Desliga Cliente");
			putValue(SHORT_DESCRIPTION, "Desliga um cliente que terminou o tempo");
		}
		public void actionPerformed(ActionEvent e) {
			int cod = Integer.parseInt(JOptionPane.showInputDialog("Entre com o código do cliente: "));
			DAOClientes.off(cod);
		}
	}
}
