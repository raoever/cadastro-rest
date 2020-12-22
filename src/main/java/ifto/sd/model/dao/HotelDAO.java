/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifto.sd.model.dao;

import ifto.sd.model.entity.Cidade;
import ifto.sd.model.entity.Estado;
import ifto.sd.model.entity.Hotel;
import ifto.sd.model.entity.Quarto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rene
 */
@Repository
public class HotelDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Hotel> listHoteis() {
        Query query = em.createQuery("from Hotel");
        return query.getResultList();
    }
    
    public List<Hotel> listHoteisPorCidade(long id) {
        Query query = em.createQuery("from Hotel h where h.bairro.cidade.id = ?1");
        query.setParameter(1, id);
        return query.getResultList();
    }

    public List<Hotel> listHoteisCidadeBairro(long id1, long id2) {
        Query query = em.createQuery("from Hotel h where h.bairro.cidade.id = ?1 and h.bairro.id =?2");
        query.setParameter(1, id1);
        query.setParameter(2, id2);
        return query.getResultList();
    }

    public Map<String, Quarto> listHoteisCidadeFaixaCamas(long id1, double id2, double id3, int id4) {
        if (id2 < id3) {
            double temp = id2;
            id2 = id3;
            id3 = temp;
        }
        Map<String, Quarto> hoteisSorted = new HashMap<>();
        List<Quarto> quartos = listQuartosCidade(id1);
        for (Quarto q : quartos) {
            if (q.getNumeroCamas() == id4 && q.getPreco() < id2 && q.getPreco() > id3) {
                hoteisSorted.put(q.getHotel().getNome(), q);
            }
        }
        return hoteisSorted;
    }

    public List<Quarto> listQuartosCidade(long id1) {
        Query query = em.createQuery("from Quarto q where q.hotel.bairro.cidade.id = ?1");
        query.setParameter(1, id1);
        return query.getResultList();
    }

}
