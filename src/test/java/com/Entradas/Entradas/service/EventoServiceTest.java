
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.DataProvider;
import com.Entradas.Entradas.exceptions.EntradasException;
import com.Entradas.Entradas.model.Evento;
import com.Entradas.Entradas.repository.IEventoRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest @ExtendWith(MockitoExtension.class)
public class EventoServiceTest {
    @Mock
    private IEventoRepository eventoRepo;

    @InjectMocks
    private EventoService eventoService;
    
     @Test
    public void testGetEventos() {
        // When
        when(eventoRepo.findAll()).thenReturn(DataProvider.listaEventosMock());
        
        // Act
        List<Evento> result = eventoService.getEventos();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("Concierto", result.get(0).getNombre());
        Assertions.assertEquals("Feria", result.get(1).getNombre());
    }
    
    @Test
    public void testFindById() {
        Long id = 1L;

        when(eventoRepo.findById(id)).thenReturn(Optional.of(DataProvider.eventoMock()));
        
        // Act
        Evento evento = eventoService.findById(id);

        // Then
        Assertions.assertNotNull(evento);
        Assertions.assertEquals("Concierto", evento.getNombre());
    }
    
    // Test cuando no se encuentra el evento
    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;

        when(eventoRepo.findById(id)).thenReturn(Optional.empty());

        EntradasException exception = Assertions.assertThrows(EntradasException.class, () -> {
            eventoService.findById(id);
        });

        Assertions.assertEquals("evento no encontrado", exception.getMessage());
        verify(eventoRepo, times(1)).findById(id);
    }
    
    @Test
    public void testSaveEvento() {
        // Given
        Evento evento = DataProvider.newEventoMock();

        // Act
        eventoService.saveEvento(evento);

        // Then
        ArgumentCaptor<Evento> eventoCaptor = ArgumentCaptor.forClass(Evento.class);
        verify(this.eventoRepo).save(eventoCaptor.capture());
        Assertions.assertEquals(3L, eventoCaptor.getValue().getIdEvento());
        Assertions.assertEquals("Teatro", eventoCaptor.getValue().getNombre());
    }
    
    // Test para deleteEvento
    @Test
    public void testDeleteEvento() {
        Long id = 1L;

        when(eventoRepo.findById(id)).thenReturn(Optional.of(DataProvider.eventoMock()));

        // Act
        eventoService.deleteEvento(id);

        // Then
        verify(eventoRepo).deleteById(id);
    }    
    
     @Test
    public void testDeleteEventoNotFound() {
        Long id = 1L;

        when(eventoRepo.findById(id)).thenReturn(Optional.empty());

        EntradasException exception = Assertions.assertThrows(EntradasException.class, () -> {
            eventoService.deleteEvento(id);
        });

        Assertions.assertEquals("evento no encontrado", exception.getMessage());
        verify(eventoRepo, times(1)).findById(id);
        verify(eventoRepo, times(0)).deleteById(id);
    }
}    