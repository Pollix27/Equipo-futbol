package org.futbol.equipofutbol.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partidos")
public class Partidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Long idPartido;

    // Relación Many-to-One con Equipos Local
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_local", nullable = false)
    private Equipos equipoLocal;

    // Relación Many-to-One con Equipos Visitante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_visitante", nullable = false)
    private Equipos equipoVisitante;

    @Column(name = "goles_local", nullable = false)
    private Integer golesLocal = 0;

    @Column(name = "goles_visitante", nullable = false)
    private Integer golesVisitante = 0;

    @Column(name = "fecha_partido", nullable = false)
    private LocalDate fechaPartido;

    // Relación One-to-Many con Goles
    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goles> goles = new ArrayList<>();

    // Constructores
    public Partidos() {
    }

    public Partidos(Equipos equipoLocal, Equipos equipoVisitante, LocalDate fechaPartido) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fechaPartido = fechaPartido;
        this.golesLocal = 0;
        this.golesVisitante = 0;
    }

    // Getters y Setters
    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public Equipos getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipos equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipos getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipos equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(Integer golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public LocalDate getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(LocalDate fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public List<Goles> getGoles() {
        return goles;
    }

    public void setGoles(List<Goles> goles) {
        this.goles = goles;
    }

    // Método de ayuda para agregar gol y actualizar marcador
    public void agregarGol(Goles gol) {
        goles.add(gol);
        gol.setPartido(this);

        // Actualizar marcador según el equipo del jugador
        if (gol.getJugador().getEquipo().equals(this.equipoLocal)) {
            this.golesLocal++;
        } else if (gol.getJugador().getEquipo().equals(this.equipoVisitante)) {
            this.golesVisitante++;
        }
    }

    @Override
    public String toString() {
        return "Partidos{" +
                "idPartido=" + idPartido +
                ", equipoLocal=" + (equipoLocal != null ? equipoLocal.getNombre() : "null") +
                ", equipoVisitante=" + (equipoVisitante != null ? equipoVisitante.getNombre() : "null") +
                ", golesLocal=" + golesLocal +
                ", golesVisitante=" + golesVisitante +
                ", fechaPartido=" + fechaPartido +
                '}';
    }
}