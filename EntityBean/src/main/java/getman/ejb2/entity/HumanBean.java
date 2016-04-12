package getman.ejb2.entity;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import java.rmi.RemoteException;

/**
 * Created by Parfenov Artem on 12.04.2016.
 */
public class HumanBean implements EntityBean {
    private String lastName;
    private String firstName;
    private String primaryKey;

    private EntityContext entityContext;



    //--------------------------------------------------------------
    /**we have to set the create method according to J2EE spec*/
    public String ejbCreate(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.primaryKey = this.getPk();
        //inserting data into the database
        //...

        return this.primaryKey;
    }


    public String getLastName() {
        return lastName;
    }

    /**updates the database state*/
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    /**updates the database state*/
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



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

    private String getPk() {
        return "some primary key";
    }
}
