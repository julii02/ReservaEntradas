
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.DataProvider;
import com.Entradas.Entradas.exceptions.EntradasException;
import com.Entradas.Entradas.model.Pago;
import com.Entradas.Entradas.repository.IEntradaRepository;
import com.Entradas.Entradas.repository.IPagoRepository;
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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {
    @Mock
    private IPagoRepository pagoRepo;

    @Mock
    private IEntradaRepository entradaRepo;

    @InjectMocks
    private PagoService pagoService;
    
    @Test
    public void testGetPagos() {
        // When
        when(pagoRepo.findAll()).thenReturn(DataProvider.listaPagosMock());

        // Act
        List<Pago> result = pagoService.getPagos();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(500L, result.get(0).getMonto());
        Assertions.assertEquals("Tarjeta de crédito", result.get(0).getMetodoPago());
    }
    
    @Test
    public void testFindById() {
        Long id = 1L;

        when(pagoRepo.findById(id)).thenReturn(Optional.of(DataProvider.pagoMock()));

        // Act
        Pago pago = pagoService.findById(id);

        // Then
        Assertions.assertNotNull(pago);
        Assertions.assertEquals(500L, pago.getMonto());
        Assertions.assertEquals("Tarjeta de crédito", pago.getMetodoPago());
    }
    
    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;

        when(pagoRepo.findById(id)).thenReturn(Optional.empty());

        EntradasException exception = Assertions.assertThrows(EntradasException.class, () -> {
            pagoService.findById(id);
        });

        Assertions.assertEquals("Pago no encontrado", exception.getMessage());
        verify(pagoRepo, times(1)).findById(id);
    }
    
    @Test
    public void testSavePago() {
        // Given
        Pago pago = DataProvider.newPagoMock();

        when(entradaRepo.findById(pago.getEntrada().getIdEntrada())).thenReturn(Optional.of(DataProvider.entradaMock()));

        // Act
        pagoService.savePago(pago);

        // Then
        ArgumentCaptor<Pago> pagoCaptor = ArgumentCaptor.forClass(Pago.class);
        verify(pagoRepo).save(pagoCaptor.capture());
        Assertions.assertEquals(3L, pagoCaptor.getValue().getId());
        Assertions.assertEquals(500L, pagoCaptor.getValue().getMonto());
    }
    
     @Test
    public void testDeletePago() {
        Long id = 1L;

        when(pagoRepo.findById(id)).thenReturn(Optional.of(DataProvider.pagoMock()));

        // Act
        pagoService.deletePago(id);

        // Then
        verify(pagoRepo).deleteById(id);
    }
    
    @Test
    public void testDeletePagoNotFound() {
        Long id = 1L;

        when(pagoRepo.findById(id)).thenReturn(Optional.empty());

        EntradasException exception = Assertions.assertThrows(EntradasException.class, () -> {
            pagoService.deletePago(id);
        });

        Assertions.assertEquals("Pago no encontrado", exception.getMessage());
        verify(pagoRepo, times(1)).findById(id);
        verify(pagoRepo, times(0)).deleteById(id);
    }
}
