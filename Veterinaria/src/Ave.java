package Veterinaria;


import java.time.LocalDate;
import java.util.Date;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
// Clase Ave que hereda de Mascota
class Ave extends Mascota {
    private int numeroAnilla;

    public Ave(int id, Cliente duenyo, String nombre, LocalDate fechaNacimiento, String especie, int numeroAnilla) {
        super(id, duenyo, nombre, fechaNacimiento, especie);
        this.numeroAnilla = numeroAnilla;
    }
}