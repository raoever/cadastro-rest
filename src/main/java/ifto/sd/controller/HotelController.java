/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.controller;

import ifto.sd.model.repository.BairroRepository;
import ifto.sd.model.repository.CidadeRepository;
import ifto.sd.model.repository.EstadoRepository;
import ifto.sd.model.repository.HotelRepository;
import ifto.sd.model.entity.Bairro;
import ifto.sd.model.entity.Cidade;
import ifto.sd.model.entity.Estado;
import ifto.sd.model.entity.Hotel;
import ifto.sd.model.entity.Quarto;
import java.util.List;
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
@RequestMapping(value = "/hotel")
public class HotelController {

    @Autowired
    HotelRepository repository;
    
    @Autowired
    BairroRepository bairroRepository;
    
    @Autowired
    CidadeRepository cidadeRepository;
    
    @Autowired
    EstadoRepository estadoRepository;
    
    @GetMapping("/todos")
    public List<Hotel> listHoteis(){
        return repository.listHoteis();
    }
    
    //    consultar hotéis por nome de cidade
    @GetMapping("/NomeCidade/{cidade}")
    public List<Hotel> listHoteisNomeCidade(@PathVariable(value = "cidade") String cidade){
        List <Cidade> cidades = cidadeRepository.findByNome(cidade);
        if(cidades.size() > 1){
            throw new IllegalArgumentException("Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO.");
        } else if (cidades.size() < 1){
            throw new IllegalArgumentException("Cidade não Encontrada!");
        }
        return repository.listHoteisPorCidade(cidades.get(0).getId());
    }
    
    //    consultar hotéis por nome de cidade e estado para para cidades com nomes repetidos
    @GetMapping("/NomeCidade/{cidade}/{uf}")
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
        return repository.listHoteisPorCidade(idCidade);
    }
    
    //    consultar hotéis por id de cidade
    @GetMapping("/PorIdCidade/{id}")
    public List<Hotel> listHotelPorCidade(@PathVariable(value = "id") long id) {
        return repository.listHoteisPorCidade(id);
    }
    
    //    consultar hotéis por nome de bairro de uma cidade
    @GetMapping("/PorNomeCidadeBairro/{cidade}/{bairro}")
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
        return repository.listHoteisCidadeBairro(idCidade, idBairro);
    }
    
    //    consultar hotéis por nome de bairro de uma cidade e estado para cidades com nomes repetidos
    @GetMapping("/PorNomeCidadeBairro/{cidade}/{uf}/{bairro}")
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
        return repository.listHoteisCidadeBairro(idCidade, idBairro);
    }
    
    //    consultar hotéis por Id bairro de uma Id cidade
    @GetMapping("/PorIdCidadeIdBairro/{id1}/{id2}")
    public List<Hotel> listHotelPorIdCidadeBairro(@PathVariable long id1, @PathVariable long id2) {
        return repository.listHoteisCidadeBairro(id1, id2);
    }
    
    //    consultar hotéis por cidade, faixa de preço e total de camas
    @GetMapping("/CidadeFaixaCamas/{cidade}/{preco1}/{preco2}/{camas}")
    public List<Quarto> listHotelPorNomeCidadeFaixaCamas(@PathVariable(value = "cidade") String cidade, @PathVariable(value = "preco1") double preco1, @PathVariable(value = "preco2") double preco2, @PathVariable(value = "camas") int camas) {
        List <Cidade> cidades = cidadeRepository.findByNome(cidade);
        if(cidades.size() > 1){
            throw new IllegalArgumentException("Achado mais de uma cidade com o nome indicado, Por favor entre como nome da cidade + UF - ex.: Palmas/TO/100/300/2.");
        } else if (cidades.size() < 1){
            throw new IllegalArgumentException("Cidade não Encontrada!");
        }
        long idCidade = cidades.get(0).getId();
        return repository.listHoteisCidadeFaixaCamas(idCidade, preco1, preco2, camas);
    }
    
    //    consultar hotéis por cidade, uf, faixa de preço e total de camas
    @GetMapping("/CidadeFaixaCamas/{cidade}/{uf}/{preco1}/{preco2}/{camas}")
    public List<Quarto> listHotelPorCidadeEstadoFaixaCamas(@PathVariable(value = "cidade") String cidade, @PathVariable(value = "uf") String uf, @PathVariable(value = "preco1") double preco1, @PathVariable(value = "preco2") double preco2, @PathVariable(value = "camas") int camas) {
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
        return repository.listHoteisCidadeFaixaCamas(idCidade, preco1, preco2, camas);
    }

    //    consultar hotéis por Id cidade, faixa de preço e total de camas
    @GetMapping("/IdCidadeFaixaCamas/{id}/{preco1}/{preco2}/{camas}")
    public List<Quarto> listHotelPorIdCidadeFaixaCamas(@PathVariable(value = "id") long id, @PathVariable(value = "preco1") double preco1, @PathVariable(value = "preco2") double preco2, @PathVariable(value = "camas") int camas) {
        return repository.listHoteisCidadeFaixaCamas(id, preco1, preco2, camas);
    }
    
}