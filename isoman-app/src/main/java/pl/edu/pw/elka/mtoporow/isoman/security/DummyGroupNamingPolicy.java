package pl.edu.pw.elka.mtoporow.isoman.security;

import org.objectledge.security.GroupNamingPolicy;
import org.objectledge.security.object.immutable.ImmutableGroup;
import org.objectledge.security.object.interfaces.SecurityGroupRO;
import org.objectledge.security.util.ImmutableSecuritySet;
import org.objectledge.security.util.SecuritySetRO;

/**
 * Example implementation of {@link GroupNamingPolicy} interface.
 * <p/>
 * <p>Created on 2009-03-24</p>
 *
 * @author <a href="mailto:mgolebsk@elka.pw.edu.pl">Marcin Golebski</a>
 * @version $Id: DummyGroupNamingPolicy.java,v 1.2 2013-07-02 13:19:07 mgolebsk Exp $
 */
public class DummyGroupNamingPolicy implements GroupNamingPolicy {
    /**
     * Groups can be delcared dynamicaly using some data sources eg. database
     * here is an example only.
     */
    private final static SecuritySetRO<SecurityGroupRO> ALL_GROUPS = new ImmutableSecuritySet<SecurityGroupRO>(
            new ImmutableGroup(null, "office", "Resources belong to the office", false, true),
            new ImmutableGroup(null, "factory", "Resources belong to the factory", false, true),
            new ImmutableGroup(null, "hr", "Resources belong to the human resources department", false, true)
    );

    @Override
    public SecuritySetRO<SecurityGroupRO> getAllGroups() {
        return ALL_GROUPS;
    }

    @Override
    @SuppressWarnings("unused")
    public SecuritySetRO<SecurityGroupRO> getSubGroups(final SecurityGroupRO root) {
        return ImmutableSecuritySet.emptySetRO();
    }

}
