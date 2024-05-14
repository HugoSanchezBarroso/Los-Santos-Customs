package com.app.LosSantosCustoms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Establecimiento {
	public static void main(String[] args) {
		
		//Primera consulta de la BD.
		consultarEstablecimiento();
		
		//Insertar nuevo registro a la tabla Establecimiento.
		insertarEstablecimiento("Gasoil City", "Calle Sánchez Cotán s/n", "Kuala Lumpur", "Viernes a Domingo 10am-22pm");
		
		//Segunda consulta a la BD.
		consultarEstablecimiento();
		
		//Actualizar la tabla Establecimiento - campo Ciudad de Ciudad A a Birmingham
		actualizarEstablecimiento("Ciudad", "Birmingham");
		
		//Tercera consulta a la BD.
		consultarEstablecimiento();
		
		//Eliminar la tabla insertada
		//eliminarEstablecimiento();
		
		//Cuarta consulta a la BD.
		consultarEstablecimiento();
	}
	
	//-- CRUD de Establecimiento
	public static void insertarEstablecimiento(String Nombre, String Direccion, String Ciudad, String Horario) {
		
		//Modifico el código de insertar dos artículos para uno específico
		//pasado por argumentos.
		
		try {
			String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Establecimiento (Nombre, Direccion, Ciudad, Horario)" + "VALUES ('"+Nombre+"',  '"+Direccion+"', '"+Ciudad+"', '"+Horario+"')");
			conn.close();
		}catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
public static void consultarEstablecimiento () {
		
		// Abrimos la conexión capturando el posible error con URL, Usuario, Contraseña
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lossantoscustoms", "root", "");				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement st = connection.createStatement();
				
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				
				ResultSet resultset = st.executeQuery("SELECT * FROM Establecimiento")) {
			
			// Ahora, por cada fila el resultset, realizamos las operaciones correspondientes
			
			while (resultset.next()) {
				String ID = resultset.getString("ID");
				String Nombre = resultset.getString("Nombre");
				String Direccion = resultset.getString("Direccion");
				String Ciudad = resultset.getString("Ciudad");
				String Horario = resultset.getString("Horario");

				System.out.println(ID + "\t" + Nombre + "\t" + Direccion + "\t" + Ciudad + "\t" + Horario);
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
	
	public static void actualizarEstablecimiento (String campo, String nuevoValor) {
	
			    try {
			    	 String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
			    	 Connection conn = DriverManager.getConnection(url,"root","");
			         String query = "update Establecimiento set " + campo + "=? where ID='E001'";
			         PreparedStatement ps = conn.prepareStatement(query);
			         ps.setString(1, nuevoValor);
			         ps.executeUpdate();
			         System.out.println("Actualización realizada correctamente.");
			         } 
			    catch (Exception e) {
			            e.printStackTrace();
			      }
	}
	
	public static void eliminarTablaEstablecimiento() {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Establecimiento";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void eliminarEstablecimiento(String idEstablecimiento) {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Vehiculo WHERE ID = '" + idEstablecimiento+"'";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}