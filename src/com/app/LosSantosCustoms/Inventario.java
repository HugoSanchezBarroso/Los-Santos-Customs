package com.app.LosSantosCustoms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inventario {
	
	
	//-- CRUD de Inventario
	public static void insertarInventario(Integer ID_establecimiento, Integer ID_vehiculo) {
		
		
		try {
			String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Inventario (ID_establecimiento, ID_vehiculo) " + "VALUES ('"+ID_establecimiento+"',  '"+ID_vehiculo+"')");
			conn.close();
		}catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	
public static void consultarInventario () {
		
		// Abrimos la conexión capturando el posible error con URL, Usuario, Contraseña
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lossantoscustoms", "root", "");				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement st = connection.createStatement();
				
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				
				ResultSet resultset = st.executeQuery("SELECT * FROM Inventario")) {
			
			// Ahora, por cada fila el resultset, realizamos las operaciones correspondientes
			
			while (resultset.next()) {
				String ID_establecimiento = resultset.getString("ID_establecimiento");
				String ID_vehiculo = resultset.getString("ID_vehiculo");
				String ID_venta = resultset.getString("ID_venta");

				System.out.println(ID_establecimiento + "\t" + ID_vehiculo + "\t" + ID_venta);
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
	
	public static void eliminarTablaInventario() {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Inventario";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void eliminarInventario(String idEstablecimiento, String idVehiculo) {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Inventario WHERE ID_establecimiento = " + idEstablecimiento + " AND ID_vehiculo = " + idVehiculo;
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}