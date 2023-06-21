package universidad.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import universidad.logica.Logica;

import javax.swing.JButton;

public class Produccion extends JPanel implements ActionListener {
	private double periodo;
	private double costoOperativo;
	private Object[][] datos;
	private JFrame frame;
	private static JButton process;
	private static JButton reset;
	

	public Produccion(JFrame frame) {
		this.frame = frame;
		setLayout(new GridLayout(4, 1));
		/**
		Integer numeroColumnas = Integer.valueOf(JOptionPane.showInputDialog("Ingrese número de partes:"));
		periodo = Double.valueOf(JOptionPane.showInputDialog("Ingrese periodo:"));
		costoOperativo = Double.valueOf(JOptionPane.showInputDialog("Ingrese costo operativo:"));*/
		Formulario formulario = new Formulario(this);
		add(formulario);
	}

	public void generarTabla(Integer numeroColumnas, Double periodo, Double costoOperativo) {
		this.periodo = periodo;
		this.costoOperativo =  costoOperativo;
		Model model = new Model(numeroColumnas + 1);
		datos = model.datos;
		JTable tabla = new JTable(model) ;

		// La tabla se añade a un ScrollPane para que sea éste el
		// que controle automáticamente en tamaño de la tabla,
		// presentando una barra de desplazamiento cuando sea
		// necesario
		JScrollPane panel = new JScrollPane(tabla);		add(panel);
		this.revalidate();
		
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Tutorial de Java, Swing");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

		Produccion procesado = new Produccion(frame);
		frame.getContentPane().add(procesado, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		process = new JButton("Process");
		process.addActionListener(procesado);
		panel.add(process);
		reset = new JButton("Reset");
		reset.addActionListener(procesado);
		panel.add(reset);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(1000, 550);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(process)) {
			double[][] datosPrimitivos = convertirObjectToDouble();
			Logica logica = new Logica(datosPrimitivos, costoOperativo, periodo);
			double[][] resultado = logica.procesar();
			DefaultTableModel defaultModel = new DefaultTableModel(convertirToObject(resultado),
					new Double[datos[0].length]);
			JTable resultTable = new JTable(defaultModel);
			JScrollPane panel = new JScrollPane(resultTable);
			add(panel);

			Greedy greedy = new Greedy(logica.calcularBarra(), periodo);
			add(greedy);
			this.revalidate();
			System.out.println(logica.calcularBarra());
		} else if (e.getSource().equals(reset)) {
			frame.dispose();
			main(null);
			this.revalidate();
		}

	}

	private double[][] convertirObjectToDouble() {
		double[][] datos2 = new double[datos.length][datos[0].length - 1];
		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j < datos[0].length - 1; j++) {
				datos2[i][j] = (double) datos[i][j + 1];
			}
		}
		return datos2;
	}

	private Object[][] convertirToObject(double[][] resultado) {

		Object[][] datos2 = new Object[resultado.length][resultado[0].length + 1];
		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado[0].length; j++) {
				datos2[i][j + 1] = Double.valueOf(resultado[i][j]);
			}
		}
		datos2[0][0] = "AHORRO POR UNIDAD (AUi) ($/und)";
		datos2[1][0] = "Ahorro total por periodo (si) ($/periodo)";
		datos2[2][0] = "Tiempo total de procesamiento por periodo (pi) (hr/peri)";
		datos2[3][0] = "Ahorro por unidad de tiempo (si/pi) ($/hr)";
		datos2[4][0] = "Orden de asignación";

		return datos2;
	}
}