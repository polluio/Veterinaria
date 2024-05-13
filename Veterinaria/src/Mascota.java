package Veterinaria;


import java.io.Serializable;
import java.time.LocalDate;

abstract class Mascota implements Serializable {
    protected int id;
    protected Cliente duenyo;
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String especie;

    public Mascota(int id, Cliente duenyo, String nombre, LocalDate fechaNacimiento, String especie)  {
        this.id = id;
        this.duenyo = duenyo;
        this.nombre = nombre;
        if (fechaNacimiento==null){
            this.fechaNacimiento=LocalDate.parse("0/0/0");
        }else{
            this.fechaNacimiento = fechaNacimiento;
        }
        this.especie = especie;
    }

    @Override
    public String toString() {
        return "id: " + id + ", nombre: " + nombre;
    }

  
}

