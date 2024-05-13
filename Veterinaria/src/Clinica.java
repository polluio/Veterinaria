package Veterinaria;

import static Veterinaria.Main.duenyo;
import static Veterinaria.Main.mascota;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import static Veterinaria.Main.ois;
import static Veterinaria.Main.ois;

public class Clinica {

    private Scanner num = new Scanner(System.in);

    private static MiObjectOutputStream oos = null;
    private static MiObjectInputStream ois = null;

    private HashSet<Cliente> clientes;

    public Clinica() {
        clientes = new HashSet<>();
    }

    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    public void darDeAltaCliente(String dni, String nombreCompleto) {
        Cliente c = new Cliente(dni, nombreCompleto);
        try {
            if (this.clientes.add(c)) {
                this.guardarClientes(c);
            } else {
                System.out.println("no se ha podido dar de alta este cliente");
            }
        } catch (Exception e) {
        }
    }

    public Cliente encontrarCliente(Cliente miCliente) {

        for (Cliente c : this.clientes) {
            if (c.equals(miCliente)) {
                return c;
            }
        }
        return null; // TODO: cuidado manejar esta posibilidad 

    }

    public Mascota encontrarMascota(Cliente cliente, int idMascota) {
        for (Mascota m : cliente.mascotas) {
            if (m.id == idMascota) {
                return m;
            }

        }
        return null;

    }

    public void darDeAltaMascota(Mascota m) {
        try {

            Cliente duenyoEncontrado = this.encontrarCliente(m.duenyo);

            if (duenyoEncontrado == null) {
                return;
            }

            if (duenyoEncontrado.mascotas.add(m)) {
                //m.duenyo = duenyoEncontrado;
                guardarMascotas(m);
                actualizarFicheroClientes();
            } else {
                System.out.println("no se ha podido registrar la mascota");
            }
        } catch (Exception e) {
        }

    }

    public void borrarCliente(String dni) {
        Cliente c = new Cliente(dni);
        try {
            if (this.clientes.remove(c)) {
                System.out.printf("cliente con %s borrado\n", dni);
                actualizarFicheroClientes();
            } else {
                System.out.println("no se ha podido dar de baja");
            }
        } catch (Exception e) {
        }
    }

    public void vacunarMamifero(Mamifero mam) {
        if (mam.getEstaVacunado()) {
            System.out.println("Ya está vacunado");
        } else {
            mam.vacunar(LocalDate.now());
            System.out.println("vacunado con éxito");
            //mam.agregarTratamiento("Vacunacion");            
        }
        for (Cliente cliente : clientes) {
            for (Mascota mascota : cliente.mascotas) {
                actualizarFicheroMascotas(cliente);
            }
        }
        actualizarFicheroClientes();
    }

    public void listarClientes() {
        for (Cliente cliente : this.clientes) {
            System.out.println(cliente);
            for (Mascota m : cliente.mascotas) {
                System.out.println("\t"+m);
            }
            System.out.println();
        }
    }

    public void listarMamiferosVacunados() {
        for (Cliente cliente : clientes) {
            for (Mascota mascota : cliente.mascotas) {
                if (mascota instanceof Mamifero) {
                    Mamifero m = (Mamifero) mascota;
                    if (m.getEstaVacunado()) {
                        System.out.println(m);
                    }
                }
            }
        }

        //ESTO NO FUNCIONA, POR QUE?
//        try {
//            ois = new MiObjectInputStream(new FileInputStream(new File("mascotas.dat")));
//            Object aux = ois.readObject();
//            while (aux != null) {
//                System.out.println(aux);
//                if (aux instanceof Mamifero) {
//                    Mamifero m = (Mamifero) aux;
//                    if (m.getEstaVacunado() == true) {
//                        System.out.println(m);
//                    }
//                }
//                aux = ois.readObject();
//
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            //System.out.println(e.getMessage());
//        } finally {
//            try {
//                ois.close();
//            } catch (Exception e) {
//            }
//        }
    }

    //TRABAJO CON FICHEROS
    public void guardarClientes(Cliente cliente) {
        File f = new File("clientes.dat");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            oos = new MiObjectOutputStream(new FileOutputStream(f, true));
            oos.writeObject(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
        }
    }

    public void guardarMascotas(Mascota m) {
        File f = new File("mascotas.dat");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            oos = new MiObjectOutputStream(new FileOutputStream(f, true));

            if (m instanceof Mamifero) {
                oos.writeObject((Mamifero) m);
            }
            if (m instanceof Pez) {
                oos.writeObject((Pez) m);
            }
            if (m instanceof Ave) {
                oos.writeObject((Ave) m);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
        }
    }

    public void cargarClientes(HashSet<Cliente> clientes) {
        try {
            ois = new MiObjectInputStream(new FileInputStream("clientes.dat"));
            Object aux = ois.readObject();
            do {
                clientes.add((Cliente) aux);
            } while (aux != null);
        } catch (Exception e) {
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
        }
    }

