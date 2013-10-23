package pl.edu.pw.elka.mtoporow.isoman.security;

import org.objectledge.security.GroupNamingPolicy;
import org.objectledge.security.object.Group;
import org.objectledge.security.util.GroupSet;

public class DummyGroupNamingPolicy implements GroupNamingPolicy {

    @Override
    public GroupSet getAllGroups() {
        return new GroupSet();
    }

    @Override
    public GroupSet getSubGroups(Group group) {
        return new GroupSet();
    }
}
