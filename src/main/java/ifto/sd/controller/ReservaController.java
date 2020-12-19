/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.controller;

import ifto.sd.model.dao.QuartoRepository;
import ifto.sd.model.dao.ReservaRepository;
import ifto.sd.model.entity.Quarto;
import ifto.sd.model.entity.Reserva;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map<String, Reserva> listReservas(){
        Map <String, Reserva> reservas = new HashMap<>();
        List<Reserva> res = reservaRepository.findAll();
        for(Reserva r : res){
            reservas.put("Hotel: " + r.getQuarto().getHotel().getNome()+
                         " Quarto n: " + r.getQuarto().getNumero()+
                         " Preço: R$ " + r.getQuarto().getPreco(), r);
        }
        return reservas;
    }
    
//    cadastrar reserva de quarto
    @PostMapping("/reserva")
    public Map<String, Reserva> salvaReserva(@RequestBody Reserva reserva){
//        System.out.println(reserva.toString());
        Map<String, Reserva>  resposta = new HashMap<>();
        Quarto quarto = quartoRepository.findById(reserva.getQuarto().getId());
        reserva.setQuarto(quarto);
        Reserva res = reservaRepository.save(reserva);
        resposta.put("Id quarto: "+res.getQuarto().getId(), res);
        return resposta;
    }
    
//    alterar reserva de quarto
    @PutMapping("/reserva")
    public Map<String, Reserva> alteraReserva(@RequestBody Reserva reserva){
        Map<String, Reserva>  resposta = new HashMap<>();
        Quarto quarto = quartoRepository.findById(reserva.getQuarto().getId());
        reserva.setQuarto(quarto);
        Reserva res = reservaRepository.save(reserva);
        resposta.put("Id quarto: "+res.getQuarto().getId(), res);
        return resposta;
    }
    
    
//    excluir reserva de quarto
    @DeleteMapping("/reserva/{id}")
    public void exclueReserva(@PathVariable(value = "id") long id){
        reservaRepository.deleteById(id);
    }
}
