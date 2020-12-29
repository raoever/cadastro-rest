/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.model.repository;

import ifto.sd.model.entity.Cidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rene
 */
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    List <Cidade> findByNome (String nome);
}
