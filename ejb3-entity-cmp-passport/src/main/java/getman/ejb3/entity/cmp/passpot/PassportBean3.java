package getman.ejb3.entity.cmp.passpot;

import javax.persistence.*;
import java.io.Serializable;

/** Person`s passport entity
 * Created by Parfenov Artem on 01.06.2016.
 */

@Entity
@Table(name="passport")
@SqlResultSetMapping(name="PassResult",
        classes={@ConstructorResult(targetClass=PassportBean3.class,
            columns={
                @ColumnResult(name="passid"),
                @ColumnResult(name="number"),
                @ColumnResult(name="country")})})

public class PassportBean3 implements Serializable {
    //------------Data fields-----------
    @Id
    @Column(name="passid")
    private Integer passid;
    @Column(name="number")
    private String number;
    @Column(name="country")
    private String country;
    //------------Methods-----------
    public PassportBean3() {}

    /**конструктор с параметрами в порядке следования их в SqlResultSetMapping*/
    public PassportBean3(Integer id, String number, String country) {
        this.passid = id;
        this.number = number;
        this.country = country;
    }



    public Integer getPassid() {
        return passid;
    }

    public void setPassid(Integer id) {
        this.passid = id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
