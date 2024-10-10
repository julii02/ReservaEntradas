
package com.Entradas.Entradas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @NotNull(message = "El nombre del evento no puede estar vacío")
    @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La descripción no puede estar vacía")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String descripcion;
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    @Future(message = "La fecha de inicio debe ser en el futuro")
    private LocalDateTime fechaInicio;
    
    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDateTime fechaFin;
    
    @NotNull(message = "La capacidad total es obligatoria")
    private Integer capacidadTotal; // Total de entradas disponibles
    
    @NotNull(message = "La capacidad disponible es obligatoria")
    private Integer capacidadDisponible; // Entradas restantes

    public Evento() {
    }

    public Evento(Long idEvento, String nombre, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, Integer capacidadTotal, Integer capacidadDisponible) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.capacidadTotal = capacidadTotal;
        this.capacidadDisponible = capacidadDisponible;
    }
    
    
}
