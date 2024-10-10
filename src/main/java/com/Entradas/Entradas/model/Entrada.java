package com.Entradas.Entradas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrada;

    @NotNull(message = "El precio es obligatorio")
    private Long precio;
    
    @NotNull(message = "La categoría de la entrada es obligatoria")
    private String categoriaEntrada;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    @Valid
    private Evento evento; // Relación con el evento al que pertenece

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @Valid
    private Usuario usuario; // Usuario que compró la entrada

    public Entrada() {
    }

    public Entrada(Long idEntrada, Long precio, String categoriaEntrada, Evento evento, Usuario usuario) {
        this.idEntrada = idEntrada;
        this.precio = precio;
        this.categoriaEntrada = categoriaEntrada;
        this.evento = evento;
        this.usuario = usuario;
    }

    
}
