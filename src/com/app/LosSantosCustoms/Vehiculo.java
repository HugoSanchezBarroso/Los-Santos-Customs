package com.app.LosSantosCustoms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Vehiculo {

	public static void main(String[] args) {
		
		//Primera consulta de la BD.
		//consultarVehiculo();
		
		//insertarVehiculo("V004", "Lamborghini", "Urus", "Naranja", "6785GRD", 2, 750, "2018", 240294.00);
		
		//Segunda consulta de la BD.
		//consultarVehiculo();
		
		//Actualizar tabla Vehiculo
		//actualizarVehiculo("Modelo", "Performante");
		
		//Tercera consulta de la BD.
		//consultarVehiculo();
		
		//Eliminar la tabla insertada
		//eliminarVehiculo();
		
		//Cuarta consulta de la BD.
		consultarVehiculo();
	}
	
	//-- CRUD de Establecimientoo
	public static void insertarVehiculo(String Marca, String Modelo, String Color, String Matricula, Integer N_plazas, Integer Potencia, Integer Anho, Double Precio) {
		
		//Modifico el código de insertar dos artículos para uno específico
		//pasado por argumentos.
		
		try {
			String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Vehiculo (Marca, Modelo, Color, Matricula, N_plazas, Potencia, Anho, Precio) " + "VALUES ('"+Marca+"',  '"+Modelo+"', '"+Color+"', '"+Matricula+"', '"+N_plazas+"', '"+Potencia+"', '"+Anho+"', '"+Precio+"')");
			conn.close();
		}catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void consultarVehiculo () {
		
		// Abrimos la conexión capturando el posible error con URL, Usuario, Contraseña
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lossantoscustoms", "root", "");				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement st = connection.createStatement();
				
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				
				ResultSet resultset = st.executeQuery("SELECT * FROM Vehiculo")) {
			
			// Ahora, por cada fila el resultset, realizamos las operaciones correspondientes
			
			while (resultset.next()) {
				String ID = resultset.getString("ID");
				String Marca = resultset.getString("Marca");
				String Modelo = resultset.getString("Modelo");
				String Color = resultset.getString("Color");
				String Matricula = resultset.getString("Matricula");
				int N_plazas = resultset.getInt("N_plazas");
				int Potencia = resultset.getInt("Potencia");
				double Precio = resultset.getDouble("Precio");

				System.out.println(ID + "\t" + Marca + "\t" + Modelo + "\t" + Color + "\t" + Matricula + "\t" + N_plazas + "\t" + Potencia + "\t" + Precio);
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
	
	public static void actualizarVehiculo (String campo, String nuevoValor) {
	
			    try {
			    	 String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
		            Connection conn = DriverManager.getConnection(url,"root","");
			         String query = "update Vehiculo set " + campo + "=? where ID='V004'";
			         PreparedStatement ps = conn.prepareStatement(query);
			         ps.setString(1, nuevoValor);
			         ps.executeUpdate();
			         System.out.println("Actualización realizada correctamente.");
			         } 
			    catch (Exception e) {
			            e.printStackTrace();
			      }
	}
	

	
	public static void eliminarTablaVehiculo() {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Vehiculo";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void eliminarVehiculo(String idVehiculo) {
        try {
            String url = "jdbc:mysql://localhost:3306/lossantoscustoms";
            Connection con = DriverManager.getConnection(url,"root","");
            String SQL = "DELETE FROM Vehiculo WHERE ID = '" + idVehiculo+"'";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
