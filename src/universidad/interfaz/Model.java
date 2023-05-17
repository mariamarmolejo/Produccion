package universidad.interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


// El Modelo de la Tabla es el que controla todos los
// datos que se colocan en ella
public class Model extends AbstractTableModel {
	public Double datos[][];
  
  // Esta clase imprime los datos en la consola cada vez
  // que se produce un cambio en cualquiera de las
  // casillas de la tabla
  class TablaListener implements TableModelListener {
    public void tableChanged( TableModelEvent evt ) {
      for( int i=0; i < datos.length; i++ ) {
        for( int j=0; j < datos[0].length; j++ )
          System.out.print( datos[i][j] + " " );
        System.out.println();
      }
    }
  }
  // Constructor
  Model(int numeroColumnas) {
	datos = new Double[4][numeroColumnas];
	/*datos[1][0] = "Precio de compra por unidad (PCUi) ($/und)";
	datos[2][0] = "Costo de material por unidad (CMUi) ($/und)";
	datos[3][0] = "Demanda por unidad (Di) (und/periodo)";
	datos[4][0] = "Tiempo unitario de procesamienro (TPUi) (hrs/unidad)";*/
    addTableModelListener( new TablaListener() );
  }
  // Devuelve el número de columnas de la tabla
  public int getColumnCount() { 
    return( datos[0].length ); 
  }
  // Devuelve el número de filas de la tabla
  public int getRowCount() { 
    return( datos.length );
  }
  // Devuelve el valor de una determinada casilla de la tabla
  // identificada mediante fila y columna
  public Object getValueAt( int fila,int col ) { 
    return( datos[fila][col] ); 
  }
  // Cambia el valor que contiene una determinada casilla de
  // la tabla
  public void setValueAt( Object valor,int fila,int col ) {
    datos[fila][col] = Double.valueOf((String)valor);
    // Indica que se ha cambiado
    fireTableDataChanged();
  }
  // Indica si la casilla identificada por fila y columna es
  // editable
  public boolean isCellEditable( int fila,int col ) { 
    return( true ); 
  }
}       

