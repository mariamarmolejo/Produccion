package universidad.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Logica {

	double[][] datos;
	double[][] resultados;
	double costoOperativo;
	double periodo;

	public Logica(double[][] datos, double costoOperativo, double periodo) {
		super();
		this.datos = datos;
		this.costoOperativo = costoOperativo;
		this.periodo = periodo;
	}

	public double[][] procesar() {
		resultados = new double[5][datos[0].length];
		calcularAhorroPorUnidad();
		calcularAhorroTotalPorPeriodo();
		calcularTiempoTotalProcesamientoPeriodo();
		calcularAhorroUnidadTiempo();
		ordenarLista();

		return resultados;
	}

	// OPERACIÓN 1
	// Ahorro por Unidad (AU1) = Precio de compra por unidad - ( Costo de material
	// por unidad + ( Costo Operativo * Tiempo unitario de procesamiento))
	double[] calcularAhorroPorUnidad() {
		double[] au1 = new double[datos[0].length];
		int filai = 0;

		for (int columnaj = 0; columnaj < datos[0].length; columnaj++) {
			au1[columnaj] = datos[filai][columnaj]
					- (datos[filai + 1][columnaj] + (costoOperativo * datos[filai + 3][columnaj]));
		}

		resultados[0] = au1;
		return au1;
	}

	// OPERACIÓN 2
	// Ahorro total por periodo (S1) = Ahorro por unidad * Demanda por unidad
	double[] calcularAhorroTotalPorPeriodo() {
		double[] s1 = new double[datos[0].length];
		int filai = 0;

		for (int columnaj = 0; columnaj < datos[0].length; columnaj++) {
			double temp = resultados[0][columnaj] * datos[filai + 2][columnaj];

			if (temp < 0) {
				s1[columnaj] = 0;
			} else {
				s1[columnaj] = temp;
			}
		}

		resultados[1] = s1;
		return s1;
	}

	// OPERACIÓN 3
	// Tiempo Total de Procesamiento por periodo (p1) = Tiempo unitario de
	// procesamiento * Demanda por unidad
	double[] calcularTiempoTotalProcesamientoPeriodo() {
		double[] p1 = new double[datos[0].length];
		int filai = 3;

		for (int columnaj = 0; columnaj < datos[0].length; columnaj++) {
			double temp = datos[filai][columnaj] * datos[filai - 1][columnaj];
			if (temp < 0 || resultados[1][columnaj] <= 0) {
				p1[columnaj] = 0;
			} else {
				p1[columnaj] = temp;
			}
		}

		resultados[2] = p1;
		return p1;
	}

	// OPERACIÓN 4
	// Ahorro por unidad de tiempo (p1) = Ahorro total por periodo (S1) / Tiempo
	// Total de Procesamiento por periodo (p1)
	double[] calcularAhorroUnidadTiempo() {
		double[] at1 = new double[datos[0].length];

		for (int columnaj = 0; columnaj < datos[0].length; columnaj++) {
			double temp1 = resultados[1][columnaj];
			double temp2 = resultados[2][columnaj];
			if (temp1 <= 0 || temp2 <= 0 || resultados[2][columnaj] <= 0) {
				at1[columnaj] = 0;
			} else {
				at1[columnaj] = resultados[1][columnaj] / resultados[2][columnaj];
			}
		}

		resultados[3] = at1;
		return at1;
	}

	// OPERACIÓN 5
	// Organizar los valores de loa operación 4 (Ahorro por unidad de tiempo P1) De
	// mayor a menor.
	double[] ordenarAsignamiento() {
		double[] asignamiento = new double[datos[0].length];

		return asignamiento;
	}

	// OPERACIÓN 6
	// Organizar los valores de loa operación 4 (Ahorro por unidad de tiempo P1) De
	// mayor a menor.
	double[] barra() {
		double[] asignamiento = new double[datos[0].length];

		return asignamiento;
	}

	public void ordenarLista() {
		List<Double> axut = DoubleStream.of(resultados[3]).boxed().collect(Collectors.toList());
		Comparator<Double> comparador = Collections.reverseOrder();

		Collections.sort(axut, comparador);
		List<Integer> agregado = new ArrayList<>();
		for (int columnaj = 0; columnaj < resultados[0].length; columnaj++) {
			if (resultados[3][columnaj] > 0) {
				resultados[4][columnaj] = getIndex(axut, resultados[3][columnaj], agregado) + 1;
			}
		}
	}

	public static int getIndex(List<Double> array, Double obj, List<Integer> agregado) {
		for (int i = 0; i < array.size(); i++) {
			if (obj.equals(array.get(i)) && !agregado.contains(Integer.valueOf(i))) {
				agregado.add(i);
				return i;
			}
		}
		return -1;
	}

}
