package getman.ejbclient.test;

import getman.ejb.test.CalculatorBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by Parfenov Artem on 15.02.2016.
 */
public class StatelessBeanClient {

    public static void main(String [] args) throws NamingException {
        System.out.println(callRemoteAddition(Double.valueOf(10), Double.valueOf(6)));
    }

    private static double callRemoteAddition(double a, double b) throws NamingException {
//        Properties prop = new Properties();
//        prop.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
//        prop.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
//        prop.put("java.naming.provider.url", "iiop://193.124.131.105:3700");

//        prop.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
//        prop.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
//        prop.put("org.omg.CORBA.ORBInitialHost", "193.124.131.105");
//        prop.put("org.omg.CORBA.ORBInitialPort", "3700");

//        prop.put(InitialContext.SECURITY_PRINCIPAL, "admin");
//        prop.put(InitialContext.SECURITY_CREDENTIALS, "12345678");

//        InitialContext initialContext = new InitialContext(prop);
        InitialContext initialContext = new InitialContext();

//        Object object = initialContext.lookup("getman.ejb.test.CalculatorBean");
//        Object object = initialContext.lookup("StateLessBean");
//        Object object = initialContext.lookup("getman.ejb.test.StateLessBean-ear");
//        Object object = initialContext.lookup("StateLessBean-ear");
        Object object = initialContext.lookup("StateLessBean-ear");
        CalculatorBeanRemote calc = (CalculatorBeanRemote) object;
        return calc.add(a, b);
    }

}

