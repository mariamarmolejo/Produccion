package universidad.interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Formulario extends JPanel {

	/**
	 * 
	 */
	private Double periodo;
	private Integer numeroPartes;
	private Double costoOperativo;
	private static final long serialVersionUID = 1L;
	private JTextField partesText;
	private JTextField periodoText;
	private JTextField costoText;
	private Produccion produccion;

	public Formulario(Produccion produccion) {
		setBackground(new Color(64, 0, 128));
		setForeground(new Color(0, 0, 0));
		Font fuente = new Font("Calibri", 3, 20);
		this.produccion = produccion;
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Produccion");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(fuente);
		lblNewLabel.setBounds(148, 11, 103, 34);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("#Partes:");
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(22, 46, 54, 14);
		add(lblNewLabel_1);

		partesText = new JTextField();
		partesText.setBounds(74, 43, 40, 20);
		add(partesText);
		partesText.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Periodo:");
		lblNewLabel_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(117, 46, 70, 14);
		add(lblNewLabel_1_1);

		periodoText = new JTextField();
		periodoText.setColumns(10);
		periodoText.setBounds(169, 43, 46, 20);
		add(periodoText);

		costoText = new JTextField();
		costoText.setColumns(10);
		costoText.setBounds(316, 43, 40, 20);
		add(costoText);

		JLabel lblNewLabel_1_1_1 = new JLabel("Costo operativo:");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setBounds(225, 46, 103, 14);
		add(lblNewLabel_1_1_1);

		JButton btnNewButton = new JButton("Generar");
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNumeric(periodoText.getText()) && isNumeric(costoText.getText())
						&& isNumeric(partesText.getText())) {
					periodo = Double.parseDouble(periodoText.getText());
					costoOperativo = Double.parseDouble(costoText.getText());
					numeroPartes = Integer.parseInt(partesText.getText());
					produccion.generarTabla(numeroPartes, periodo, costoOperativo);
					
				}
			}

			public boolean isNumeric(String strNum) {
				if (strNum == null) {
					return false;
				}
				try {
					double d = Double.parseDouble(strNum);
				} catch (NumberFormatException nfe) {
					return false;
				}
				return true;
			}
		});
		btnNewButton.setBounds(22, 71, 334, 23);
		add(btnNewButton);

	}

}
