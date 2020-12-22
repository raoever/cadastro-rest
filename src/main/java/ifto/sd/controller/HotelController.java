/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.controller;

import ifto.sd.model.dao.BairroRepository;
import ifto.sd.model.dao.CidadeRepository;
import ifto.sd.model.dao.EstadoRepository;
import ifto.sd.model.dao.HotelDAO;
import ifto.sd.model.entity.Bairro;
import ifto.sd.model.entity.Cidade;
import ifto.sd.model.entity.Estado;
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
    
    @Autowired
    BairroRepository bairroRepository;
    
    @Autowired
    CidadeRepository cidadeRepository;
    
    @Autowired
    EstadoRepository estadoRepository;
    
    @GetMapping("/hoteis")
    public List<Hotel> listHoteis(){
        return hotelDAO.listHoteis();
    }
    
    //    consultar hotéis por nome de cidade
    @GetMapping("/hoteisNomeCidade/{cidade}")
    public List<Hotel> listHoteisNomeCidade(@PathVariable(value = "cidade") String cidade){
        List <Cidade> cidades = cidadeRepository.findByNome(cidade);
        if(cidades.size() > 1){
            throw new IllegalArgumentException("Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO.");
        } else if (cidades.size() < 1){
            throw new IllegalArgumentException("Cidade não Encontrada!");
        }
        return hotelDAO.listHoteisPorCidade(cidades.get(0).getId());
    }
    
    //    consultar hotéis por nome de cidade e estado para para cidades com nomes repetidos
    @GetMapping("/hoteisNomeCidade/{cidade}/{uf}")
    public List<Hotel> listHoteisNomeCidade(@PathVariable(value = "cidade") String cidade, @PathVariable(value = "uf") String uf){
        List <Cidade> cidades = cidadeRepository.findByNome(cidade);
        if(cidades.size() < 1){
            throw new IllegalArgumentException("Cidade não Encontrada!");
        }
        List <Estado> estados = estadoRepository.findByUf(uf);
        if(estados.size() < 1){
            throw new IllegalArgumentException("Estado não Encontrado!");
        }
        long idEstado = estados.get(0).getId();
        long idCidade = 0;
        for (Cidade c : cidades) {
            if(c.getEstado().getId() == idEstado){
                idCidade = c.getId();
            }
        }
        return hotelDAO.listHoteisPorCidade(idCidade);
    }
    
    //    consultar hotéis por id de cidade
    @GetMapping("/hotelPorIdCidade/{id}")
    public List<Hotel> listHotelPorCidade(@PathVariable(value = "id") long id) {
        return hotelDAO.listHoteisPorCidade(id);
    }
    
    //    consultar hotéis por nome de bairro de uma cidade
    @GetMapping("/hotelPorNomeCidadeBairro/{cidade}/{bairro}")
    public List<Hotel> listHotelPorCidadeBairro(@PathVariable(value = "cidade") String cidade, @PathVariable(value = "bairro") String bairro) {
        List <Cidade> cidades = cidadeRepository.findByNome(cidade);
        if(cidades.size() > 1){
            throw new IllegalArgumentException("Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO/Plano%20Diretor%20Norte.");
        } else if (cidades.size() < 1){
            throw new IllegalArgumentException("Cidade não Encontrada!");
        }
        long idCidade = cidades.get(0).getId();
        List <Bairro> bairros = bairroRepository.findByNome(bairro);
        if(bairros.size() < 1){
            throw new IllegalArgumentException("Bairro não Encontrado!");
        }
        long idBairro = bairros.get(0).getId();
        return hotelDAO.listHoteisCidadeBairro(idCidade, idBairro);
    }
    
    //    consultar hotéis por nome de bairro de uma cidade e estado para cidades com nomes repetidos
    @GetMapping("/hotelPorNomeCidadeBairro/{cidade}/{uf}/{bairro}")
    public List<Hotel> listHotelPorCidadeBairro(@PathVariable(value = "cidade") String cidade, @PathVariable(value = "uf") String uf, @PathVariable(value = "bairro") String bairro) {
        List <Cidade> cidades = cidadeRepository.findByNome(cidade);
        if (cidades.size() < 1){
            throw new IllegalArgumentException("Cidade não Encontrada!");
        }
        List <Estado> estados = estadoRepository.findByUf(uf);
        if(estados.size() < 1){
            throw new IllegalArgumentException("Estado não Encontrado!");
        }
        long idEstado = estados.get(0).getId();
        long idCidade = 0;
        for (Cidade c : cidades) {
            if(c.getEstado().getId() == idEstado){
                idCidade = c.getId();
            }
        }
        List <Bairro> bairros = bairroRepository.findByNome(bairro);
        if(bairros.size() < 1){
            throw new IllegalArgumentException("Bairro não Encontrado!");
        }
        long idBairro = bairros.get(0).getId();
        return hotelDAO.listHoteisCidadeBairro(idCidade, idBairro);
    }
    
    //    consultar hotéis por Id bairro de uma Id cidade
    @GetMapping("/hotelPorIdCidadeIdBairro/{id1}/{id2}")
    public List<Hotel> listHotelPorIdCidadeBairro(@PathVariable long id1, @PathVariable long id2) {
        return hotelDAO.listHoteisCidadeBairro(id1, id2);
    }
    
    //    consultar hotéis por cidade, faixa de preço e total de camas
    @GetMapping("/hoteisCidadeFaixaCamas/{cidade}/{preco1}/{preco2}/{camas}")
    public Map<String, Quarto> listHotelPorNomeCidadeFaixaCamas(@PathVariable(value = "cidade") String cidade, @PathVariable(value = "preco1") double preco1, @PathVariable(value = "preco2") double preco2, @PathVariable(value = "camas") int camas) {
        List <Cidade> cidades = cidadeRepository.findByNome(cidade);
        if(cidades.size() > 1){
            throw new IllegalArgumentException("Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO/100/300/2.");
        } else if (cidades.size() < 1){
            throw new IllegalArgumentException("Cidade não Encontrada!");
        }
        long idCidade = cidades.get(0).getId();
        return hotelDAO.listHoteisCidadeFaixaCamas(idCidade, preco1, preco2, camas);
    }
    
    //    consultar hotéis por cidade, uf, faixa de preço e total de camas
    @GetMapping("/hoteisCidadeFaixaCamas/{cidade}/{uf}/{preco1}/{preco2}/{camas}")
    public Map<String, Quarto> listHotelPorCidadeEstadoFaixaCamas(@PathVariable(value = "cidade") String cidade, @PathVariable(value = "uf") String uf, @PathVariable(value = "preco1") double preco1, @PathVariable(value = "preco2") double preco2, @PathVariable(value = "camas") int camas) {
         List <Cidade> cidades = cidadeRepository.findByNome(cidade);
        if (cidades.size() < 1){
            throw new IllegalArgumentException("Cidade não Encontrada!");
        }
        List <Estado> estados = estadoRepository.findByUf(uf);
        if(estados.size() < 1){
            throw new IllegalArgumentException("Estado não Encontrado!");
        }
        long idEstado = estados.get(0).getId();
        long idCidade = 0;
        for (Cidade c : cidades) {
            if(c.getEstado().getId() == idEstado){
                idCidade = c.getId();
            }
        }
        return hotelDAO.listHoteisCidadeFaixaCamas(idCidade, preco1, preco2, camas);
    }

    //    consultar hotéis por Id cidade, faixa de preço e total de camas
    @GetMapping("/hoteisIdCidadeFaixaCamas/{id}/{preco1}/{preco2}/{camas}")
    public Map<String, Quarto> listHotelPorIdCidadeFaixaCamas(@PathVariable(value = "id") long id, @PathVariable(value = "preco1") double preco1, @PathVariable(value = "preco2") double preco2, @PathVariable(value = "camas") int camas) {
        return hotelDAO.listHoteisCidadeFaixaCamas(id, preco1, preco2, camas);
    }
    
}