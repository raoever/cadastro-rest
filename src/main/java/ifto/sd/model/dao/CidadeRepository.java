/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.model.dao;

import ifto.sd.model.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rene
 */
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    
}
