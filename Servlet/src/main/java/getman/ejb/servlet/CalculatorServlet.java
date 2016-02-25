package getman.ejb.servlet;

import getman.ejb.test.CalculatorHome;
import getman.ejb.test.CalculatorWithMemoryHome;
import getman.ejb.test.CalculatorWithMemoryRemote;
import getman.ejb3.test.CalculatorRemote3;
import getman.ejb.test.CalculatorRemote;

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
    private CalculatorRemote3 calc3;
    private CalculatorRemote calcBean;
    private CalculatorWithMemoryRemote calcWithMemBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String result) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<div class=\"container\">");
        out.println("<form method = \"post\" class=\"form-signin\" role=\"form\" action=\"calc-servlet\">");
        out.println("Call result: " + result);
        out.println("<p>");
        out.println("<input type=\"submit\" name=\"callCalc\" class=\"btn\" value=\"Call calc\">");
        out.println("<input type=\"submit\" name=\"callCalc3\" class=\"btn\" value=\"Call calc3\">");
        out.println("<input type=\"submit\" name=\"callCalcWithMem\" class=\"btn\" value=\"Call calc with mem\">");
        out.println("<input type=\"submit\" name=\"killCalcWithMem\" class=\"btn\" value=\"Kill calc with mem\">");
        out.println("</form>");
        out.println("</div>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("callCalc") != null) {
            processRequest(request, response, callCalculator(2, 5));
        } else if (request.getParameter("callCalc3") != null) {
            processRequest(request, response, callCalculator3(3, 9));
        } else if (request.getParameter("callCalcWithMem") != null) {
            processRequest(request, response, callCalculatorWithMemory(10));
        } else if (request.getParameter("killCalcWithMem") != null) {
            killCalcWithMem();
            processRequest(request, response, "");
        } else processRequest(request, response, "");
    }

    private String callCalculator3(double a, double b) {
        return Double.valueOf(calc3.add(a, b)).toString();
    }

    private String callCalculator(double a, double b) {
        try {
            if (calcBean == null) {
                InitialContext ic = new InitialContext();
                java.lang.Object ejbHomeStub = ic.lookup("CalcRemote");
                CalculatorHome calcHome = (CalculatorHome)javax.rmi.PortableRemoteObject.narrow(ejbHomeStub, CalculatorHome.class);
                calcBean = (CalculatorRemote) calcHome.create();
            }
            return Double.valueOf(calcBean.add(a, b)).toString();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String callCalculatorWithMemory(double a) {
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

    private void killCalcWithMem() {
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
}
