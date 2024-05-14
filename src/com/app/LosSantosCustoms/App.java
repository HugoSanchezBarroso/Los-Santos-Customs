package com.app.LosSantosCustoms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("¡Bienvenido a Los Santos Customs!");
        boolean bucle = true;
        Scanner sc = new Scanner(System.in);

        while (bucle) {
            System.out.println("");
            System.out.println("----------------------------------------------");
            System.out.println("                     MENÚ                ");
            System.out.println("----------------------------------------------");
            System.out.println("1. Insertar uno o varios registros.");
            System.out.println("2. Mostrar todos los registros.");
            System.out.println("3. Eliminar todos los registros de una tabla.");
            System.out.println("4. Eliminar un resgistro.");
            System.out.println("5. Salir");
            System.out.println("----------------------------------------------");
            System.out.println("");

            System.out.println("Introduzca una opción: ");
            int res = sc.nextInt();

            switch (res) {
                case 1:
                    insertarRegistros(sc);
                    break;
                case 2:
                    mostrarRegistros(sc);
                    break;
                case 3:
                    eliminarTabla(sc);
                    break;
                case 4:
                    eliminarRegistro(sc);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    bucle = false;
                    break;
                default:
                    System.out.println("Opción incorrecta!");
            }
        }
        sc.close();
    }
    
    

    private static void insertarRegistros(Scanner sc) {
        System.out.println("¿En qué tabla desea introducir nuevos registros?");
        String nombreTabla = sc.next();
        try {
            switch (nombreTabla.toLowerCase()) {
                case "establecimiento":
                    System.out.println("¿Cuántos registros debe introducir?");
                    int numDoc = sc.nextInt();
                    for (int i = 0; i < numDoc; i++) {
                        System.out.println("Registro n." + (i + 1));
                        System.out.println("Introduzca el Nombre: ");
                        String Nombre = sc.next();
                        System.out.println("Introduzca la Dirección: ");
                        String Direccion = sc.next();
                        System.out.println("Introduzca la Ciudad: ");
                        String Ciudad = sc.next();
                        System.out.println("Introduzca el Horario: ");
                        String Horario = sc.next();
                        Establecimiento.insertarEstablecimiento(Nombre, Direccion, Ciudad, Horario);
                    }
                    break;
                case "usuario":
                    System.out.println("¿Cuántos registros debe introducir?");
                    int numDocUser = sc.nextInt();
                    for (int i = 0; i < numDocUser; i++) {
                        System.out.println("Registro n." + (i + 1));
                        System.out.println("Introduzca el Nombre: ");
                        String Nombre = sc.next();
                        System.out.println("Introduzca los Apellidos: ");
                        String Apellidos = sc.next();
                        System.out.println("Introduzca el Correo: ");
                        String Correo = sc.next();
                        System.out.println("Introduzca la Contraseña: ");
                        String Contrasenia = sc.next();
                        Usuario.insertarUsuario(Nombre, Apellidos, Correo, Contrasenia);
                    }
                    break;
                case "inventario":
                    System.out.println("¿Cuántos registros debe introducir?");
                    int numDocInve = sc.nextInt();
                    for (int i = 0; i < numDocInve; i++) {
                    	System.out.println("Registro n." + (i + 1));
                        System.out.println("Introduzca el ID del Establecimiento: ");
                        Integer ID_establecimiento = sc.nextInt();
                        System.out.println("Introduzca el ID del Vehiculo: ");
                        Integer ID_vehiculo = sc.nextInt();
                        Inventario.insertarInventario(ID_establecimiento, ID_vehiculo);
                    }
                    break;
                case "venta":
                    System.out.println("¿Cuántos registros debe introducir?");
                    int numDocVent = sc.nextInt();
                    for (int i = 0; i < numDocVent; i++) {
                    	System.out.println("Registro n." + (i + 1));
                        System.out.println("Introduzca el ID del Vahiculo: ");
                        Integer ID_vehiculo = sc.nextInt();
                        System.out.println("Introduzca el ID del Cliente: ");
                        Integer ID_cliente = sc.nextInt();
                        System.out.println("Introduzca el ID del Trabajador: ");
                        Integer ID_trabajador = sc.nextInt();
                        System.out.println("Introduzca la fecha: ");
                        String fecha_venta = sc.next();
                        Venta.insertarVenta(ID_vehiculo, ID_cliente, ID_trabajador, fecha_venta);
                    }
                    break;
                case "vehiculo":
                    System.out.println("¿Cuántos registros debe introducir?");
                    int numDocVeh = sc.nextInt();
                    for (int i = 0; i < numDocVeh; i++) {
                        System.out.println("Registro n." + (i + 1));
                        System.out.println("Introduzca la Marca: ");
                        String Marca = sc.next();
                        System.out.println("Introduzca el Modelo: ");
                        String Modelo = sc.next();
                        System.out.println("Introduzca el Color: ");
                        String Color = sc.next();
                        System.out.println("Introduzca la Matrícula: ");
                        String Matricula = sc.next();
                        System.out.println("Introduzca el Número de Plazas: ");
                        Integer N_plazas = sc.nextInt();
                        System.out.println("Introduzca la Potencia: ");
                        Integer Potencia = sc.nextInt();
                        System.out.println("Introduzca el Año de Fabricación");
                        Integer Anho = sc.nextInt();
                        System.out.println("Introduzca el Precio: ");
                        Double Precio = sc.nextDouble();
                        Vehiculo.insertarVehiculo(Marca, Modelo, Color, Matricula, N_plazas, Potencia, Anho, Precio);
                    }
                    break;
                default:
                    System.out.println("Tabla no válida.");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error al insertar registros. ") ;
            System.err.println(e.getMessage());
        }
    }
    


    private static void mostrarRegistros(Scanner sc) {
        System.out.println("Has elegido mostrar todos los registros de una tabla.");
        System.out.println("¿Qué tabla desea mostrar?");
        String nombreTabla = sc.next();

        try {
            switch (nombreTabla.toLowerCase()) {
                case "usuario":
                    System.out.println("Mostrando todos los registros de Usuario:");
                    Usuario.consultarUsuarios();
                    break;
                case "vehiculo":
                    System.out.println("Mostrando todos los registros de Vehiculo:");
                    Vehiculo.consultarVehiculo();
                    break;
                case "establecimiento":
                    System.out.println("Mostrando todos los registros de Establecimiento:");
                    Establecimiento.consultarEstablecimiento();
                    break;
                case "inventario":
                    System.out.println("Mostrando todos los registros de Inventario:");
                    Inventario.consultarInventario();
                    break;
                case "venta":
                    System.out.println("Mostrando todos los registros de Venta:");
                    Venta.consultarVenta();
                    break;
                default:
                    System.out.println("Tabla no existente.");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error al mostrar registros. ") ;
            System.err.println(e.getMessage());
        }
    }


    private static void eliminarTabla(Scanner sc) {
        System.out.println("¿Qué tabla desea borrar todos los registros?");
        String nombreTabla = sc.next();

        try {
            switch (nombreTabla.toLowerCase()) {
                case "usuario":
                    System.out.println("Eliminando todos los usuarios...");
                    Usuario.eliminarTablaUsuario();
                    break;
                case "inventario":
                    System.out.println("Eliminando todo el inventario...");
                    Inventario.eliminarTablaInventario();
                    break;
                case "venta":
                    System.out.println("Eliminando todas las ventas...");
                    Venta.eliminarTablaVenta();
                    break;
                case "vehiculo":
                    System.out.println("Eliminando todos los vehiculos...");
                    Vehiculo.eliminarTablaVehiculo();
                    break;
                case "establecimiento":
                    System.out.println("Eliminando todos los establecimientos...");
                    Establecimiento.eliminarTablaEstablecimiento();
                    break;
                default:
                    System.out.println("Tabla no existente.");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar tabla. ") ;
            System.err.println(e.getMessage());
        }
    }


    
    private static void eliminarRegistro(Scanner sc) {
        System.out.println("¿En qué tabla desea eliminar un registro?");
        String nombreTabla = sc.next();
        System.out.println("¿Cuál es el ID del registro que desea eliminar?");
        String id = sc.next();

        try {
            switch (nombreTabla.toLowerCase()) {
                case "usuario":
                    System.out.println("Eliminando Usuario con id: " +id + "...");
                    Usuario.eliminarUsuario(id);
                    break;
                case "inventario":
                    System.out.println("¿Cuál es el Vehiculo_ID del registro que desea eliminar?");
                    String idd = sc.next();
                    System.out.println("Eliminando Usuario con id: " +id +" " +idd+ "...");
                    Inventario.eliminarInventario(id, idd);
                    break;
                case "venta":
                    System.out.println("Eliminando Usuario con id: " +id + "...");
                    Venta.eliminarVenta(id);
                    break;
                case "vehiculo":
                    System.out.println("Eliminando Vehiculo con id: " +id + "...");
                    Vehiculo.eliminarVehiculo(id);
                    break;
                case "establecimiento":
                    System.out.println("Eliminando Establecimiento con id: " +id + "...");
                    Establecimiento.eliminarEstablecimiento(id);
                    break;
                default:
                    System.out.println("Tabla no existente.");
                    break;}
            }catch (Exception e){
            System.err.println("Error al eliminar registro. ") ;
            System.err.println(e.getMessage());
        }
    } 
    
    
}