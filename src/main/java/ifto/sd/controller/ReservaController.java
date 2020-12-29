/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.controller;

import ifto.sd.model.repository.QuartoRepository;
import ifto.sd.model.repository.ReservaRepository;
import ifto.sd.model.entity.Quarto;
import ifto.sd.model.entity.Reserva;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rene
 */
@RestController
@RequestMapping(value = "/api")
public class ReservaController {
    @Autowired
    QuartoRepository quartoRepository;
    
    @Autowired
    ReservaRepository reservaRepository;

    @GetMapping("/reservas")
    public List<Reserva> listReservas(){
        List reservas = reservaRepository.findAll();
        for (Object reserva : reservas) {
            System.out.println(reserva);
        }
        return reservas;
    }
    
//    cadastrar reserva de quarto
    @PostMapping("/reserva")
    public Reserva salvaReserva(@RequestBody Reserva reserva){
        Quarto quarto = quartoRepository.findById(reserva.getQuarto().getId());
        reserva.setQuarto(quarto);
        Reserva res = reservaRepository.save(reserva);
        System.out.println(res);
        return res;
    }
    
//    alterar reserva de quarto
    @PutMapping("/reserva")
    public Reserva alteraReserva(@RequestBody Reserva reserva){
        Quarto quarto = quartoRepository.findById(reserva.getQuarto().getId());
        reserva.setQuarto(quarto);
        Reserva res = reservaRepository.save(reserva);
        System.out.println(res);
        return res;
    }
    
    
//    excluir reserva de quarto
    @DeleteMapping("/reserva/{id}")
    public void exclueReserva(@PathVariable(value = "id") long id){
        reservaRepository.deleteById(id);
    }
}
