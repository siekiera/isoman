package pl.edu.pw.elka.mtoporow.isoman.domain.dao;

import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Osoba;

import java.util.List;

/**
 * Dao dla osoby
 *
 * @author Michał Toporowski
 */
public interface OsobaDao extends BaseUniqueDao<Osoba, Long> {
    public Osoba getById(Long id);

    public List<Osoba> getAllOsobas();


    public void deleteById(Long id);

}
