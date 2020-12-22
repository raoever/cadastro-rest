/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.model.dao;

import ifto.sd.model.entity.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rene
 */
public interface EstadoRepository extends JpaRepository<Estado, Long>{
    List <Estado> findByUf (String nome);
}
