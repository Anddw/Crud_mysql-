
package com.mycompany.java_crud_myql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CAlumnos {

    int codigo;
    String nombreAlumnos; 
    String ApellidosAlumnos;
    
    public int getCodigo() { 
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidosAlumnos() {
        return ApellidosAlumnos;
    }

    public void setApellidosAlumnos(String ApellidosAlumnos) {
        this.ApellidosAlumnos = ApellidosAlumnos;
    }
    
    public void InsertarAlumno(JTextField paramNombres, JTextField paramApellidos) {

    setNombreAlumnos(paramNombres.getText());
    setApellidosAlumnos(paramApellidos.getText());

    CConexion objetoConexion = new CConexion();

    // Corrige la consulta para no usar comillas simples alrededor de los marcadores de posición
    String consulta = "INSERT INTO Alumnos (nombres, apellidos) VALUES (?, ?)";

    try {

        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setString(1, getNombreAlumnos());
        cs.setString(2, getApellidosAlumnos());

        cs.execute();

        JOptionPane.showMessageDialog(null, "Se insertó correctamente el Alumno");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al insertar el Alumno: " + e.toString());
    }
    
}

    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
        
       CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();

       TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTablaTotalAlumnos.setRowSorter(ordenarTabla);

        // Establecer el modelo en la tabla
        String sql="";
        
        modelo.addColumn("id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        sql = "select * from Alumnos;";
        
        String[] datos = new String[3];
        Statement st;
        
        try{
            st= objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                
                datos[0]= rs.getString(1);
                 datos[1]= rs.getString(2);
                 datos[2]= rs.getString(3);
                 
                 modelo.addRow(datos);
            }
            
            paramTablaTotalAlumnos.setModel(modelo);
            
        }catch (Exception e){
            
             JOptionPane.showMessageDialog(null, "No sepuedo mostrar los registros, error: " + e.toString());
            
        }
        
        
    }
    public void SeleccionarAlumnos(JTable paramTableAlumnos, JTextField paramID, JTextField paramNombres, JTextField paramApellidos) {
        
        
        try {
        int fila = paramTableAlumnos.getSelectedRow();
        if (fila >= 0) {
            // Corrige el error: .toString() es un método, por lo que debe tener paréntesis
            paramID.setText(paramTableAlumnos.getValueAt(fila, 0).toString());
            paramNombres.setText(paramTableAlumnos.getValueAt(fila, 1).toString());
            paramApellidos.setText(paramTableAlumnos.getValueAt(fila, 2).toString());
        }
        
        else{
               JOptionPane.showMessageDialog(null, "fola no seleccionada");
        }
        
    } catch (Exception e) {
         
             JOptionPane.showMessageDialog(null, "Error de seleccion, error:" + e.toString());
        }
        
        
    }
    
     public void Modificar(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos) {
    setCodigo(Integer.parseInt(paramCodigo.getText()));
    setNombreAlumnos(paramNombres.getText());
    setApellidosAlumnos(paramApellidos.getText());

    CConexion objetoConexion = new CConexion();
    String consulta = "UPDATE Alumnos SET nombres=?, apellidos=? WHERE id=?";

    try {
        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setString(1, getNombreAlumnos());
        cs.setString(2, getApellidosAlumnos());
        cs.setInt(3, getCodigo());
        cs.execute();

        JOptionPane.showMessageDialog(null, "Modificación Exitosa");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.getMessage());
    }
}


        
      public void EliminarAlumnos(JTextField paramCodigo) {
    setCodigo(Integer.parseInt(paramCodigo.getText()));

    CConexion objetoConexion = new CConexion();
    String consulta = "DELETE FROM Alumnos WHERE id=?";

    try {
        CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
        cs.setInt(1, getCodigo());  // Establece el valor del parámetro
        cs.execute();
        JOptionPane.showMessageDialog(null, "Se eliminó correctamente el Alumno");
    } catch (Exception e) {
        e.printStackTrace(); // Muestra detalles de la excepción en la consola
        JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.getMessage());
    }
}

        }
        
    