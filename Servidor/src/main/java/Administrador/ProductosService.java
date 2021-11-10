package Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductosService {

    public static void crearProducto() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nIngresa los datos del producto\n");

        System.out.println("Ingresa el nombre del producto");
        String nombre = sc.nextLine();

        System.out.println("Ingresa la descripcion del producto");
        String descripcion = sc.nextLine();

        System.out.println("Ingresa la cantidad del producto");
        int cantidad = sc.nextInt();

        System.out.println("Ingresa el precio del producto");
        double precio = sc.nextDouble();
        System.out.println("");

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCantidad(cantidad);
        producto.setPrecio(precio);

        ProductosDAO.crearDB(producto);
    }

    public static void listarProducto() {
        ProductosDAO.leerDB();
    }

    public static ArrayList listarProductosServidor() {
        
        ArrayList<String> Datos = new ArrayList<>();
        Datos = ProductosDAO.leerDBServidor();

        return Datos;
    }

    public static void borrarProducto() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nIngrese el id del producto a eliminar.");
        int idProducto = sc.nextInt();

        ProductosDAO.borrarDB(idProducto);
    }

    public static void editarProducto() {
        Scanner sc = new Scanner(System.in);

        Producto producto = new Producto();

        System.out.println("\nIngrese el id del producto a actualizar.");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingresa el nombre del producto");
        String nombre = sc.nextLine();

        System.out.println("Ingresa la descripcion del producto");
        String descripcion = sc.nextLine();

        System.out.println("Ingresa la cantidad del producto");
        int cantidad = sc.nextInt();

        System.out.println("Ingresa el precio del producto");
        double precio = sc.nextDouble();

        System.out.println("");

        producto.setIdProducto(idProducto);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCantidad(cantidad);
        producto.setPrecio(precio);

        ProductosDAO.actualizarDB(producto);
    }

}
