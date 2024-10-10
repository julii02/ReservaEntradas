
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.model.Usuario;
import java.util.List;


public interface IUsuarioService {
    
    public void saveUsuario(Usuario usuario);
    
    public List <Usuario> getUsuarios();
    
    public void deleteUsuario(Long id);
    
    public Usuario findById(Long id);
    
    public void editUsuario(Usuario usuario , Long id);   
    
}