    public void actualizarFicheroClientes() {
        HashSet<Cliente> clientes = this.getClientes();
        try {
            oos = new MiObjectOutputStream(new FileOutputStream("clientes.dat"));
            for (Cliente cliente : clientes) {
                oos.writeObject(cliente);

            }
        } catch (Exception e) {
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
        }

    }

    public void actualizarFicheroMascotas(Cliente c) {
        HashSet<Mascota> mascotas = c.mascotas;
        try {
            oos = new MiObjectOutputStream(new FileOutputStream("mascotas.dat"));
            for (Mascota m : mascotas) {
                if (m instanceof Mamifero) {
                    oos.writeObject((Mamifero) m);
                }
                if (m instanceof Pez) {
                    oos.writeObject((Pez) m);
                }
                if (m instanceof Ave) {
                    oos.writeObject((Ave) m);
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
        }

    }

    public void tienda(Cliente c1) {
        // Parte de David Y Carlos
        int OPC, wh = 0, OPCdesc = 0;
        boolean esCliente = false;

        for (Cliente cliente : clientes) {
            if (c1.equals(cliente)) {
                esCliente = true;
                c1 = cliente;
            }
        }

        if (esCliente) {

            System.out.println("Bienvenido a la tienda");
            System.out.println("Desea comprar lo recetado por el veterinario? o Dejar de ser socio.");
            System.out.println("1. Comprar\n2. No comprar\n3. Darse de baja");
            OPC = num.nextInt();
            //empieza la tienda

            do {
                try {
                    switch (OPC) {
                        case 1:
                            //Compruebo si es socio, con un atributo de tipo Boolean que le añado al Cliente, Si es cliente de antemano le sumo 10 puntos, para futuros descuentos
                            if (c1.getEsSocio()) {
                                runSocioTask(c1, OPCdesc);
                                wh++;
                            } else {
                                runNotSocioTask(c1, wh);
                            }

                            break;
                        case 2:
                            System.out.println("Para que vienes entonces >:(");
                            wh++;
                            break;
                        case 3:
                            if (c1.getEsSocio()) {
                                c1.desInscribirSocio();
                                System.out.println("Ya no eres socio");
                            } else {
                                System.out.println("No puedes darte de baja sin ser socio primero.");
                            }
                            wh++;

                            break;

                        default:
                            throw new AssertionError();
                    }

                } catch (AssertionError e) {
                    System.out.println("Numero equivocado, hasta la proxima");
                    wh++;
                }
            } while (wh == 0);

        } else {
            System.out.println("Primero tienes q darte de alta (opcion 1)");
        }
    }

    public static void IntroducirTratamiento(Mamifero mam) {
        Scanner tcl = new Scanner(System.in);
        String descripcion;

        System.out.println("Introduce que la descripcion del tratamiento:");
        descripcion = tcl.nextLine();
        mam.agregarTratamiento(descripcion);

    }

    public static void ImprimirTratamiento(Mamifero mam) {
        Scanner tcl = new Scanner(System.in);
        String nombreFichero;
        System.out.println("Introduce el nombre del fichero donde quieres que se impriman los tratamientos de tu mascota");
        nombreFichero = tcl.nextLine();
        mam.imprimirHistorrialArchivo(nombreFichero);
    }

    public void runSocioTask(Cliente c1, int OPCdesc) {
        System.out.println("Dado que usted es socio, se sumaran 10 puntos a su cuenta por su compra");
        c1.setPuntos(c1.getPuntos() + 10);

        // Si el cliente tiene 20 puntos o mas le hago el descuento, esto realmente no hace nada util, simplemente pesabamos que quedaria bien
        if (c1.getPuntos() >= 20) {
            System.out.println("Tiene usted tiene " + c1.getPuntos() + " puntos, desea un descuento en su compra?");
            System.out.println("(1)Si (2)No");
            OPCdesc = num.nextInt();

            switch (OPCdesc) {
                case 1:
                    System.out.println("Perfecto, le restaremos 10 puntos a su cuenta.");
                    c1.setPuntos(-10);
                    break;
                case 2:
                    System.out.println("De acuerdo, no se le aplicara ningun descuento.");
                    break;
                default:
                    throw new AssertionError();
            }
        }

        System.out.println("Gracias por su compra.");
    }

    public void runNotSocioTask(Cliente c1, int wh) {
        int Soc;
        System.out.println("Usted no es socio, ¿le gustaria inscribirse?");
        System.out.println("(1)Sí (2)No");
        Soc = num.nextInt();
        switch (Soc) {
            case 1:
                System.out.println("Perfecto, sumaremos 10 puntos a su cuenta por su compra.");
                c1.InscribirSocio();
                c1.setPuntos(c1.getPuntos() + 10);
                actualizarFicheroClientes();
                System.out.println("Gracias por su compra.");
                wh++;
                break;
            case 2:
                System.out.println("De acuerdo.");
                System.out.println("Gracias por su compra.");
                wh++;
                break;
            default:
                throw new AssertionError();
        }
    }

//Se puede imprimir en un fichero de texto los datos de los clientes (dni y nombre).
    public void imprimirClientes(String rutaFichero) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(rutaFichero));
            for (Cliente cliente : clientes) {
                bw.write(cliente.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
