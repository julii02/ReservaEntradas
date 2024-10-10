
package com.Entradas.Entradas;

import com.Entradas.Entradas.model.Entrada;
import com.Entradas.Entradas.model.Evento;
import com.Entradas.Entradas.model.Pago;
import com.Entradas.Entradas.model.Usuario;
import java.time.LocalDateTime;
import java.util.List;


public class DataProvider {
    
    public static List <Usuario> listaUsuariosMock(){
        
        return List.of(
                new Usuario(1L , "Julian" , "Gonzalez" , "juli@gmail.com" , "1123936055"),
                new Usuario(2L , "Pepe" , "Lopez" , "pepe@gmail.com" , "123456789")
        );
    }
    
    public static Usuario usuarioMock(){
        return new Usuario(1L , "Julian" , "Gonzalez" , "juli@gmail.com" , "1123936055");
    }
    
    public static Usuario newUsuarioMock(){
        return new Usuario(7L , "Lionel" , "Messi" , "juli@gmail.com" , "1123936055");
    }
    
    // Nueva lista de eventos mock
    public static List<Evento> listaEventosMock() {
        return List.of(
            new Evento(1L, "Concierto", "Un concierto de música en vivo",
                LocalDateTime.now().plusDays(10), 
                LocalDateTime.now().plusDays(11), 500, 500),
                
            new Evento(2L, "Feria", "Una feria de comida y bebidas",
                LocalDateTime.now().plusDays(15),
                LocalDateTime.now().plusDays(16), 300, 300)
        );
    }
    public static Evento eventoMock() {
        return new Evento(1L, "Concierto", "Un concierto de música en vivo",
            LocalDateTime.now().plusDays(10), 
            LocalDateTime.now().plusDays(11), 500, 500);
    }
    
    public static Evento newEventoMock() {
        return new Evento(3L, "Teatro", "Una obra de teatro",
            LocalDateTime.now().plusDays(20), 
            LocalDateTime.now().plusDays(21), 200, 200);
    }
    
     public static Entrada entradaMock() {
        return new Entrada(1L, 500L, "General", eventoMock(), usuarioMock());
    }

    public static Entrada newEntradaMock() {
        return new Entrada(1L, 1000L, "VIP", eventoMock(), usuarioMock());
    }

    public static List<Entrada> listaEntradasMock() {
        return List.of(
                new Entrada(1L, 500L, "General", eventoMock(), usuarioMock()),
                new Entrada(2L, 1000L, "VIP", eventoMock(), usuarioMock())
        );
    }
    
     public static List<Pago> listaPagosMock() {
        return List.of(
            new Pago(1L, 500L, LocalDateTime.now(), "Tarjeta de crédito", entradaMock()),
            new Pago(2L, 1000L, LocalDateTime.now(), "PayPal", entradaMock())
        );
    }

    public static Pago pagoMock() {
        return new Pago(1L, 500L, LocalDateTime.now(), "Tarjeta de crédito", entradaMock());
    }

    public static Pago newPagoMock() {
        return new Pago(3L, 500L, LocalDateTime.now(), "Tarjeta de crédito", entradaMock());
    }
    
}
