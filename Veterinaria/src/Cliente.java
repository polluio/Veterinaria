package Veterinaria;


import java.io.Serializable;
import java.util.*;


public class Cliente implements Serializable{
    private String dni;
    private String nombreCompleto;
    private int puntos;
    private Boolean esSocio = false;
    HashSet<Mascota> mascotas;
    

    public HashSet<Mascota> getMascotas() {
        return mascotas;
    }

public Cliente(String dni, String nombreCompleto) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.mascotas = new HashSet<>();
    }

    public Cliente(String dni) {
        this.dni = dni;
    }
    
    public int getPuntos() {
        return puntos;
    }

    public Boolean getEsSocio() {
        return esSocio;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void InscribirSocio() {
        this.esSocio = true;
    }
    
    
    public void desInscribirSocio() {
        this.esSocio = false;
    }


    @Override
    public String toString() {
        return nombreCompleto+" con DNI: "+dni+" tiene "+mascotas.size()+" mascotas en la clinica ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}