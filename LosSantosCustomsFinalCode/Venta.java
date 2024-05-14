package com.app.LosSantosCustoms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Venta {
	
	
	//-- CRUD de Venta
	public static void insertarVenta(Integer ID_vehiculo, Integer ID_cliente, Integer ID_trabajador, String fecha_venta) {
		
		
		try {
			String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Venta (ID_vehiculo, ID_cliente, ID_trabajador, fecha_venta) " + "VALUES ('"+ID_vehiculo+"',  '"+ID_cliente+"', '"+ID_trabajador+"', '"+fecha_venta+"')");
			conn.close();
		}catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	
public static void consultarVenta () {
		
		// Abrimos la conexión capturando el posible error con URL, Usuario, Contraseña
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lossantoscustoms", "root", "");				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement st = connection.createStatement();
				
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				
				ResultSet resultset = st.executeQuery("SELECT * FROM Venta")) {
			
			// Ahora, por cada fila el resultset, realizamos las operaciones correspondientes
			
			while (resultset.next()) {
				String ID = resultset.getString("ID");
				String ID_cliente = resultset.getString("ID_cliente");
				String ID_vehiculo = resultset.getString("ID_vehiculo");
				String ID_trabajador = resultset.getString("ID_trabajador");
				String fecha_venta = resultset.getString("fecha_venta");


				System.out.println(ID + "\t" + ID_cliente + "\t" + ID_vehiculo + "\t" + ID_trabajador + "\t" + fecha_venta);
			}
			
			// Por Seguridad, cerramos la conexión
			
				resultset.close();
				st.close();
				connection.close();
				
		}catch (SQLException e) {
			System.out.println("Error en la conexión de la base de datos.");
			e.printStackTrace();
		}
	}
	
	public static void eliminarTablaVenta() {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Venta";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void eliminarVenta(String idVenta) {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Venta WHERE ID = " + idVenta;
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
	