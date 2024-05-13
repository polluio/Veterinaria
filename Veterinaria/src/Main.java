package Veterinaria;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

    static Scanner tec = new Scanner(System.in);
    static Scanner num = new Scanner(System.in);
    static Clinica clinica = new Clinica();
    static String nombre, dni, especie;
    static int idCliente, idMascota;
    static LocalDate fecha;
    static Cliente duenyo, cliente;
    static Mascota mascota;

    
    static MiObjectInputStream ois = null;

    public static void main(String[] args) {
        
        //Cargar clientes
        try {
            ois = new MiObjectInputStream(new FileInputStream("clientes.dat"));
            Object aux = ois.readObject();
            while (aux != null) {
                clinica.getClientes().add((Cliente) aux);
                aux = ois.readObject();
            }
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
        }
        //Cargar mascotas
        try {
            ois = new MiObjectInputStream(new FileInputStream("mascotas.dat"));
            Object aux = ois.readObject();
            while (aux != null) {
                mascota = (Mascota) aux;
                duenyo = mascota.duenyo;
                duenyo.mascotas.add(mascota);
                aux = ois.readObject();
            }
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
        }

        while (true) {
            System.out.println("\n1. Dar de alta nuevo cliente");
            System.out.println("2. Borrar cliente");
            System.out.println("3. Vacunar mamífero");
            System.out.println("4. Listar clientes y mascotas");
            System.out.println("5. Listar mamíferos vacunados");
            System.out.println("6. Dar de alta nueva mascota");
            System.out.println("7. Ir a tienda");
            System.out.println("8. Aplicar tratamiento a un mamifero");
            System.out.println("9. Imprimir listado de tratamiento");
            System.out.println("10. Salir");
            System.out.print("\nElige una opción: ");
            int opcion = num.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("nombre: ");
                    nombre = tec.nextLine();
                    System.out.print("dni: ");
                    dni = tec.nextLine();
                    clinica.darDeAltaCliente(dni, nombre);
                    break;

                case 2:
                    System.out.print("dni: ");
                    dni = tec.nextLine();
                    clinica.borrarCliente(dni);
                    break;

                case 3:
                    Mamifero m = buscarMamifero();
                    if(m != null){
                        clinica.vacunarMamifero(m);
                    }
                    break;

                case 4:
                    clinica.listarClientes();
                    break;

                case 5:
                    clinica.listarMamiferosVacunados();
                    break;

                case 6:
                    System.out.println("qué tipo de mascota quieres dar de alta");
                    System.out.println("1. mamifero");
                    System.out.println("2. pez");
                    System.out.println("3. ave");
                    int opt = num.nextInt();
                    System.out.print("id: ");
                    idMascota = num.nextInt();
                    System.out.print("dni dueño: ");
                    dni = tec.nextLine();
                    duenyo = new Cliente(dni);
                    System.out.print("nombre: ");
                    nombre = tec.nextLine();
                    System.out.println("fecha de nacimiento: YYYY-MM-DD ");
                    fecha = LocalDate.parse(tec.nextLine());
                    System.out.print("especie: ");
                    especie = tec.nextLine();

                    switch (opt) {
                        case 1:
                            Mamifero mamifero = new Mamifero(idMascota, duenyo, nombre, fecha, especie);
                            clinica.darDeAltaMascota((Mamifero) mamifero);
                            break;
                        case 2:
                            Pez pez = new Pez(idMascota, duenyo, nombre, fecha, especie);
                            clinica.darDeAltaMascota((Pez) pez);
                            break;
                        case 3:

                            System.out.print("número de anilla: ");
                            int anilla = num.nextInt();
                            Ave ave = new Ave(idMascota, duenyo, nombre, fecha, especie, anilla);
                            clinica.darDeAltaMascota((Ave) ave);
                            break;
                    }

                    break;

                case 7:
                    System.out.println("DNI: ");
                    dni = tec.nextLine();
                    cliente = new Cliente(dni);
                    clinica.tienda(cliente);
                    break;
                case 8:
                    Mamifero mamifero = buscarMamifero();
                    if (mascota == null) {
                        System.out.println("no se ha encontrado ninguna mascota");
                    } else {
                        clinica.IntroducirTratamiento(mamifero);
                    }
                    clinica.actualizarFicheroMascotas(mascota.duenyo);
                    break;
                case 9:
                    mamifero = buscarMamifero();
                    if (mamifero == null) {
                        System.out.println("no se ha encontrado ninguna mascota");
                    } else {
                        clinica.ImprimirTratamiento(mamifero);
                    }
                    
                    break;

                case 10:
                    System.out.println("Saliendo...");

                    try {

                    } catch (Exception e) {
                    }
                    // Guardar clientes antes de salir

                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public static Mascota buscaMascota(){
    System.out.print("dni del cliente: ");
                    dni = tec.nextLine();
                    cliente = new Cliente(dni);
                    cliente = clinica.encontrarCliente(cliente);
                    for (Mascota m : cliente.mascotas) {
                        System.out.println(m);
                    }
                    System.out.print("id de la mascota: ");
                    idMascota = num.nextInt();
                    return clinica.encontrarMascota(cliente, idMascota);
    }
    
    public static Mamifero buscarMamifero(){
    System.out.print("dni del cliente: ");
                    dni = tec.nextLine();
                    cliente = new Cliente(dni);
                    cliente = clinica.encontrarCliente(cliente);
                    for (Mascota m : cliente.mascotas) {
                        System.out.println(m);
                    }
                    System.out.print("id de la mascota: ");
                    idMascota = num.nextInt();
                    Mascota m = clinica.encontrarMascota(cliente, idMascota);
                    if (m instanceof Mamifero){
                        return (Mamifero) m;
                    }
                    else{
                        System.out.println("no se ha encontrado la mascota o no es un mamifero");
                        return null;
                    }
    }
}
