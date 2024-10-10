
package com.Entradas.Entradas.controller;

import com.Entradas.Entradas.model.Pago;
import com.Entradas.Entradas.service.IPagoService;
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
public class PagoController {
    @Autowired
    private IPagoService pagoServ;
    
    @PostMapping("/pago/guardar")
    public String saveEvento(@RequestBody Pago pago){
        pagoServ.savePago(pago);
        return "Pago guardado correctamente";
    }
    
    @GetMapping("/pago/traer")
    public List <Pago> getEventos(){
        return pagoServ.getPagos();
    }
    
    @GetMapping("/pago/traer/{idPago}")
    public Pago traerPagoPorId(@PathVariable Long idPago){
        return pagoServ.findById(idPago);
    }
    
    @DeleteMapping("/pago/borrar/{idPago}")
    public String deletePago(@PathVariable Long idPago){
        pagoServ.deletePago(idPago);
        return "evento eliminado correctamente";
    }
    
    @PutMapping("/pago/editar/{idPago}")
    public String editPago(@Valid @RequestBody Pago pago , @PathVariable Long idPago){
        pagoServ.editPago(pago , idPago);
        return "pago editado";
    }
}
