package org.futbol.equipofutbol.entidades;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipos")
public class Equipos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Long idEquipo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    // Relación One-to-Many con Jugadores
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jugadores> jugadores = new ArrayList<>();

    // Relación One-to-Many con Partidos (como local)
    @OneToMany(mappedBy = "equipoLocal")
    private List<Partidos> partidosLocal = new ArrayList<>();

    // Relación One-to-Many con Partidos (como visitante)
    @OneToMany(mappedBy = "equipoVisitante")
    private List<Partidos> partidosVisitante = new ArrayList<>();

    // Constructores
    public Equipos() {
    }

    public Equipos(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
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

    public List<Partidos> getPartidosLocal() {
        return partidosLocal;
    }

    public void setPartidosLocal(List<Partidos> partidosLocal) {
        this.partidosLocal = partidosLocal;
    }

    public List<Partidos> getPartidosVisitante() {
        return partidosVisitante;
    }

    public void setPartidosVisitante(List<Partidos> partidosVisitante) {
        this.partidosVisitante = partidosVisitante;
    }

    // Métodos de ayuda para manejar la relación bidireccional
    public void agregarJugador(Jugadores jugador) {
        jugadores.add(jugador);
        jugador.setEquipo(this);
    }

    public void removerJugador(Jugadores jugador) {
        jugadores.remove(jugador);
        jugador.setEquipo(null);
    }

    @Override
    public String toString() {
        return "Equipos{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}