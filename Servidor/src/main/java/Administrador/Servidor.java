package Administrador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Servidor {

    public static void IniciarServidor() {

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
                    opcion = entrada.readInt();

                    System.out.println(opcion);
                    switch (opcion) {
                        case 1:
                            enviarConsultaDB(conexion);
                            break;
                        case 2:
                            break;
                        case 5:
                            break;
                        default:
                            break;
                    }

                } while (opcion != 5);

                System.out.println("Transaccion Realizada");

            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void menuServidor(Socket conexion, int opcion) {

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
}
