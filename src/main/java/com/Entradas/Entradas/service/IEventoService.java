
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.model.Evento;
import java.util.List;


public interface IEventoService {
    
    public List <Evento> getEventos();
    
    public void saveEvento(Evento evento);
    
    public Evento findById(Long id);
    
    public void deleteEvento(Long id);
    
    public void editEvento(Evento evento , Long id);
}
