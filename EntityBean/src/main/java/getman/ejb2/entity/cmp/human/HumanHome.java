package getman.ejb2.entity.cmp.human;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Created by Parfenov Artem on 13.04.2016.
 */
public interface HumanHome extends EJBHome {
    public HumanBean create(String firstName, String lastName) throws CreateException, RemoteException;
    public HumanBean findByPrimaryKey(String primaryKey) throws FinderException, RemoteException;
    public HumanBean findByPersonName(String name) throws FinderException, RemoteException;
    public Collection findByPersonLastName(String lastName) throws FinderException, RemoteException;
}
