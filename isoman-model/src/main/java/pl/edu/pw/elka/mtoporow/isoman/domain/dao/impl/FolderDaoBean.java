package pl.edu.pw.elka.mtoporow.isoman.domain.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.objectledge.context.Context;
import pl.edu.pw.elka.mtoporow.isoman.common.util.CollectionTool;
import pl.edu.pw.elka.mtoporow.isoman.domain.dao.FolderDao;
import pl.edu.pw.elka.mtoporow.isoman.domain.entity.Folder;
import pl.edu.pw.elka.mtoporow.isoman.domain.session.SessionFactory;

import java.util.List;

/**
 * Implementacja dao encji folderu
 * Data utworzenia: 06.12.13, 17:33
 *
 * @author Michał Toporowski
 */
public class FolderDaoBean extends AbstractGenericUniqueDao<Folder, Long> implements FolderDao {

    /**
     * Konstruktor
     *
     * @param context        kontekst
     * @param sessionFactory fabryka sesji Hibernate
     */
    public FolderDaoBean(Context context, SessionFactory sessionFactory) {
        super(context, sessionFactory);
    }

    @Override
    public Folder getByFsid(Long fsid) {
        List<Folder> folders = getFiltered(Restrictions.eq("fsid", fsid));
        return CollectionTool.getFirst(folders);
    }

    @Override
    public void deleteTree(Folder folder) {
        if (!CollectionTool.containsElements(folder.getPodrzedne())) {
            delete(folder);
        } else {
            for (Folder child : folder.getPodrzedne()) {
                deleteTree(child);
            }
        }
    }
}
