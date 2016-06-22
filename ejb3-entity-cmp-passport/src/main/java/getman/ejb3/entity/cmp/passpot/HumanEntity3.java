package getman.ejb3.entity.cmp.passpot;

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
                                @ColumnResult(name="humanid"),
                                @ColumnResult(name="surname")})
        })
public class HumanEntity3 implements Serializable {
    //------------Data fields-----------
    @Id
    @Column(name="humanid")
    private Integer humanid;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    //------------Methods-----------
    public HumanEntity3() {}
    public HumanEntity3(String name, Integer id, String surname) {
        this.humanid = id;
        this.name = name;
        this.surname = surname;
    }

    public HumanEntity3(Integer id, String name) {
        this.humanid = id;
        this.name = name;
    }

    public Integer getHumanid() {
        return humanid;
    }

    public void setHumanid(Integer id) {
        this.humanid = id;
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
}
