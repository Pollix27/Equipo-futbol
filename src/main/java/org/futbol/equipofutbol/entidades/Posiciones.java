package org.futbol.equipofutbol.entidades;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posiciones")
public class Posiciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_posicion")
    private Long idPosicion;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    // Relación One-to-Many con Jugadores
    @OneToMany(mappedBy = "posicion")
    private List<Jugadores> jugadores = new ArrayList<>();

    // Constructores
    public Posiciones() {
    }

    public Posiciones(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(Long idPosicion) {
        this.idPosicion = idPosicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugadores> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Posiciones{" +
                "idPosicion=" + idPosicion +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}