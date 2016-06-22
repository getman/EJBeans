package getman.ejb3.entity.cmp.passpot;

import javax.persistence.*;

/**
 * Created by Parfenov Artem on 07.06.2016.
 */

@Entity
@SqlResultSetMapping(name="SelectDriverId",
        classes={
            @ConstructorResult(
                targetClass=DriverId.class,
                columns={
                    @ColumnResult(name="driverid", type = Integer.class),
                    @ColumnResult(name="number", type = String.class)})})
public class DriverId {
    @Id
    @Column(name="driverid")
    private Integer driverid;
    @Column(name="number")
    private String number;

    public DriverId() {}
    public DriverId(Integer driverid, String number) {
        this.driverid = driverid;
        this.number = number;
    }

    public Integer getDriverid() {
        return driverid;
    }

    public void setDriverid(Integer driverid) {
        this.driverid = driverid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

