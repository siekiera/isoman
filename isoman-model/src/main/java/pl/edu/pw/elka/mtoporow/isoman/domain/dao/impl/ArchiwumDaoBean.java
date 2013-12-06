package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
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
        List<Archiwum> archiwa = criteria.list();
        return CollectionTool.getFirst(archiwa);
    }
}
