package com.ipn.mx.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.net.Socket;
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

        try {
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            System.out.println("Bienvenido al carrito de compras.");

            do {
                System.out.println("---------MENU---------");
                System.out.println("----------------------");
                System.out.println("Seleccione una opción:");
                System.out.println("1. Ver los productos disponibles.");
                System.out.println("5. Salir");
                System.out.println("----------------------");

                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        verProductos(conexion);
                        break;
                    case 2:
                        salida.writeInt(2);
                        break;
                    case 3:
                        salida.writeInt(3);
                        break;
                    case 4:
                        salida.writeInt(4);
                        break;
                    case 5:
                        salida.writeInt(5);
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                        break;

                }
            } while (opcion != 5);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void verProductos(Socket conexion) {
        try {

            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            salida.writeInt(1);

            InputStream inputStream = conexion.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            List<String> Datos = (List<String>) objectInputStream.readObject();

            System.out.println("Productos:");

            for (int i = 0; i < Datos.size(); i++) {
                System.out.println("----------------------");
                System.out.println("Nombre: " + Datos.get(i));
                i++;
                System.out.println("Descripción: " + Datos.get(i));
                i++;
                System.out.println("Cantidad: " + Datos.get(i));
                i++;
                System.out.println("Precio: " + Datos.get(i));
                System.out.println("----------------------");
                System.out.println("");
            }

            System.out.println("recibido");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    


}
