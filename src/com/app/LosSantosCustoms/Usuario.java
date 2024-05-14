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
	
	public static void insertarUsuario (String Nombre, String Apellidos, String Correo, String Contrasenia) {
		//Insert
		try {
			String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Usuario (Nombre, Apellidos, Correo, Contrasenia) " + "VALUES ('"+Nombre+"', '"+Apellidos+"', '"+Correo+"', '"+Contrasenia+"')");
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
				String Correo = resultset.getString("Correo");
				String Contrasenia = resultset.getString("Contrasenia");
				String Permiso = resultset.getString("Permiso");
				System.out.println(ID + "\t" + Nombre + "\t" + Apellidos + "\t" + Correo + "\t" + Contrasenia + "\t" + Permiso);
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
	
	
	
	public static void eliminarTablaUsuario() {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Usuario";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void eliminarUsuario(String idUsuario) {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Usuario WHERE ID = '" + idUsuario+"'";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    public static String consultarPermisoUsuario(String correo, String contrasenia) {
        String permiso = null;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lossantoscustoms", "root", "");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT Permiso FROM Usuario WHERE Correo = ? AND Contrasenia = ?")) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, contrasenia);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    permiso = resultSet.getString("Permiso");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos.");
            e.printStackTrace();
        }

        return permiso;
    }
    
    
    public static void buscarVehiculosPorMarca(String marca) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lossantoscustoms", "root", "");
             Statement st = connection.createStatement();
             ResultSet resultset = st.executeQuery("SELECT * FROM Vehiculo WHERE Marca='" + marca + "'")) {

            while (resultset.next()) {
                String ID = resultset.getString("ID");
                String Modelo = resultset.getString("Modelo");
                String Color = resultset.getString("Color");
                String Matricula = resultset.getString("Matricula");
                int N_plazas = resultset.getInt("N_plazas");
                int Potencia = resultset.getInt("Potencia");
                int Anho = resultset.getInt("Anho");
                double Precio = resultset.getDouble("Precio");
                System.out.println(ID + "\t" + Modelo + "\t" + Color + "\t" + Matricula + "\t" + N_plazas + "\t" + Potencia + "\t" + Anho + "\t" + Precio);
            }

            resultset.close();
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos.");
            e.printStackTrace();
        }
    }


    
}
