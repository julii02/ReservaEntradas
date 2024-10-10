
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.DataProvider;
import com.Entradas.Entradas.exceptions.EntradasException;
import com.Entradas.Entradas.model.Entrada;
import com.Entradas.Entradas.model.Evento;
import com.Entradas.Entradas.repository.IEntradaRepository;
import com.Entradas.Entradas.repository.IEventoRepository;
import com.Entradas.Entradas.repository.IPagoRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EntradaServiceTest {
    @Mock
    private IEntradaRepository entradaRepo;

    @Mock
    private IEventoRepository eventoRepo;

    @Mock
    private IPagoRepository pagoRepo;

    @InjectMocks
    private EntradaService entradaService;
    
     @Test
    public void testSaveEntrada() {
        // Given
        Entrada entrada = DataProvider.entradaMock();
        Evento evento = DataProvider.eventoMock();
        when(eventoRepo.findById(anyLong() )).thenReturn(Optional.of(evento));
        when(entradaRepo.save(any (Entrada.class))).thenReturn(entrada);

        // When
        entradaService.saveEntrada(entrada);

        // Then
        ArgumentCaptor<Entrada> entradaCaptor = ArgumentCaptor.forClass(Entrada.class);
        verify(entradaRepo).save(entradaCaptor.capture());
        Assertions.assertEquals(entrada.getCategoriaEntrada(), entradaCaptor.getValue().getCategoriaEntrada());
        Assertions.assertEquals(evento.getCapacidadDisponible(), 499); // Se resta la capacidad
    }
    
    @Test
    public void testGetEntradas() {
        // Given
        when(entradaRepo.findAll()).thenReturn(DataProvider.listaEntradasMock());

        // When
        List<Entrada> result = entradaService.getEntradas();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("General" , result.get(0).getCategoriaEntrada());
    }
    
    @Test
    public void testFindById() {
        // Given
        Entrada entrada = DataProvider.entradaMock();
        when(entradaRepo.findById(anyLong())).thenReturn(Optional.of(entrada));

        // When
        Entrada result = entradaService.findById(1L);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getIdEntrada());
    }
    // Test cuando no se encuentra el evento
    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;

        when(entradaRepo.findById(id)).thenReturn(Optional.empty());

        EntradasException exception = Assertions.assertThrows(EntradasException.class, () -> {
            entradaService.findById(id);
        });

        Assertions.assertEquals("Entrada no encontrada", exception.getMessage());
        verify(entradaRepo, times(1)).findById(id);
    }
    
    /*
    @Test
    public void testDeleteEntrada() {
        // Given
        Entrada entrada = DataProvider.entradaMock();
        Evento evento = entrada.getEvento();
        when(entradaRepo.findById(anyLong())).thenReturn(Optional.of(entrada));
        when(pagoRepo.findAll()).thenReturn(DataProvider.listaPagosMock());
        doNothing().when(pagoRepo).deleteById(anyLong());

        // When
        entradaService.deleteEntrada(1L);

        // Then
        verify(entradaRepo).deleteById(1L);
        verify(eventoRepo).save(evento);
        Assertions.assertEquals(evento.getCapacidadDisponible(), 501); // Se incrementa la capacidad
    }*/
    @Test
    public void testEditEntrada() {
        // Given
        Entrada entrada = DataProvider.entradaMock();
        when(entradaRepo.findById(anyLong())).thenReturn(Optional.of(entrada));
        when(entradaRepo.save(any(Entrada.class))).thenReturn(entrada);

        // When
        entradaService.editEntrada(DataProvider.newEntradaMock(), 1L);

        // Then
        ArgumentCaptor<Entrada> entradaCaptor = ArgumentCaptor.forClass(Entrada.class);
        verify(entradaRepo).save(entradaCaptor.capture());
        Assertions.assertEquals("VIP", entradaCaptor.getValue().getCategoriaEntrada()); // Asegura que se actualizó
    }
    
}
