package getman.ejb2.entity.cmp.passport;

import javax.ejb.EJBHome;
//import javax.ejb.EJBLocalHome;

/**
 * Created by Parfenov Artem on 26.04.2016.
 */
//public interface PassportHome extends EJBLocalHome{
public interface PassportHome extends EJBHome {

    //------------------EJBHome methods-------------
    public PassportBean create(String passportId);
    public PassportBean createPassport(String passportId, String passportCountry);
    /**finder-метод*/
    public PassportBean findByPrimaryKey();

    //----------------Home business methods-----------
    /**получить номер пасспорта по id*/
    public String getPassportNumber(String passportId);
    /**получить страну пасспорта по id*/
    public String getPassportCountry(String passportId);
}
