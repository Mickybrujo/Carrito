package Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductosDAO {

    public static void crearDB(Producto producto) {
        Conexion dbConnect = new Conexion();

        try (Connection conexion = dbConnect.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO productos (nombre,descripcion,cantidad,precioUnitario) VALUES (?,?,?,?);";
                ps = conexion.prepareStatement(query);
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getDescripcion());
                ps.setInt(3, producto.getCantidad());
                ps.setDouble(4, producto.getPrecio());

                ps.executeUpdate();

                System.out.println("");
                System.out.println("-------------------------------");
                System.out.println("Producto agregado correctamente");
                System.out.println("-------------------------------");
                System.out.println("");

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void leerDB() {
        Conexion dbConnect = new Conexion();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection conexion = dbConnect.get_connection()) {
            String query = "SELECT * FROM productos";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();

            System.out.println("\nProductos registrados: ");
            System.out.println("----------------------");
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("idProducto"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Descripción: " + rs.getString("descripcion"));
                System.out.println("Cantidad: " + rs.getInt("cantidad"));
                System.out.println("Precio: " + rs.getDouble("precioUnitario"));
                System.out.println("");
            }
            System.out.println("----------------------");
            System.out.println("");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static ArrayList leerDBServidor() {
        Conexion dbConnect = new Conexion();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection conexion = dbConnect.get_connection()) {
            String query = "SELECT nombre,descripcion,cantidad,precioUnitario FROM productos";

            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            ArrayList<String> Datos = new ArrayList<>(columns);

            while (rs.next()) {
                int i = 1;
                while (i <= columns) {
                    Datos.add(rs.getString(i++));
                }
            }
            return Datos;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public static void borrarDB(int idProducto) {
        Conexion dbConnect = new Conexion();

        try (Connection conexion = dbConnect.get_connection()) {
            PreparedStatement ps = null;

            try {
                String query = "DELETE FROM productos WHERE idProducto = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, idProducto);

                ps.executeUpdate();

                System.out.println("");
                System.out.println("--------------------------------");
                System.out.println("Producto eliminado correctamente");
                System.out.println("--------------------------------");
                System.out.println("");

            } catch (Exception e) {
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void actualizarDB(Producto producto) {

        Conexion dbConnect = new Conexion();

        try (Connection conexion = dbConnect.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "UPDATE productos SET nombre = ?, descripcion = ?, cantidad =?, precioUnitario = ? where idProducto = ?";

                ps = conexion.prepareStatement(query);
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getDescripcion());
                ps.setInt(3, producto.getCantidad());
                ps.setDouble(4, producto.getPrecio());
                ps.setInt(5, producto.getIdProducto());

                ps.executeUpdate();

                System.out.println("");
                System.out.println("----------------------------------");
                System.out.println("Producto Actualizado Correctamente");
                System.out.println("----------------------------------");
                System.out.println("");

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void restarProductosDB(String nombre) {

        Conexion dbConnect = new Conexion();

        try (Connection conexion = dbConnect.get_connection()) {
            PreparedStatement ps = null;

            try {
                String query = "DELETE FROM productos WHERE idProducto = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, nombre);

                ps.executeUpdate();

                System.out.println("");
                System.out.println("--------------------------------");
                System.out.println("Producto eliminado correctamente");
                System.out.println("--------------------------------");
                System.out.println("");

            } catch (Exception e) {
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
