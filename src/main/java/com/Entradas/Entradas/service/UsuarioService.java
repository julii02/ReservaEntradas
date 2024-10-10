
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.exceptions.EntradasException;
import com.Entradas.Entradas.exceptions.ExceptionDetails;
import com.Entradas.Entradas.model.Usuario;
import com.Entradas.Entradas.repository.IUsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository userRepo;
    
    @Override
    public void saveUsuario(Usuario usuario) {
        try{
            userRepo.save(usuario);
        }
        catch(Exception e){
             throw new EntradasException("Error al guardar el usuario" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") , e);
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        List <Usuario> listaUsuarios = userRepo.findAll();
        
        if(listaUsuarios == null){
            throw new EntradasException("La lista de usuarios esta vacia" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error"));
        }
        return listaUsuarios;
    }

    @Override
    public void deleteUsuario(Long id) {
        this.findById(id);
        userRepo.deleteById(id);
    }

    @Override
    public Usuario findById(Long id) {
        Usuario user = userRepo.findById(id).orElse(null);
        if(user == null){
            throw new EntradasException("Usuario no encontrado" , 
                     new ExceptionDetails("La ID ingresada no existe","Error") );
        }
        return user;
    }

    @Override
    public void editUsuario(Usuario usuario , Long id) {
        Usuario user = this.findById(id);
        
        user.setApellido(usuario.getApellido());
        user.setEmail(usuario.getEmail());
        user.setNombre(usuario.getNombre());
        user.setTelefono(usuario.getTelefono());
        
        try{
            userRepo.save(user);
        }  
        catch(Exception e){
            throw new EntradasException("Error al guardar el usuario" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") , e);
        }
    }
    
}
