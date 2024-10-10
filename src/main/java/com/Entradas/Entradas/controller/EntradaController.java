package com.Entradas.Entradas.controller;

import com.Entradas.Entradas.model.Entrada;
import com.Entradas.Entradas.service.IEntradaService;
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
public class EntradaController {
    @Autowired
    private IEntradaService entradaServ;
    
    @PostMapping("/entrada/guardar")//agregar que se aumente el espacio ocupado del espacio
    public String saveEntrada(@RequestBody Entrada entrada){
        entradaServ.saveEntrada(entrada);
        return "Entrada guardado correctamente";
    }
    
    @GetMapping("/entrada/traer")
    public List <Entrada> getEntrada(){
        return entradaServ.getEntradas();
    }
    
    @GetMapping("/entrada/traer/{idEntrada}")
    public Entrada traerEntradaPorId(@PathVariable Long idEntrada){
        return entradaServ.findById(idEntrada);
    }
    
    @DeleteMapping("/entrada/borrar/{idEntrada}")
    public String deleteEntrada(@PathVariable Long idEntrada){
        entradaServ.deleteEntrada(idEntrada);
        return "entrada eliminado correctamente";
    }
    
    @PutMapping("/entrada/editar/{idEntrada}")
    public void editEntrada(@RequestBody Entrada entrada , @PathVariable Long idEntrada){
        entradaServ.editEntrada(entrada , idEntrada);   
    }
}
