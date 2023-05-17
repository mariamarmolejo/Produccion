package universidad.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import universidad.logica.Logica;

import javax.swing.JButton;

public class Java1416 extends JPanel implements ActionListener {
	private double periodo;
	private double costoOperativo;
	private Double[][] datos;
	private JTable tabla;

	public Java1416() {
		setLayout(new BorderLayout());
		Integer numeroColumnas = Integer.valueOf(JOptionPane.showInputDialog("Ingrese numero de columnas:"));
		periodo = Double.valueOf(JOptionPane.showInputDialog("Ingrese periodo:"));
		costoOperativo = Double.valueOf(JOptionPane.showInputDialog("Ingrese costoOperativo:"));

		Model model = new Model(numeroColumnas);
		datos = model.datos;
		/*
		 * datos[0][0] = "Precio de compra por unidad (PCUi) ($/und)"; datos[1][0] =
		 * "Costo de material por unidad (CMUi) ($/und)"; datos[2][0] =
		 * "Demanda por unidad (Di) (und/periodo)"; datos[3][0] =
		 * "Tiempo unitario de procesamienro (TPUi) (hrs/unidad)";
		 */



		tabla = new JTable(model) ;

		// La tabla se añade a un ScrollPane para que sea éste el
		// que controle automáticamente en tamaño de la tabla,
		// presentando una barra de desplazamiento cuando sea
		// necesario
		JScrollPane panel = new JScrollPane(tabla);
		add(panel, BorderLayout.CENTER);
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Tutorial de Java, Swing");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

		Java1416 procesado = new Java1416();
		frame.getContentPane().add(procesado, BorderLayout.CENTER);
		JButton comp = new JButton("Process");
		comp.addActionListener(procesado);
		frame.getContentPane().add(comp, BorderLayout.SOUTH);

		frame.setSize(800, 200);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		double[][] datos2 = convertir();
		Logica logica = new Logica(datos2, costoOperativo, periodo);
		double[][] resultado = logica.procesar();
		for (int x = 0; x < resultado.length; x++) {
			System.out.print("|");
			for (int y = 0; y < resultado[x].length; y++) {
				System.out.print(resultado[x][y]);
				if (y != resultado[x].length - 1)
					System.out.print("\t");
			}
			System.out.println("|");
		}

	}

	private double[][] convertir() {
		double[][] datos2 = new double[datos.length][datos[0].length];
		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j < datos[0].length; j++) {
				datos2[i][j] = datos[i][j];
			}
		}
		return datos2;
	}
}