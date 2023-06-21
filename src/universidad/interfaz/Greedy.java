package universidad.interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Greedy extends JPanel {

	/**
	 * Create the panel.
	 */
	public Greedy(List<String> greedyList, Double periodo) {
		setBackground(new Color(198, 253, 253));
		setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(10, 31, 709, 28);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		for (String resultado : greedyList) {
			String[] table = resultado.split(",");
			JButton btnNewButton = new JButton(table[0] + " -> " + table[1] + " hr");

			btnNewButton.setBackground(Color.BLACK);
			btnNewButton.setForeground(Color.WHITE);
			panel.add(btnNewButton);
			
		}
		
		JLabel LabelPeriodo = new JLabel(periodo+"");
		LabelPeriodo.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		LabelPeriodo.setBounds(295, 9, 108, 25);
		add(LabelPeriodo);
		
		JLabel lblNewLabel = new JLabel("Periodo:");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel.setBounds(209, 11, 86, 20);
		add(lblNewLabel);
		
		Panel panel2 = new Panel();
		panel2.setBounds(10, 65, 709, 28);
		add(panel2);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		double total = 0.0;
		for (String resultado : greedyList) {
			String[] table = resultado.split(",");
			total+= Double.valueOf(table[2]);
			JLabel lblLabel = new JLabel(table[2] + " $/periodo");
			panel2.add(lblLabel);
			
		}
		
		Label label = new Label("Total:");
		label.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		label.setBounds(209, 99, 52, 22);
		add(label);
		
		Label label_total = new Label(total+"");
		label_total.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		label_total.setBounds(295, 99, 165, 22);
		add(label_total);

		

	}
}
