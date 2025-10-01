package org.futbol.equipofutbol.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "goles")
public class Goles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gol")
    private Long idGol;

    // Relación Many-to-One con Partidos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido", nullable = false)
    private Partidos partido;

    // Relación Many-to-One con Jugadores
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugadores jugador;

    @Column(name = "minuto", nullable = false)
    private Integer minuto;

    // Constructores
    public Goles() {
    }

    public Goles(Partidos partido, Jugadores jugador, Integer minuto) {
        this.partido = partido;
        this.jugador = jugador;
        this.minuto = minuto;
    }

    // Getters y Setters
    public Long getIdGol() {
        return idGol;
    }

    public void setIdGol(Long idGol) {
        this.idGol = idGol;
    }

    public Partidos getPartido() {
        return partido;
    }

    public void setPartido(Partidos partido) {
        this.partido = partido;
    }

    public Jugadores getJugador() {
        return jugador;
    }

    public void setJugador(Jugadores jugador) {
        this.jugador = jugador;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    @Override
    public String toString() {
        return "Goles{" +
                "idGol=" + idGol +
                ", jugador=" + (jugador != null ? jugador.getNombre() : "null") +
                ", minuto=" + minuto +
                '}';
    }
}