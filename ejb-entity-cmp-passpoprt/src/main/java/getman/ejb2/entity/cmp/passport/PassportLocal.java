package getman.ejb2.entity.cmp.passport;

//import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;

/**
 * Created by Parfenov Artem on 26.04.2016.
 */
public interface PassportLocal extends EJBObject {
    public void remove();

    //------------Методы доступа к CMP полям entity--------------
    public void setPassportNumber(String newPassportNumber);
    public String getPassportNumber();

    public void setPassportCountry(String newPassportCountry);
    public String getPassportCountry();

    public void setPassportId(String newPassId);
    public String getPassportId();

}
