package getman.ejbclient.test;


//import com.sun.appserv.security.ProgrammaticLogin;
//import getman.ejb.test.CalculatorHome;
//import getman.ejb.test.CalculatorRemote;
//import getman.ejb3.test.CalculatorRemote3;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.security.auth.callback.*;
//import javax.security.auth.login.LoginContext;
//import java.io.IOException;
//import java.util.Properties;

/**
 * Created by Parfenov Artem on 15.02.2016.
 */
public class StatelessBeanClient {

    public static void main(String [] args) throws Exception {
//        System.out.println(callRemoteAddition(Double.valueOf(10), Double.valueOf(6)));
//        callRemote3();
//        callRemote3new();
    }

   /* private static void callRemote3new() {
        System.setProperty("java.security.auth.login.config", "C:\\glassfish3\\glassfish\\domains\\domain1\\config\\login.conf");
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        props.setProperty(InitialContext.SECURITY_PRINCIPAL, "admin");
        props.setProperty(InitialContext.SECURITY_CREDENTIALS, "12345678");
//        props.setProperty(InitialContext.PROVIDER_URL, "ormi://localhost:3700/myRealm");
//        props.setProperty("dedicated.rmicontext", "true");

        InitialContext ic = null;
        try {
            ic = new InitialContext(props);
//            ic = new InitialContext();

            ProgrammaticLogin pm = new ProgrammaticLogin();
            pm.login("admin", "12345678");
//            pm.login("getman", "12345678");
//            MyCallbackHandler cbh = new MyCallbackHandler("admin", "12345678");
//            LoginContext auth = new LoginContext( "getmanRealm", cbh);
//            auth.login();

//            CalculatorRemote3 b = (CalculatorRemote3)ic.lookup("java:global/CalculatorRemote3");
//            CalculatorRemote3 b = (CalculatorRemote3)ic.lookup("java:global/getman.ejb3.test.CalculatorRemote3");
//            CalculatorRemote3 b = (CalculatorRemote3)ic.lookup("getman.ejb3.test.CalculatorRemote3");
//            CalculatorRemote3 b = (CalculatorRemote3)ic.lookup("CalculatorRemote3");
//            CalculatorRemote3 b = (CalculatorRemote3)ic.lookup("CalculatorBean3");
//            ic = new InitialContext();
            java.lang.Object ejbHomeStub = ic.lookup("CalcRemote");
            CalculatorHome calcHome = (CalculatorHome)javax.rmi.PortableRemoteObject.narrow(ejbHomeStub, CalculatorHome.class);
            CalculatorRemote b = (CalculatorRemote) calcHome.create();

            System.out.println(b.add(1,1));
            pm.logout();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class MyCallbackHandler implements CallbackHandler {
        private String username;
        private String password;
        public MyCallbackHandler(String username, String password) {
            this.username = username;
            this.password = password;
        }
        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
            for(int i = 0; i < callbacks.length; i++) {
                if(callbacks[i] instanceof NameCallback) {
                    NameCallback nc = (NameCallback)callbacks[i];
                    nc.setName(username);
                } else if(callbacks[i] instanceof PasswordCallback) {
                    PasswordCallback pc = (PasswordCallback)callbacks[i];
                    pc.setPassword(password.toCharArray());
                } else {
                    throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
                }
            }
        }
    }*/

}

