package getman.ejb.servlet;

import getman.ejb3.test.CalculatorRemote3;
import getman.ejb.test.CalculatorRemote;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Parfenov Artem on 20.02.2016.
 */
public class CalculatorServlet extends HttpServlet {
    @EJB
    private CalculatorRemote3 calc3;
//    @EJB
    private CalculatorRemote calc;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(calc3.toString());
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet FooServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Calculator3: " + calc3.add(3,1) + "</h1>");
        try {
            InitialContext ic = new InitialContext();
//            java.lang.Object ejbObj = ic.lookup("java:comp/env/getman/ejb/test/CalculatorRemote");
//            java.lang.Object ejbObj = ic.lookup("CalculatorBean");
            java.lang.Object ejbObj = ic.lookup("getman.ejb.test.CalculatorHome");
            calc = (CalculatorRemote)javax.rmi.PortableRemoteObject.narrow(ejbObj, CalculatorRemote.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        out.println("<h1>Calculator: " + calc.add(4,2) + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
