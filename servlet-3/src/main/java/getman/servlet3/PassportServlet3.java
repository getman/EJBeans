package getman.servlet3;


import getman.ejb.logger.EJBLogger;
import getman.ejb3.entity.passport.DriverId;
import getman.ejb3.entity.passport.HumanEntity3;
import getman.ejb3.entity.passport.PassportBean3;
import org.apache.logging.log4j.Logger;

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
import java.util.Iterator;
import java.util.List;

/**
 * Created by Parfenov Artem on 14.06.2016.
 */
@WebServlet(urlPatterns = "/persons")
public class PassportServlet3 extends HttpServlet {
    private Logger logger = EJBLogger.getLogger(getClass());

    @PersistenceContext(unitName = "passport-unit")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet(...) performed");
        selectEntities(request, response);
//        selectEntityProperties(request, response);
    }

    private void insertEntityBean(HttpServletRequest request, HttpServletResponse response) {

    }

    private void selectEntities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PassportBean3> samples = entityManager.createNativeQuery("SELECT p.passid, p.number, p.country FROM passport p", "PassResult").getResultList();
        List<HumanEntity3> humanEntities = entityManager.createNativeQuery("SELECT h.humanid, h.name, h.surname FROM human h", "SelectHuman").getResultList();
        List<DriverId> driverIdEntities = entityManager.createNativeQuery("SELECT d.driverid, d.number FROM driverid d", "SelectDriverId").getResultList();
        if (!samples.isEmpty()) {
            request.setAttribute("passportBeans", samples);
        }
        if (!humanEntities.isEmpty()) {
            request.setAttribute("humanEntities", humanEntities);
        }
        if (!driverIdEntities.isEmpty()) {
            request.setAttribute("driveridEntities", driverIdEntities);
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/entityBean.jsp").forward(request, response);
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
        request.getServletContext().getRequestDispatcher("/WEB-INF/pages/entityProperies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        logger.info("Action is:" + action);
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
        response.sendRedirect("persons");
    }

    private void handleAddition(HttpServletRequest request) throws SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        String passport = request.getParameter("passport");
        if (passport != null && !passport.isEmpty()) {
            transaction.begin();
            PassportBean3 newPassport = new PassportBean3();
            newPassport.setCountry("расеюшка моя любимая");
            newPassport.setNumber(passport);
            entityManager.persist(newPassport);
            transaction.commit();
        }
    }

    private void handleRemoval(HttpServletRequest request) throws IOException, SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        Integer idToRemove = Integer.valueOf(request.getParameter("idToRemove"));
        if (idToRemove != null) {
            transaction.begin();
            int id = idToRemove;
            logger.info("Id to search passport:" + id);
            PassportBean3 passport = entityManager.find(PassportBean3.class, id);
            if (passport != null) {
                logger.info("Found passport: " + passport.getPassid() + "/" + passport.getNumber() +
                    "/" + passport.getCountry());
                entityManager.remove(passport);
                transaction.commit();
            }
        }
    }
}
