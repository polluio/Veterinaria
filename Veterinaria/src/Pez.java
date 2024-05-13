package Veterinaria;


import java.time.LocalDate;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
// Clase Pez que hereda de Mascota
class Pez extends Mascota {
    public Pez(int id, Cliente duenyo, String nombre, LocalDate fechaNacimiento, String especie) {
        super(id, duenyo, nombre, fechaNacimiento, especie);
    }
}
