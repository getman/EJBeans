package getman.ejb3.entity.cmp.passpot;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Parfenov Artem on 03.06.2016.
 */
@WebServlet(urlPatterns = "/tasks")
public class PassportServlet extends HttpServlet {

    @PersistenceContext(unitName = "passport-unit")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        selectEntityBeans(request, response);
//        selectEntityProperties(request, response);
    }

    private void insertEntityBean(HttpServletRequest request, HttpServletResponse response) {

    }

    private void selectEntityBeans(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PassportBean3> samples = entityManager.createNativeQuery("SELECT p.passid, p.number, p.country FROM passport p", "PassResult").getResultList();
        List<HumanEntity3> humanEntities = entityManager.createNativeQuery("SELECT h.humanid, h.name, h.surname FROM human h", "SelectHuman").getResultList();
        List<DriverId> driverIdEntities = entityManager.createNativeQuery("SELECT d.driverid, d.number FROM driverid d", "SelectDriverId").getResultList();
        if (!samples.isEmpty()) {
            request.setAttribute("passportBeans", samples);
            request.setAttribute("humanEntities", humanEntities);
            request.setAttribute("driveridEntities", driverIdEntities);
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/entityBean.jsp").forward(request, response);
    }

    private void selectEntityProperties(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Query query = entityManager.createNativeQuery("select passid, number, country from passport");
        List<PassportBean3> samples = query.getResultList();
        if (!samples.isEmpty()) {
            Iterator iter = samples.iterator();
            while (iter.hasNext()) {
                Object[] res = (Object[]) iter.next();
                request.setAttribute("passId", Integer.valueOf((Integer) res[0]));
                request.setAttribute("passNumber", String.valueOf((String) res[1]));
            }
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/entityProperies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action != null) {
            if ("add".equals(action)) {
                try {
                    handleAddition(request);
                } catch (Throwable t) {
                    throw new RuntimeException(t);
                }
            } else if ("remove".equals(action)) {
                try {
                    handleRemoval(request);
                } catch (Throwable t) {
                    throw new RuntimeException(t);
                }
            }
        }
        response.sendRedirect("tasks");
    }

    private void handleAddition(HttpServletRequest request) throws SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        String item = request.getParameter("item");
        if (item != null && !item.isEmpty()) {
            transaction.begin();
            entityManager.persist(new PassportBean3());
            transaction.commit();
        }
    }

    private void handleRemoval(HttpServletRequest request) throws IOException, SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        Long item = Long.valueOf(request.getParameter("item"));
        if (item != null) {
            transaction.begin();
            PassportBean3 task = entityManager.find(PassportBean3.class, item);
            if (task != null) {
                entityManager.remove(task);
                transaction.commit();
            }
        }
    }
}

