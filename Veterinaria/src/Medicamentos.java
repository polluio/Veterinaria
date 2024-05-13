/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Carlos Moya Collado
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Medicamentos implements Serializable {

    // Variables de instancia
    private double precio;
    private String nombre;

    // Constructor
    public Medicamentos(double precio, String nombre) {
        this.precio = precio;
        this.nombre = nombre;
    }

    // Método para mostrar todos los medicamentos para las distintas especies
    public static void mostrarMedicamentos(Map<String, Medicamentos[]> medicamentosPorEspecie) {
        for (String especie : medicamentosPorEspecie.keySet()) {
            System.out.println("Medicamentos para " + especie + ":");
            for (Medicamentos medicamento : medicamentosPorEspecie.get(especie)) {
                System.out.println("Nombre: " + medicamento.getNombre());
                System.out.println("Precio: " + medicamento.getPrecio());
                System.out.println("Descripción: " + medicamento.getDescripcion());
                System.out.println();
            }
        }
    }

    // Getters y setters
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    public static void main(String[] args) {
        // Crear medicamentos para distintas especies
        Medicamentos medicamentoAve1 = new Medicamentos(10.5, "Medicamento para aves 1");
        Medicamentos medicamentoAve2 = new Medicamentos(15.75, "Medicamento para aves 2");
        Medicamentos medicamentoMamifero1 = new Medicamentos(20.0, "Medicamento para mamíferos 1");
        Medicamentos medicamentoMamifero2 = new Medicamentos(30.25, "Medicamento para mamíferos 2");
        Medicamentos medicamentoPez1 = new Medicamentos(8.99, "Medicamento para peces 1");
        Medicamentos medicamentoPez2 = new Medicamentos(12.0, "Medicamento para peces 2");

        // Crear un mapa para almacenar medicamentos por especie
        Map<String, Medicamentos[]> medicamentosPorEspecie = new HashMap<>();
        medicamentosPorEspecie.put("Ave", new Medicamentos[]{medicamentoAve1, medicamentoAve2});
        medicamentosPorEspecie.put("Mamífero", new Medicamentos[]{medicamentoMamifero1, medicamentoMamifero2});
        medicamentosPorEspecie.put("Pez", new Medicamentos[]{medicamentoPez1, medicamentoPez2});

        // Mostrar todos los medicamentos por especie
        mostrarMedicamentos(medicamentosPorEspecie);
    }
}
