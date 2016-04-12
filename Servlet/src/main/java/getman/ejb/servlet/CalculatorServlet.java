package getman.ejb.servlet;

import getman.ejb.logger.EJBLogger;
import getman.ejb.test.CalculatorHome;
import getman.ejb.test.CalculatorWithMemoryHome;
import getman.ejb.test.CalculatorWithMemoryRemote;
import getman.test.ejb3.stateless.calc.CalculatorRemote3;
import getman.ejb.test.CalculatorRemote;
import getman.test.ejb3.stateful.calc.CalculatorWithMemoryRemote3;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

/**
 * Created by Parfenov Artem on 20.02.2016.
 */
public class CalculatorServlet extends HttpServlet {
    @EJB
    private CalculatorRemote3 statelessCalc3bean;
    @EJB
    private CalculatorWithMemoryRemote3 calcStateful3;
    private CalculatorRemote statelessCalc2Bean;
    private CalculatorWithMemoryRemote calcWithMemBean;
    private Logger logger = EJBLogger.getLogger(getClass());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String result) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<div class=\"container\">");
        out.println("<form method = \"post\" class=\"form-signin\" role=\"form\" action=\"calc-servlet\">");
        out.println("Call result: " + result);
        out.println("<p>");
        out.println("<input type=\"submit\" name=\"callStatelessCalc2\" class=\"btn\" value=\"Call stateless calc 2.0\">");
        out.println("<input type=\"submit\" name=\"killStatelessCalc2\" class=\"btn\" value=\"Remove\">");

        out.println("<input type=\"submit\" name=\"callStatelessCalc3\" class=\"btn\" value=\"Call stateless calc 3.1\">");
        out.println("<input type=\"submit\" name=\"killStatelessCalc3\" class=\"btn\" value=\"Remove\">");

        out.println("<input type=\"submit\" name=\"callStatefulCalc2\" class=\"btn\" value=\"Call stateful calc 2.0\">");
        out.println("<input type=\"submit\" name=\"killStatefulCalc2\" class=\"btn\" value=\"Remove\">");

        out.println("<input type=\"submit\" name=\"calcStatefulCalc3\" class=\"btn\" value=\"Call stateful calc 3.1\">");
        out.println("<input type=\"submit\" name=\"killStatefulCalc3\" class=\"btn\" value=\"Remove\">");
        out.println("</form>");
        out.println("</div>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("callStatelessCalc2") != null) {
            processRequest(request, response, callStatelessCalc2(2, 5));
            logger.debug("callStatelessCalc2");
        } else if (request.getParameter("killStatelessCalc2") != null) {
            killStateless2();
            processRequest(request, response, "");
            logger.debug("killStatelessCalc2");
        } else if (request.getParameter("callStatelessCalc3") != null) {
            processRequest(request, response, callStatelessCalc3(3, 9));
            logger.debug("callStatelessCalc3");
        } else if (request.getParameter("killStatelessCalc3") != null) {
            killStateless3();
            processRequest(request, response, "");
            logger.debug("killStatelessCalc3");
        } else if (request.getParameter("callStatefulCalc2") != null) {
            processRequest(request, response, callStatefulCalc2(10));
            logger.debug("callStatefulCalc2");
        } else if (request.getParameter("killStatefulCalc2") != null) {
            killStatefulCalc2();
            processRequest(request, response, "");
            logger.debug("killStatefulCalc2");
        } else if (request.getParameter("calcStatefulCalc3") != null) {
            processRequest(request, response, callStatefulCalc3(15));
            logger.debug("calcStatefulCalc3");
        }  else if (request.getParameter("killStatefulCalc3") != null) {
            killStatefulCalc3();
            processRequest(request, response, "");
            logger.debug("killStatefulCalc3");
        } else processRequest(request, response, "");
    }

    private String callStatefulCalc3(double a) {
        try {
            if (calcStateful3 == null) {
                reinjectStatefulCalc3();
            }
            return Double.valueOf(calcStateful3.addToMemory(a)).toString();
        } catch (NamingException e) {
            logger.error("Could not reinject stateful calc 3", e);
        }
        return null;
    }

    private String callStatelessCalc3(double a, double b) {
        return Double.valueOf(statelessCalc3bean.add(a, b)).toString();
    }

    private String callStatelessCalc2(double a, double b) {
        try {
            if (statelessCalc2Bean == null) {
                InitialContext ic = new InitialContext();
                java.lang.Object ejbHomeStub = ic.lookup("CalcRemote");
                CalculatorHome calcHome = (CalculatorHome)javax.rmi.PortableRemoteObject.narrow(ejbHomeStub, CalculatorHome.class);
                statelessCalc2Bean = (CalculatorRemote) calcHome.create();
            }
            return Double.valueOf(statelessCalc2Bean.add(a, b)).toString();
        } catch (NamingException e) {
            logger.error("Failed to execute call due to NamingException", e);
        } catch (RemoteException e) {
            logger.error("Failed to execute call due to RemoteException", e);
        }
        return null;
    }

    private String callStatefulCalc2(double a) {
        try {
            if (calcWithMemBean == null) {
                InitialContext ic = new InitialContext();
                java.lang.Object ejbHomeStub = ic.lookup("CalcWithMemRemote");
                CalculatorWithMemoryHome calcHome = (CalculatorWithMemoryHome) javax.rmi.PortableRemoteObject.narrow(
                        ejbHomeStub, CalculatorWithMemoryHome.class);
                calcWithMemBean = (CalculatorWithMemoryRemote) calcHome.create();
            }
            return Double.valueOf(calcWithMemBean.addToMemory(a)).toString();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void killStatefulCalc2() {
        if (calcWithMemBean != null) {
            try {
                calcWithMemBean.remove();
                calcWithMemBean = null;
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (RemoveException e) {
                e.printStackTrace();
            }
        }
    }

    private void killStatefulCalc3() {
        if (calcStateful3 != null) {
            calcStateful3.removeMe();
            calcStateful3 = null;
        }
    }

    private void killStateless3() {
        if (statelessCalc3bean != null) {
            statelessCalc3bean.remove();
        }
    }

    private void killStateless2() {
        try {
            if (statelessCalc2Bean != null) {
                statelessCalc2Bean.remove();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (RemoveException e) {
            e.printStackTrace();
        }
    }

    private void reinjectStatefulCalc3() throws NamingException {
        calcStateful3 = (CalculatorWithMemoryRemote3) new InitialContext().
                lookup("getman.test.ejb3.stateful.calc.CalculatorWithMemoryRemote3");
    }
}
