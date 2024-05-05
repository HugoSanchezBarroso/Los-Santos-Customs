package com.app.LosSantosCustoms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {

	public static void main(String[] args) {
		
		
		//Primera consulta de la BD.
		//consultarUsuarios();
		
		//Insertamos nuevo registro de Usuario
		//insertarUsuario("5", "Kiko", "Rivera");
		
		//Tercera consulta a la BD.
		//consultarUsuarios();
		
		//Cambio de nombre de Lola a Rosario
		actualizarUsuario("Nombre", "Rosario");
		actualizarUsuario("ID", "4");
		
		//Tercera consulta de la BD.
		consultarUsuarios();
		
		//Eliminar ID mayores a 4
		//eliminarUsuario();
		
		//Cuarta consulta de la BD.
		//consultarUsuarios();		
	}
	
	//-- CRUD de Usuario
	
	public static void insertarUsuario (String ID, String Nombre, String Apellidos) {
		//Insert
		try {
			String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Usuario " + "VALUES ('"+ID+"','"+Nombre+"', '"+Apellidos+"')");
			conn.close();
		}catch (Exception e) {
			System.err.println("No se ha podido insertar los datos. ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void consultarUsuarios () {
		
		// Abrimos la conexión capturando el posible error con URL, Usuario, Contraseña
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lossantoscustoms", "root", "");				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement st = connection.createStatement();
				
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				
				ResultSet resultset = st.executeQuery("SELECT * FROM Usuario")) {
			
			// Ahora, por cada fila el resultset, realizamos las operaciones correspondientes
			
			while (resultset.next()) {
				String ID = resultset.getString("ID");
				String Nombre = resultset.getString("Nombre");
				String Apellidos = resultset.getString("Apellidos");
				System.out.println(ID + "\t" + Nombre + "\t" + Apellidos);
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
	
	public static void actualizarUsuario (String campo, String nuevoValor) {
		
			    try {
			    	 String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
		            Connection conn = DriverManager.getConnection(url,"root","");
			         String query = "update Usuario set " + campo + "=? where ID=5";
			         PreparedStatement ps = conn.prepareStatement(query);
			         ps.setString(1, nuevoValor);
			         ps.executeUpdate();
			         System.out.println("Actualización realizada correctamente.");
			         } 
			    catch (Exception e) {
			            e.printStackTrace();
			      }
	}
	
	public static void eliminarUsuario() {
		try {
			String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
	        Connection con = DriverManager.getConnection(url,"root","");
	        String SQL = "DELETE FROM Usuario WHERE ID > 3 ";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.executeUpdate();
		} catch (Exception e) {
           e.printStackTrace();
		}
	}
}

