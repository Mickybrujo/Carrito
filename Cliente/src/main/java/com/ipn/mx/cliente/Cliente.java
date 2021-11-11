package com.ipn.mx.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {

            // System.out.printf("Escriba la dirección del servidor:");
            // String host = br.readLine();
            String host = "localhost";

            // System.out.printf("\n\nEscriba el puerto:");
            // int pto = Integer.parseInt(br.readLine());
            int pto = 7000;

            Socket conexion = new Socket(host, pto);

            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());

            menuCliente(conexion);

            conexion.close();

        } catch (Exception e) {

        }
    }

    public static void menuCliente(Socket conexion) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        Carrito carritoCompras = new Carrito();

        try {
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            System.out.println("\nBienvenido al carrito de compras.\n");

            List<String> Datos = recibirProductos(conexion);

            verProductos(Datos);

            do {
                System.out.println("");
                System.out.println("\n---------MENU---------");
                System.out.println("----------------------");
                System.out.println("Seleccione una opción:");
                System.out.println("1. Volver a ver los productos disponibles.");
                System.out.println("2. Agregar productos al carrito.");
                System.out.println("3. Cambiar cantidades.");
                System.out.println("4. Eliminar producto.");
                System.out.println("5. Ver el carrito Actual.");
                System.out.println("6. Realizar compra.");
                System.out.println("7. Salir.");
                System.out.println("----------------------");

                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        verProductos(Datos);
                        break;
                    case 2:
                        carritoCompras.agregarProductos(Datos);
                        break;
                    case 3:
                        carritoCompras.editarProductos();
                        break;
                    case 4:
                        carritoCompras.eliminarProductos();
                        break;
                    case 5:
                        carritoCompras.verProductos();
                        break;
                    case 6:
                        realizarCompra(carritoCompras, conexion);
                        break;
                    case 7:
                        salida.writeInt(0);
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                        break;

                }
            } while (opcion != 7);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static List<String> recibirProductos(Socket conexion) {
        try {

            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            salida.writeInt(1);

            InputStream inputStream = conexion.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            List<String> Datos = (List<String>) objectInputStream.readObject();

            return Datos;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void verProductos(List<String> Datos) {

        System.out.println("\nProductos Disponibles:");

        for (int i = 0; i < Datos.size(); i++) {
            System.out.println("-----------------------------");
            System.out.println("Nombre: " + Datos.get(i));
            i++;
            System.out.println("Descripción: " + Datos.get(i));
            i++;
            System.out.println("Cantidad: " + Datos.get(i));
            i++;
            System.out.println("Precio: " + Datos.get(i));
            System.out.println("-----------------------------");
            System.out.println("");
        }

    }

    public static void realizarCompra(Carrito carrito, Socket conexion) {

        try {
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
            salida.writeInt(2);

            InputStream inputStream = conexion.getInputStream();
            OutputStream outputStream = conexion.getOutputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(carrito.getNombres());
            objectOutputStream.writeObject(carrito.getCantidades());
            
            ArrayList<String> resultados = (ArrayList<String>) objectInputStream.readObject();
            
            
            for(int i=0; i<resultados.size();i++){
                System.out.println(resultados.get(i));
            }
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
