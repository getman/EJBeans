package getman.ejb2.entity.cmp.human;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import java.rmi.RemoteException;

/**
 * Created by Parfenov Artem on 12.04.2016.
 */
public abstract class HumanBean implements EntityBean {
    /*private String lastName;
    private String firstName;
    private String primaryKey;*/

    private EntityContext entityContext;

    //------------------------------------------------------
    public abstract void setFirst(String first);
    public abstract String getFirst();
    public abstract void setLast(String last);
    public abstract String getLast();
    public abstract void setPK(String pk);
    public abstract String getPK();



    //--------------------------------------------------------------
    /**we have to set the create method according to J2EE spec*/
    public String ejbCreate(String firstName, String lastName) {
        this.setLast(lastName);
        this.setFirst(firstName);
        this.setPK(this.getPrimareKey());
        //inserting data into the database
        //...

        return this.getPK();
    }

    public void ejbPostCreate(String firstName, String lastName) {

    }

    public String getLastName() {
        return this.getLast();
    }

    /**updates the database state*/
    public void setLastName(String lastName) {
        this.setLast(lastName);
    }

    public String getFirstName() {
        return this.getFirst();
    }

    /**updates the database state*/
    public void setFirstName(String firstName) {
        this.setFirst(firstName);
    }


    //----------------etnity bean methods---------------
    public void setEntityContext(EntityContext entityContext) throws EJBException, RemoteException {
        this.entityContext = entityContext;
    }

    public void unsetEntityContext() throws EJBException, RemoteException {
    }

    public void ejbRemove() throws RemoveException, EJBException, RemoteException {
    }

    public void ejbActivate() throws EJBException, RemoteException {
    }

    public void ejbPassivate() throws EJBException, RemoteException {
    }

    public void ejbLoad() throws EJBException, RemoteException {
    }

    public void ejbStore() throws EJBException, RemoteException {
    }


    private String getPrimareKey() {
        return "some primary key";
    }
}
