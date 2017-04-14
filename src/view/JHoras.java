package view;

import java.awt.EventQueue;
import java.util.List;

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
		Object[][] dados = new Object[computadores.size()][3];
		int i = 0;
		
		for(Computador computador : computadores) {
			dados[i][0] = computador.getCod();
			dados[i][1] = computador.getSo();
			dados[i][2] = computador.getHorasLigado();
			i++;
		}
		
		table = new JTable(dados, colunas);
		table.setBounds(46, 32, 361, 158);
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
	}
}
