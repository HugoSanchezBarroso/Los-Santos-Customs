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
            System.out.println("------------------------------------");
            System.out.println("                MENÚ                ");
            System.out.println("------------------------------------");
            System.out.println("1. Insertar uno o varios registros.");
            System.out.println("2. Mostrar todos los registros.");
            System.out.println("3. Mostrar uno o varios registros.");
            System.out.println("4. Eliminar una tabla.");
            System.out.println("5. Eliminar un resgistro.");
            System.out.println("6. Salir");
            System.out.println("------------------------------------");
            System.out.println("");

            System.out.println("Introduzca una opción: ");
            int res = sc.nextInt();

            switch (res) {
                case 1:
                    insertarRegistros();
                    break;
                case 2:
                    mostrarRegistros();
                    break;
                case 3:
                    eliminarTabla();
                    break;
                case 4:
                    eliminarRegistro();
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

    private static void insertarRegistros() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿En qué tabla desea introducir nuevos registros?");
        String nomTab = sc.next();
        try {
            switch (nomTab) {
                case "Establecimiento":
                    System.out.println("¿Cuántos registros debe introducir?");
                    int numDoc = sc.nextInt();
                    for (int i = 0; i < numDoc; i++) {
                        System.out.println("Registro n." + (i + 1));
                        System.out.println("Introduzca el ID: ");
                        String ID = sc.next();
                        System.out.println("Introduzca el Nombre: ");
                        String Nombre = sc.next();
                        System.out.println("Introduzca la Dirección: ");
                        String Direccion = sc.next();
                        System.out.println("Introduzca la Ciudad: ");
                        String Ciudad = sc.next();
                        System.out.println("Introduzca el Horario: ");
                        String Horario = sc.next();
                        System.out.println("Introduzca el ID_Vehiculo: ");
                        String ID_Vehiculo = sc.next();
                        Establecimiento.insertarEstablecimiento(ID, Nombre, Direccion, Ciudad, Horario, ID_Vehiculo);
                    }
                    break;
                case "Usuario":
                    System.out.println("¿Cuántos registros debe introducir?");
                    int numDocUser = sc.nextInt();
                    for (int i = 0; i < numDocUser; i++) {
                        System.out.println("Registro n." + (i + 1));
                        System.out.println("Introduzca el ID: ");
                        String ID = sc.next();
                        System.out.println("Introduzca el Nombre: ");
                        String Nombre = sc.next();
                        System.out.println("Introduzca los Apellidos: ");
                        String Apellidos = sc.next();
                        Usuario.insertarUsuario(ID, Nombre, Apellidos);
                    }
                    break;
                case "Vehiculo":
                    System.out.println("¿Cuántos registros debe introducir?");
                    int numDocVeh = sc.nextInt();
                    for (int i = 0; i < numDocVeh; i++) {
                        System.out.println("Registro n." + (i + 1));
                        System.out.println("Introduzca el ID: ");
                        String ID = sc.next();
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
                        Vehiculo.insertarVehiculo(ID, Marca, Modelo, Color, Matricula, N_plazas, Potencia, Anho, Precio);
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
        sc.close();

    }


    private static void mostrarRegistros() {
        Scanner sc = new Scanner(System.in);
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
                default:
                    System.out.println("Tabla no existente.");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error al mostrar registros. ") ;
            System.err.println(e.getMessage());
        }
        sc.close();
    }


    private static void eliminarTabla() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Qué tabla desea eliminar?");
        String nombreTabla = sc.next();

        try {
            switch (nombreTabla.toLowerCase()) {
                case "usuario":
                    System.out.println("Eliminando tabla Usuario...");
                    Usuario.eliminarTablaUsuario();
                    break;
                case "vehiculo":
                    System.out.println("Eliminando tabla Vehiculo...");
                    Vehiculo.eliminarTablaVehiculo();
                    break;
                case "establecimiento":
                    System.out.println("Eliminando tabla Establecimiento...");
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
        sc.close();

    }


    private static void eliminarRegistro() {
        Scanner sc = new Scanner(System.in);
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
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar registro. ") ;
            System.err.println(e.getMessage());
        }
        sc.close();

    }
}