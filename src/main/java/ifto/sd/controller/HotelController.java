/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.controller;

import ifto.sd.model.dao.HotelDAO;
import ifto.sd.model.entity.Hotel;
import ifto.sd.model.entity.Quarto;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rene
 */
@RestController
@Transactional
@RequestMapping(value = "/api")
public class HotelController {

    @Autowired
    HotelDAO hotelDAO;

    @GetMapping("/hoteis")
    public List<Hotel> listHoteis(){
        return hotelDAO.listHoteis();
    }
    
    @GetMapping("/hotelPorCidade/{id}")
    public List<Hotel> listHotelPorCidade(@PathVariable(value = "id") long id) {
        return hotelDAO.listHoteisPorCidade(id);
    }
    
    @GetMapping("/hotelPorCidadeBairro/{id1}/{id2}")
    public List<Hotel> listHotelPorCidadeBairro(@PathVariable long id1, @PathVariable long id2) {
        return hotelDAO.listHoteisCidadeBairro(id1, id2);
    }
    
    @GetMapping("/hoteisCidadeFaixaCamas/{id1}/{id2}/{id3}/{id4}")
    public Map<String, Quarto> listHotelPorCidadeFaixaCamas(@PathVariable long id1, @PathVariable double id2, @PathVariable double id3, @PathVariable int id4) {
        return hotelDAO.listHoteisCidadeFaixaCamas(id1, id2, id3, id4);
    }

}