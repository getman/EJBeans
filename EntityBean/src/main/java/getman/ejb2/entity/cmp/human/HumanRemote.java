package getman.ejb2.entity.cmp.human;

import javax.ejb.EJBObject;

/**
 * Created by Parfenov Artem on 13.04.2016.
 */
public interface HumanRemote extends EJBObject {
    public String getLastName();

    /**updates the database state*/
    public void setLastName(String lastName);

    public String getFirstName();

    /**updates the database state*/
    public void setFirstName(String firstName);
}
