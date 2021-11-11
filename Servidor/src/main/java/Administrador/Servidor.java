package Administrador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    public static void IniciarServidor() {

        System.out.println("Servidor Iniciado.\n");

        try {

            ServerSocket servidor = new ServerSocket(7000);

            for (;;) {

                int opcion = 0;

                System.out.println("Esperando conexion");

                Socket conexion = servidor.accept();

                System.out.println("Conexión establecida desde" + conexion.getInetAddress() + ":" + conexion.getPort());

                DataInputStream entrada = new DataInputStream(conexion.getInputStream());
                DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

                do {
                    System.out.println("Esperando instrucciones del cliente.");
                    opcion = entrada.readInt();

                    System.out.println(opcion);
                    switch (opcion) {
                        case 1:
                            enviarConsultaDB(conexion);
                            break;
                        case 2:
                            realizarCompraDB(conexion);
                            break;
                        default:
                            break;
                    }

                } while (opcion != 0);

                System.out.println("Transaccion Realizada");

            }

        } catch (IOException e) {
            System.out.println("Se perdio la conexion");
            System.out.println(e);
        }

    }

    public static void enviarConsultaDB(Socket conexion) {
        try {
            OutputStream outputStream = conexion.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            ArrayList<String> Datos = new ArrayList<>();
            Datos = ProductosService.listarProductosServidor();

            objectOutputStream.writeObject(Datos);
            System.out.println("Se envio la informacion");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void realizarCompraDB(Socket conexion) {

        try {

            System.out.println("Recibiendo informacion");

            OutputStream outputStream = conexion.getOutputStream();
            InputStream inputStream = conexion.getInputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            ArrayList<String> nombresArticulos = (ArrayList<String>) objectInputStream.readObject();
            ArrayList<Integer> cantidadesArticulos = (ArrayList<Integer>) objectInputStream.readObject();

            ArrayList<String> resultados = ProductosService.realizarcomprasServidor(nombresArticulos, cantidadesArticulos);

            objectOutputStream.writeObject(resultados);
            
            System.out.println(nombresArticulos);
            System.out.println(cantidadesArticulos);

            System.out.println("Se envio la informacion");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
