package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.DAOComputadores;
import modelo.Computador;

public class JHoras extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel label;
	private final Action action = new ActOk();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JHoras frame = new JHoras();
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
	public JHoras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String colunas[] = {"Código", "SO", "Horas"};
		List<Computador> computadores = DAOComputadores.getAll();
		Object [][] dados = new Object[computadores.size()+1][3];
		int i = 1;
		
		dados[0] = colunas;
		
		for(Computador computador : computadores) {
			dados[i][0] = computador.getCod();
			dados[i][1] = computador.getSo();
			dados[i][2] = computador.getHorasLigado();
			i++;
		}
		
		table = new JTable(dados, colunas);
		table.setBounds(28, 45, 392, 159);
		contentPane.add(table);
		
		JLabel lblHorasTotais = new JLabel("Horas Totais");
		lblHorasTotais.setBounds(46, 241, 300, 15);
		contentPane.add(lblHorasTotais);
		
		//busca o total de horas para preencher o display
		int horas = DAOComputadores.getTotalHoras();
		
		label = new JLabel();
		label.setBounds(213, 241, 70, 15);
		label.setText(Integer.toString(horas)); //preenche o display com o total de horas
		contentPane.add(label);
		
		JButton btnOk = new JButton();
		btnOk.setAction(action);
		btnOk.setBounds(326, 236, 81, 25);
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
