
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.model.Entrada;
import java.util.List;

public interface IEntradaService {
    
    public void saveEntrada(Entrada entrada);
    
    public List <Entrada> getEntradas();
    
    public void deleteEntrada(Long id);
    
    public Entrada findById(Long id);
    
    public void editEntrada(Entrada entrada , Long id);
}
