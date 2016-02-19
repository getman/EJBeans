package getman.ejb.jaas.test;

import com.sun.appserv.security.AppservPasswordLoginModule;
import com.sun.enterprise.security.auth.login.LDAPLoginModule;
import com.sun.enterprise.security.auth.realm.ldap.LDAPRealm;
import com.sun.jndi.ldap.LdapCtx;

import javax.security.auth.login.LoginException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by getman on 20.02.16.
 */
public class MyLoginModule extends AppservPasswordLoginModule {
    @Override
    protected void authenticateUser() throws LoginException {
        if (!(_currentRealm instanceof MyRealm)) {
            throw new LoginException("Realm not KHMBRealm");
        }

        MyRealm realm = (MyRealm) _currentRealm;


        Enumeration enumeration = null;
        List<String> authenticatedGroups = new LinkedList();
        try {
            enumeration = realm.getGroupNames(_username);
        } catch (Exception e) {
            throw new LoginException("Get groups exception");
        }
        for (int i = 0; enumeration != null && enumeration.hasMoreElements(); i++) {
            authenticatedGroups.add((String) enumeration.nextElement());
        }
        commitUserAuthentication(authenticatedGroups.toArray(new String[0]));
    }
}
