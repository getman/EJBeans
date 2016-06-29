package getman.ejb3.entity.passport;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Parfenov Artem on 07.06.2016.
 */
@Entity
@Table(name="human")
@SqlResultSetMapping(name="SelectHuman",
        classes={
                @ConstructorResult(
                        targetClass=HumanEntity3.class,
                        columns={
                                @ColumnResult(name="name"),
                                @ColumnResult(name="id"),
                                @ColumnResult(name="surname")})
        })
public class HumanEntity3 implements Serializable {
    //------------Data fields-----------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;

    /**каждый человек имеет только один гражданский паспорт*/
    @OneToOne(mappedBy = "human", cascade={CascadeType.REMOVE})
    private PassportBean3 passport;

    //------------Methods-----------
    public HumanEntity3() {}
    public HumanEntity3(String name, Integer id, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public PassportBean3 getPassport() {
        return passport;
    }

    public void setPassport(PassportBean3 passport) {
        this.passport = passport;
    }
}
