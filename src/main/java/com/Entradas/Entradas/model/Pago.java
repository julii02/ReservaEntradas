
package com.Entradas.Entradas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto del pago es obligatorio")
    private Long monto;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDateTime fechaPago;

    @NotNull(message = "El método de pago es obligatorio")
    private String metodoPago; // Puede ser: Tarjeta de crédito, PayPal, etc.
     
    @OneToOne
    @JoinColumn(name = "entrada_id")
    @Valid
    private Entrada entrada; // Relación con la entrada comprada

    public Pago() {
    }

    public Pago(Long id, Long monto, LocalDateTime fechaPago, String metodoPago, Entrada entrada) {
        this.id = id;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.entrada = entrada;
    }
      
}
