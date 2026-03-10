/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package net.charly.eliminarhoy;

import java.util.List;
import java.util.Scanner;
import net.charly.eliminarhoy.modelo.entidades.Categoria;
import net.charly.eliminarhoy.modelo.entidades.Producto;
import net.charly.eliminarhoy.modelo.entidades.dao.CategoriaDAO;
import net.charly.eliminarhoy.modelo.entidades.dao.ProductoDAO;

/**
 *
 * @author ca921
 */
public class EliminarHoy {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int opc;
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ProductoDAO productoDAO = new ProductoDAO();

        do {
            System.out.println("-----------------------------------");
            System.out.println("\tBienvenido!");
            System.out.println("-----------------------------------");
            System.out.println("Seleccione una opcion: ");
            System.out.println("1) Categorias.");
            System.out.println("2) Productos.");
            System.out.println("3) Salir.");
            System.out.println("-----------------------------------");

            opc = leer.nextInt();
            leer.nextLine();

            if (opc == 1) {

                int opcCat;
                do {
                    System.out.println("----- MENU CATEGORIAS -----");
                    System.out.println("1) Ver categorias");
                    System.out.println("2) Crear categoria");
                    System.out.println("3) Actualizar categoria");
                    System.out.println("4) Eliminar categoria");
                    System.out.println("5) Volver");
                    System.out.println("-----------------------------------");
                    opcCat = leer.nextInt();
                    leer.nextLine();

                    switch (opcCat) {
                        case 1 -> {
                            System.out.println("-------- CATEGORIAS --------");
                            try {
                                List<Categoria> categorias = categoriaDAO.obtenerCategorias();
                                if (categorias.isEmpty()) {
                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println("No existen categorias disponibles!");
                                    System.out.println("-----------------------------------");
                                    System.out.println();

                                } else {
                                    System.out.println("-----------------------------------");
                                    for (Categoria c : categorias) {
                                        System.out.println("ID: " + c.getIdCategoria());
                                        System.out.println("Nombre: " + c.getNombreCategoria());
                                        System.out.println("Descripcion: " + c.getDescripcion());
                                        System.out.println("-----------------------------------");
                                        System.out.println();
                                    }
                                }

                            } catch (Exception ex) {
                                System.out.println("Error al mostrar las categorias disponibles!");
                            }
                        }
                        case 2 -> {
                            System.out.println("-------- AGREGAR CATEGORIA NUEVA --------");
                            try {
                                String nombreCategoria, descripcionCategoria;
                                System.out.print("Ingrese el nombre de la categoria: ");
                                nombreCategoria = leer.nextLine();
                                System.out.print("Ingrese la descripcion de la categoria: ");
                                descripcionCategoria = leer.nextLine();

                                if (nombreCategoria.isBlank() || descripcionCategoria.isBlank()) {
                                    System.out.println("-----------------------------------");
                                    System.out.println("\tDatos incompletos!");
                                    System.out.println("-----------------------------------");

                                } else {
                                    Categoria c = new Categoria();
                                    c.setNombreCategoria(nombreCategoria);
                                    c.setDescripcion(descripcionCategoria);

                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println(categoriaDAO.insertar(c));
                                    System.out.println("-----------------------------------");
                                    System.out.println();
                                }

                            } catch (Exception ex) {
                                System.out.println("Error al crear una categoria! " + ex.getLocalizedMessage());
                            }
                        }
                        case 3 -> {
                            System.out.println("-------- ACTUALIZAR CATEGORIA --------");
                            try {
                                int cat;
                                List<Categoria> categorias = categoriaDAO.obtenerCategorias();
                                if (categorias.isEmpty()) {
                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println("No existen categorias disponibles! No se puede completar la acción :(");
                                    System.out.println("-----------------------------------");
                                    System.out.println();

                                } else {
                                    System.out.println("-----------------------------------");
                                    for (Categoria c : categorias) {
                                        System.out.println("ID: " + c.getIdCategoria());
                                        System.out.println("Nombre: " + c.getNombreCategoria());
                                        System.out.println("Descripcion: " + c.getDescripcion());
                                        System.out.println("-----------------------------------");
                                        System.out.println();

                                    }
                                    System.out.print("Para continuar, por favor, seleccione la categoria a actualizar: ");
                                }
                                cat = leer.nextInt();
                                leer.nextLine();
                                System.out.println("-----------------------------------");
                                System.out.println();

                                Categoria c = categoriaDAO.obtenerCategoria(cat);
                                String dato1, dato2;
                                dato1 = c.getNombreCategoria();
                                dato2 = c.getDescripcion();

                                String nombreCategoria, descripcionCategoria;
                                System.out.print("Ingrese el nombre de la categoria (ANTIGUA: " + dato1 + ")\nNUEVA: ");
                                nombreCategoria = leer.nextLine();
                                System.out.print("Ingrese la descripcion de la categoria(ANTIGUA: " + dato2 + ")\nNUEVA: ");
                                descripcionCategoria = leer.nextLine();

                                if (nombreCategoria.isBlank() || descripcionCategoria.isBlank()) {
                                    System.out.println("-----------------------------------");
                                    System.out.println("\tDatos incompletos!");
                                    System.out.println("-----------------------------------");

                                } else {
                                    c.setNombreCategoria(nombreCategoria);
                                    c.setDescripcion(descripcionCategoria);

                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println(categoriaDAO.actualizar(c));
                                    System.out.println("-----------------------------------");
                                    System.out.println();
                                }

                            } catch (Exception ex) {
                                System.out.println("Error al mostrar las categorias disponibles!");
                            }
                        }
                        case 4 -> {
                            System.out.println("-------- ELIMINAR UNA CATEGORIA  --------");
                            try {
                                int cat;
                                List<Categoria> categorias = categoriaDAO.obtenerCategorias();
                                if (categorias.isEmpty()) {
                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println("No existen categorias disponibles! No se puede completar la acción :(");
                                    System.out.println("-----------------------------------");
                                    System.out.println();

                                } else {
                                    System.out.println("-----------------------------------");
                                    for (Categoria c : categorias) {
                                        System.out.println("ID: " + c.getIdCategoria());
                                        System.out.println("Nombre: " + c.getNombreCategoria());
                                        System.out.println("Descripcion: " + c.getDescripcion());
                                        System.out.println("-----------------------------------");
                                        System.out.println();
                                    }
                                    System.out.print("Para continuar, por favor, seleccione la categoria a eliminar: ");
                                }
                                cat = leer.nextInt();
                                leer.nextLine();
                                System.out.println("-----------------------------------");
                                System.out.println();

                                Categoria c = categoriaDAO.obtenerCategoria(cat);
                                int valida;

                                System.out.println("Esta seguro de eliminar la categoria con ID " + c.getIdCategoria() + " y nombre " + c.getNombreCategoria() + "?");
                                System.out.println("1) Si\n2) No");
                                System.out.println("-----------------------------------");
                                valida = leer.nextInt();
                                leer.nextLine();
                                System.out.println("-----------------------------------");
                                if (valida == 1) {
                                    System.out.println(categoriaDAO.eliminar(c));
                                }

                            } catch (Exception ex) {
                                System.out.println("Error al eliminar la categoria especificada!");
                            }
                        }

                        default -> {
                            System.out.println();
                            System.out.println("-----------------------------------");
                            System.out.println("Opcion invalida! Intente nuevamente");
                            System.out.println("-----------------------------------");
                            System.out.println();
                        }

                    }

                } while (opcCat != 5);

            } else if (opc == 2) {

                int opcProd;
                do {
                    System.out.println("----- MENU PRODUCTOS -----");

                    System.out.println("1) Ver productos");
                    System.out.println("2) Crear producto");
                    System.out.println("3) Actualizar producto");
                    System.out.println("4) Eliminar un producto");
                    System.out.println("5) Volver");
                    System.out.println("-----------------------------------");

                    opcProd = leer.nextInt();
                    leer.nextLine();

                    switch (opcProd) {
                        case 1 -> {
                            System.out.println("-------- PRODUCTOS --------");
                            try {
                                List<Producto> productos = productoDAO.obtenerProductos();
                                if (productos.isEmpty()) {
                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println("No existen productos disponibles!");
                                    System.out.println("-----------------------------------");
                                    System.out.println();

                                } else {
                                    System.out.println("-----------------------------------");
                                    for (Producto p : productos) {
                                        System.out.println("ID: " + p.getIdProducto());
                                        System.out.println("Nombre: " + p.getNombreProducto());
                                        System.out.println("Descripcion: " + p.getDescripcionProducto());
                                        System.out.println("Precio: $" + p.getPrecioProducto() + " MXN");
                                        System.out.println("Existencias: " + p.getExistencia());
                                        System.out.println("Creado el " + p.getCreatedAt());

                                        Categoria c = categoriaDAO.obtenerCategoria(p.getIdCategoria());
                                        System.out.println("Categoria: " + c.getNombreCategoria());

                                        System.out.println("-----------------------------------");
                                        System.out.println();
                                    }
                                }

                            } catch (Exception ex) {
                                System.out.println("Error al mostrar los productos disponibles!");
                            }
                        }
                        case 2 -> {
                            System.out.println("-------- AGREGAR PRODUCTO NUEVO --------");
                            try {
                                String nombreProducto, descripcionProducto;
                                double precioProducto;
                                int existenciaProducto, idCategoria;
                                System.out.print("Ingrese el nombre del producto: ");
                                nombreProducto = leer.nextLine();
                                System.out.print("Ingrese la descripcion del producto: ");
                                descripcionProducto = leer.nextLine();
                                System.out.print("Ingrese el precio del producto: ");
                                precioProducto = leer.nextDouble();
                                leer.nextLine();
                                System.out.print("Ingrese las existencias del producto: ");
                                existenciaProducto = leer.nextInt();
                                leer.nextLine();

                                List<Categoria> categorias = categoriaDAO.obtenerCategorias();
                                if (categorias.isEmpty()) {
                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println("No existen categorias disponibles! No se puede completar la acción :(");
                                    System.out.println("-----------------------------------");
                                    System.out.println();

                                } else {
                                    System.out.println("-----------------------------------");
                                    for (Categoria c : categorias) {
                                        System.out.println("ID: " + c.getIdCategoria());
                                        System.out.println("Nombre: " + c.getNombreCategoria());
                                        System.out.println("-----------------------------------");
                                        System.out.println();
                                    }
                                    System.out.print("Seleccione la categoria del producto: ");
                                }

                                idCategoria = leer.nextInt();
                                leer.nextLine();
                                System.out.println("-----------------------------------");
                                System.out.println();

                                if (nombreProducto.isBlank() || descripcionProducto.isBlank() || precioProducto <= 0 || existenciaProducto <= 0 || idCategoria <= 0) {
                                    System.out.println("-----------------------------------");
                                    System.out.println("\tDatos incompletos o incorrectos! Favor de verificar nuevamente ...");
                                    System.out.println("-----------------------------------");

                                } else {
                                    Producto p = new Producto();
                                    p.setNombreProducto(nombreProducto);
                                    p.setDescripcionProducto(descripcionProducto);
                                    p.setPrecioProducto(precioProducto);
                                    p.setExistencia(existenciaProducto);
                                    p.setIdCategoria(idCategoria);

                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println(productoDAO.insertar(p));
                                    System.out.println("-----------------------------------");
                                    System.out.println();
                                    
                                }

                            } catch (Exception ex) {
                                System.out.println("Error al crear un producto! " + ex.getLocalizedMessage());
                            }
                        }
                        case 3 -> {
                            System.out.println("-------- ACTUALIZAR PRODUCTO --------");
                            try {
                                int prod;
                                List<Producto> productos = productoDAO.obtenerProductos();
                                if (productos.isEmpty()) {
                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println("No existen productos disponibles!");
                                    System.out.println("-----------------------------------");
                                    System.out.println();

                                } else {
                                    System.out.println("-----------------------------------");
                                    for (Producto p : productos) {
                                        System.out.println("ID: " + p.getIdProducto());
                                        System.out.println("Nombre: " + p.getNombreProducto());
                                        System.out.println("Descripcion: " + p.getDescripcionProducto());
                                        System.out.println("Precio: $" + p.getPrecioProducto() + " MXN");
                                        System.out.println("Existencias: " + p.getExistencia());
                                        System.out.println("Creado el " + p.getCreatedAt());

                                        Categoria c = categoriaDAO.obtenerCategoria(p.getIdCategoria());
                                        System.out.println("Categoria: " + c.getNombreCategoria());

                                        System.out.println("-----------------------------------");
                                        System.out.println();
                                    }
                                    System.out.print("Para continuar, por favor, seleccione el producto a actualizar: ");
                                }
                                prod = leer.nextInt();
                                leer.nextLine();

                                if (prod != 0) {
                                    Producto p = productoDAO.obtenerProducto(prod);
                                    String nombreProducto, descripcionProducto;
                                    double precioProducto;
                                    int existenciaProducto, idCategoria;
                                    System.out.print("Ingrese el nombre del producto (ANTERIOR: " + p.getNombreProducto() + ")\nNUEVA: ");
                                    nombreProducto = leer.nextLine();
                                    System.out.print("Ingrese la descripcion del producto: (ANTERIOR: " + p.getDescripcionProducto() + ")\nNUEVA: ");
                                    descripcionProducto = leer.nextLine();
                                    System.out.print("Ingrese el precio del producto: (ANTERIOR: " + p.getPrecioProducto() + ")\nNUEVA: ");
                                    precioProducto = leer.nextDouble();
                                    leer.nextLine();
                                    System.out.print("Ingrese las existencias del producto: (ANTERIOR: " + p.getExistencia() + ")\nNUEVA: ");
                                    existenciaProducto = leer.nextInt();
                                    leer.nextLine();

                                    List<Categoria> categorias = categoriaDAO.obtenerCategorias();
                                    if (categorias.isEmpty()) {
                                        System.out.println();
                                        System.out.println("-----------------------------------");
                                        System.out.println("No existen categorias disponibles! No se puede completar la acción :(");
                                        System.out.println("-----------------------------------");
                                        System.out.println();

                                    } else {
                                        System.out.println("-----------------------------------");
                                        for (Categoria c : categorias) {
                                            System.out.println("ID: " + c.getIdCategoria());
                                            System.out.println("Nombre: " + c.getNombreCategoria());
                                            System.out.println("-----------------------------------");
                                            System.out.println();
                                        }
                                        System.out.print("Seleccione EL ID de la categoria del producto: (ANTERIOR: " + p.getIdCategoria() + ")\nNUEVA: ");
                                    }

                                    idCategoria = leer.nextInt();
                                    leer.nextLine();
                                    System.out.println("-----------------------------------");
                                    System.out.println();

                                    if (nombreProducto.isBlank() || descripcionProducto.isBlank() || precioProducto <= 0 || existenciaProducto <= 0 || idCategoria <= 0) {
                                        System.out.println("-----------------------------------");
                                        System.out.println("\tDatos incompletos o incorrectos! Favor de verificar nuevamente ...");
                                        System.out.println("-----------------------------------");

                                    } else {
                                        p.setNombreProducto(nombreProducto);
                                        p.setDescripcionProducto(descripcionProducto);
                                        p.setPrecioProducto(precioProducto);
                                        p.setExistencia(existenciaProducto);
                                        p.setIdCategoria(idCategoria);

                                        System.out.println();
                                        System.out.println("-----------------------------------");
                                        System.out.println(productoDAO.actualizar(p));
                                        System.out.println("-----------------------------------");
                                        System.out.println();
                                    }
                                }

                            } catch (Exception ex) {
                                System.out.println("Error al actualizar el producto especificado!");
                            }
                        }
                        case 4 -> {
                            System.out.println("-------- ELIMINAR UN PRODUCTO  --------");
                            try {
                                int prod;
                                List<Producto> productos = productoDAO.obtenerProductos();
                                if (productos.isEmpty()) {
                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println("No existen productos disponibles!");
                                    System.out.println("-----------------------------------");
                                    System.out.println();

                                } else {
                                    System.out.println("-----------------------------------");
                                    for (Producto p : productos) {
                                        System.out.println("ID: " + p.getIdProducto());
                                        System.out.println("Nombre: " + p.getNombreProducto());
                                        System.out.println("Descripcion: " + p.getDescripcionProducto());
                                        System.out.println("Precio: $" + p.getPrecioProducto() + " MXN");
                                        System.out.println("Existencias: " + p.getExistencia());
                                        System.out.println("Creado el " + p.getCreatedAt());

                                        Categoria c = categoriaDAO.obtenerCategoria(p.getIdCategoria());
                                        System.out.println("Categoria: " + c.getNombreCategoria());

                                        System.out.println("-----------------------------------");
                                        System.out.println();
                                    }
                                    System.out.println("Para continuar, por favor, seleccione el producto a eliminar: ");
                                }
                                prod = leer.nextInt();
                                leer.nextLine();

                                Producto p = productoDAO.obtenerProducto(prod);
                                int valida;

                                System.out.println("Esta seguro de eliminar el producto con ID " + p.getIdProducto() + " y nombre " + p.getNombreProducto() + "?");
                                System.out.println("1) Si\n2) No");
                                System.out.println("-----------------------------------");
                                valida = leer.nextInt();
                                leer.nextLine();
                                System.out.println("-----------------------------------");
                                if (valida == 1) {
                                    System.out.println();
                                    System.out.println("-----------------------------------");
                                    System.out.println(productoDAO.eliminar(p));
                                    System.out.println("-----------------------------------");
                                    System.out.println();
                                }

                            } catch (Exception ex) {
                                System.out.println("Error al eliminar el producto especificado!");
                            }
                        }

                        default -> {
                            System.out.println();
                            System.out.println("-----------------------------------");
                            System.out.println("Opcion invalida! Intente nuevamente");
                            System.out.println("-----------------------------------");
                            System.out.println();
                        }

                    }

                } while (opcProd != 5);
            }

        } while (opc != 3);
    }
}
