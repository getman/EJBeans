package getman.ejb.jaas.test;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by getman on 20.02.16.
 */
public class MyRealm extends AppservRealm {
    private static final String PARAM_JAAS_CONTEXT = "jaas-context";
    private static final String GROUP_ALL = "Authenticated";

    @Override
    public void init(Properties properties) {
        String propJaasContext = properties.getProperty(PARAM_JAAS_CONTEXT);
        if (propJaasContext != null) {
            setProperty(PARAM_JAAS_CONTEXT, propJaasContext);
        }
    }

    @Override
    public String getAuthType() {
        return "Getman Realm";
    }

    @Override
    public Enumeration getGroupNames(String s) throws InvalidOperationException, NoSuchUserException {
        Vector vector = new Vector();
        vector.add(GROUP_ALL);
        return vector.elements();
    }
}
