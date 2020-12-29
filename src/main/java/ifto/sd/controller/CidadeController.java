/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.controller;

import ifto.sd.model.repository.CidadeRepository;
import ifto.sd.model.entity.Cidade;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rene
 */
@RestController
@Transactional
@RequestMapping(value="/api")
public class CidadeController {
    
    @Autowired
    CidadeRepository cidadeRepository;
    
    @GetMapping("/cidades")
    public List<Cidade> listCidades(){
        return cidadeRepository.findAll();
    }
    
}
