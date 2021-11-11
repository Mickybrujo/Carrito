package com.ipn.mx.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carrito {

    static Scanner sc = new Scanner(System.in);

    private static ArrayList<String> nombres = new ArrayList<>();
    private static ArrayList<Integer> cantidades = new ArrayList<>();

    public static void verProductos() {

        if (nombres.isEmpty() || cantidades.isEmpty()) {
            System.out.println("\nEl carrito está vacio.");
        } else {

            System.out.println("\nCARRITO DE COMPRAS:\n");
            for (int i = 0; i < nombres.size(); i++) {
                System.out.println("Producto: " + nombres.get(i));
                System.out.println("Cantidad: " + cantidades.get(i));
                System.out.println("");
            }
        }

    }

    public void agregarProductos(List<String> Datos) {

        boolean validacion = false;

        while (!validacion) {
            System.out.println("\nIngrese el nombre del articulo a agregar.");
            String nombre = sc.nextLine();

            if (!Datos.contains(nombre)) {
                System.out.println("\nEl producto no esta disponible.");
            } else if (nombres.contains(nombre)) {
                System.out.println("\nEl producto ya se encuentra en el carrito.");
            } else {
                nombres.add(nombre);
                validacion = true;
            }
        }

        validacion = false;

        while (!validacion) {
            System.out.println("\nIngrese la cantidad del articulo a agregar.");
            int cantidad = Integer.parseInt(sc.nextLine());

            if (cantidad <= 0) {
                System.out.println("Debe introducir una cantidad mayor a 0.");
            } else {
                cantidades.add(cantidad);
                validacion = true;
            }
        }

        System.out.println("\nProducto Agregado correctamente.\n");

    }

    public static void editarProductos() {

        int indice = 0;
        boolean validacion = false;
        boolean existencia = false;

        System.out.println("\nIngrese el nombre del articulo a actualizar su cantidad.");
        String nombre = sc.nextLine();

        for (int i = 0; i < nombres.size(); i++) {
            if (nombres.get(i).equals(nombre)) {
                indice = i;

                while (!validacion) {
                    System.out.println("Ingrese la nueva cantidad del articulo.");
                    int cantidad = Integer.parseInt(sc.nextLine());

                    if (cantidad <= 0) {
                        System.out.println("Debe introducir una cantidad mayor a 0.");
                    } else {
                        cantidades.set(i, cantidad);
                        System.out.println("\nLa cantidad del producto se actualizó exitosamente.");
                        validacion = true;
                        existencia = true;
                    }
                }
                break;
            }
        }
        if (!existencia) {
            System.out.println("El producto no se encuentra en el carrito.");
        }

    }

    public static void eliminarProductos() {

        int indice = 0;
        boolean existencia = false;

        System.out.println("Ingrese el nombre del articulo a eliminar del carrito.");
        String nombre = sc.nextLine();

        for (int i = 0; i < nombres.size(); i++) {
            if (nombres.get(i).equals(nombre)) {
                indice = i;

                nombres.remove(i);
                cantidades.remove(i);
                existencia = true;

                System.out.println("\nEl producto se eliminó exitosamente.");
                break;

            }
        }
        if (!existencia) {
            System.out.println("El producto no se encuentra en el carrito.");
        }

    }

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public void setNombres(ArrayList<String> nombres) {
        this.nombres = nombres;
    }

    public ArrayList<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(ArrayList<Integer> cantidades) {
        this.cantidades = cantidades;
    }

}
