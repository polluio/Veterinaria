package Veterinaria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
class Mamifero extends Mascota implements Vacunable {

    private LocalDate fechaVacunacion;
    private boolean estaVacunado;
    private ArrayList<Tratamiento> historial = new ArrayList<Tratamiento>();

    public Mamifero(int id, Cliente duenyo, String nombre, LocalDate fechaNacimiento, String especie) {
        super(id, duenyo, nombre, fechaNacimiento, especie);
    }

    public void vacunar(LocalDate fecha) {
        this.estaVacunado = true;
        this.fechaVacunacion = fecha;
    }

    public boolean getEstaVacunado() {
        return estaVacunado;
    }

    //Se agrega el tratamiento al ArayList historial
    public void agregarTratamiento(String descripcion) {

        historial.add(new Tratamiento(LocalDate.now(), descripcion));
    }

    //Metodo para pasar el ArrayList "Historial" a un fichero
    public void imprimirHistorrialArchivo(String nombreArchivo) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo));
            writer.println("Historial de tratamientos para " + nombre + " (" + especie + "):");

            for (Tratamiento tratamiento : historial) {
                writer.println("Fecha: " + tratamiento.getFecha() + ", Descripción: " + tratamiento.getDescripcion());

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + nombreArchivo);
        }
    }

    public void Historial() {
        for (Tratamiento tratamiento : historial) {
            System.out.println("Fecha: " + tratamiento.getFecha() + ", Descripción: " + tratamiento.getDescripcion());

        }
    }

    @Override
    public String toString() {
        return super.toString()+", esta vacunadx: "+estaVacunado;
    }
    

}
