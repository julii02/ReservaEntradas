
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.DataProvider;
import com.Entradas.Entradas.model.Usuario;
import com.Entradas.Entradas.repository.IUsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest @ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    
    @Mock
    private IUsuarioRepository userRepo;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    
    @Test
    public void testGetUsuarios(){
        //When
        when(userRepo.findAll()).thenReturn(DataProvider.listaUsuariosMock());
        List <Usuario> result = usuarioService.getUsuarios();
        //Then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("Julian" , result.get(0).getNombre());
        Assertions.assertEquals("Gonzalez" , result.get(0).getApellido());        
    }
    
    @Test
    public void testFindById(){
        Long id = 1L;
        
        when(userRepo.findById(1L)).thenReturn(Optional.of(DataProvider.usuarioMock()));
        Usuario usuario = usuarioService.findById(id);
        
        Assertions.assertNotNull(usuario);
        Assertions.assertEquals("Julian" , usuario.getNombre());
        Assertions.assertEquals("Gonzalez" , usuario.getApellido()); 
    }
    
    
    
    @Test
    public void testSaveUsuario(){
        //given
        Usuario usuario = DataProvider.newUsuarioMock();
        
        //when
        usuarioService.saveUsuario(usuario);
        
        //then
        ArgumentCaptor<Usuario> usuarioCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(this.userRepo).save(usuarioCaptor.capture());
        Assertions.assertEquals(7L , usuarioCaptor.getValue().getIdUsuario());
        Assertions.assertEquals("Lionel" , usuarioCaptor.getValue().getNombre());
    }
    
    @Test
    void testDeleteById(){
        Long id = 1L;

        when(userRepo.findById(id)).thenReturn(Optional.of(DataProvider.usuarioMock()));

        // Act
        usuarioService.deleteUsuario(id);

        // Then
        verify(userRepo).deleteById(id);
    }
}
