package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.common.util.CollectionTool;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.ArchiwumDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Archiwum;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

import java.util.List;

/**
 * Implementacja Dao dla archiwów
 * Data utworzenia: 10.11.13, 19:14
 *
 * @author Michał Toporowski
 */
public class ArchiwumDaoBean extends AbstractGenericUniqueDao<Archiwum, Long> implements ArchiwumDao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    public ArchiwumDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }

    @Override
    public Archiwum getByRootFolder(String rootPath) {
        Criteria criteria = createCriteria();
        criteria.createAlias("folderGlowny", "folder")
                .add(Restrictions.eq("folder.nazwa", rootPath));
        List<Archiwum> archiwa = evaluateCriteria(criteria);
        return CollectionTool.getFirst(archiwa);
    }

    @Override
    public List<Archiwum> getUserArchives(final Long personId) {
        return evaluateCriteria(createUserArchivesCriteria(personId));
    }

    @Override
    public List<Archiwum> getUserArchives(final Long personId, final Long subjectId) {
        Criteria criteria = createUserArchivesCriteria(personId)
                .add(Restrictions.eq("p.id", subjectId));
        return evaluateCriteria(criteria);
    }

    /**
     * Tworzy kryteria wyszukujące archiwa użytkownika
     *
     * @param personId
     * @return
     */
    private Criteria createUserArchivesCriteria(final Long personId) {
        return createCriteria()
                .createAlias("przedmiot", "p")
                .createAlias("p.uczestnicy", "u")
                .add(Restrictions.eq("u.id", personId));
    }


}
