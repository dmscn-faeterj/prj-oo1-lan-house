package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DAOClientes;
import controller.DAOComputadores;
import modelo.Cliente;

public class JCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtTel;
	private JTextField txtEmail;
	private JTextField txtHoras;
	private JTextField txtPc;
	private JLabel lblHoras;
	private JLabel lblPc;
	private final Action action = new CriarCliente();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastroCliente frame = new JCadastroCliente();
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
	public JCadastroCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroCliente = new JLabel("Cadastro de Cliente");
		lblCadastroCliente.setBounds(179, 30, 300, 15);
		contentPane.add(lblCadastroCliente);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(12, 52, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblTel = new JLabel("Telefone");
		lblTel.setBounds(12, 112, 70, 15);
		contentPane.add(lblTel);
		
		txtNome = new JTextField();
		txtNome.setBounds(12, 72, 114, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(12, 129, 114, 19);
		contentPane.add(txtTel);
		txtTel.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(12, 158, 70, 15);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(12, 185, 114, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtHoras = new JTextField();
		txtHoras.setBounds(324, 72, 114, 19);
		contentPane.add(txtHoras);
		txtHoras.setColumns(10);
		
		txtPc = new JTextField();
		txtPc.setBounds(324, 129, 114, 19);
		contentPane.add(txtPc);
		txtPc.setColumns(10);
		
		lblHoras = new JLabel("Horas");
		lblHoras.setBounds(324, 52, 70, 15);
		contentPane.add(lblHoras);
		
		lblPc = new JLabel("PC");
		lblPc.setBounds(324, 112, 70, 15);
		contentPane.add(lblPc);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(170, 243, 117, 25);
		contentPane.add(btnNewButton);
	}
	private class CriarCliente extends AbstractAction {
		public CriarCliente() {
			putValue(NAME, "Cadastrar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			int qtd = DAOClientes.getQtdClientes() + 1;
			Cliente cliente = new Cliente(qtd, txtNome.getText(), txtTel.getText(), txtEmail.getText(), Integer.parseInt(txtHoras.getText()), DAOComputadores.getComputador(Integer.parseInt(txtPc.getText())));
			DAOClientes.addCliente(cliente);
		}
	}
}
