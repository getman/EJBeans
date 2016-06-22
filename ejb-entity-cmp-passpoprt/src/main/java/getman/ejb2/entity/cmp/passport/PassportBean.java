package getman.ejb2.entity.cmp.passport;

//import getman.ejb.logger.EJBLogger;
//import org.apache.logging.log4j.Logger;

import javax.ejb.*;
import java.rmi.RemoteException;

/**
 * Created by Parfenov Artem on 26.04.2016.
 */
public abstract class PassportBean implements EntityBean{
//    private Logger logger = EJBLogger.getLogger(getClass());
    private EntityContext context;

    //--------------------EntityBean methods---------
    public void setEntityContext(EntityContext entityContext) throws EJBException, RemoteException {
        context = entityContext;
    }

    public void unsetEntityContext() throws EJBException, RemoteException {
        context = null;
    }

    public void ejbRemove() throws RemoveException, EJBException, RemoteException {}

    public void ejbActivate() throws EJBException, RemoteException {}

    public void ejbPassivate() throws EJBException, RemoteException {}

    public void ejbLoad() throws EJBException, RemoteException {}

    public void ejbStore() throws EJBException, RemoteException {}


    //--------------------Методы для вызова из EJBHome методов Home interface -------------
    /**должен возвращать тип данных ключа для этого EntityBean*/
    public String ejbCreate(String passportId) throws CreateException {
//        logger.trace("ejbCreate");
        return this.getPK();
    }

    /**параметры должны совпадать с ответным методом ejbCreate*/
    public void ejbPostCreate(String passportId) {
//        logger.trace("ejbPostCreate");
    }

    public String ejbCreatePassport(String passportId, String passportCountry) throws CreateException {
//        logger.trace("ejbCreatePassport");
        return this.getPK();
    }

    public void ejbPostCreatePassport(String passportId, String passportCountry) {
//        logger.trace("ejbPostrCreatePassport");
    }

    //---------------------Методы для вызова из бизнес-методов Home interface-------------
    public String ejbHomeGetPassportNumber(String passportId) {
//        logger.trace("ejbHomeGetPassportNumber");
        return "passport number";
    }

    public String ejbHomeGetPassportCountry(String passportId) {
//        logger.trace("ejbHomeGetPassportCountry");
        return "passport country";
    }


    //-----------------Методы для вызова из Component interface (EJB[Local]Object)-----------
    public void setPassportNumber(String newPassportNumber) {
//        logger.trace("setPassportNumber");
        this.setPassportNumber(newPassportNumber);
    }

    public String getPassportNumber() {
//        logger.trace("getPassportNumber");
        return this.getNumber();
    }

    public void setPassportCountry(String newPassportCountry) {
//        logger.trace("setPassportCountry");
        this.setCountry(newPassportCountry);
    }

    public String getPassportCountry() {
//        logger.trace("getPassportCountry");
        return this.getCountry();
    }

    public void setPassportId(String newPassId) {
        this.setPassid(newPassId);
    }

    public String getPassportId() {
        return this.getPassid();
    }


    //-----------------Абстрактные методы для доступа к CMP-полям-------------
    public abstract String getNumber();
    public abstract void setNumber(String passportNumber);
    public abstract String getCountry();
    public abstract void setCountry(String passportCountry);
    public abstract String getPassid();
    public abstract void setPassid(String id);

    private String getPK() {
//        logger.trace("getPK");
        return "new PK";
    }
}
