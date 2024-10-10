
package com.Entradas.Entradas.controller;

import com.Entradas.Entradas.model.Usuario;
import com.Entradas.Entradas.service.IUsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioServ;
    
    @PostMapping("/usuario/guardar")
    public String saveUsuario(@Valid @RequestBody Usuario usuario){
        usuarioServ.saveUsuario(usuario);
        return "Usuario guardado correctamente";
    }
    
    @GetMapping("/usuario/traer")
    public List <Usuario> getUsuarios(){
        return usuarioServ.getUsuarios();
    }
    
    @GetMapping("/usuario/traer/{idUsuario}")
    public Usuario traerUsuarioPorId(@PathVariable Long idUsuario){
        return usuarioServ.findById(idUsuario);
    }
    
    @DeleteMapping("/usuario/borrar/{idUsuario}")
    public String deleteUsuario(@PathVariable Long idUsuario){
        usuarioServ.deleteUsuario(idUsuario);
        return "usuario eliminado correctamente";
    }
    
    @PutMapping("/usuario/editar/{idUsuario}")
    public String editUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long idUsuario){
        usuarioServ.editUsuario(usuario , idUsuario);
        return "Usuario editado correctamente";
        
    }
    
}
