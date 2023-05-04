package model;



import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the vid_tc database table.
 *
 */
@Entity
@Table(name="vid_tc",catalog="storage",schema="dbo")
@NamedQuery(name="VidTc.findAll", query="SELECT v FROM model.VidTc v")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate
public class VidTc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique=true, nullable=false)
    private int id;

    @Column(name="current_table", precision=38)
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update", length=1)
    private Date dateUpdate;

    private String name;

    @Column(name="user_update")
    private Integer userUpdate;

    @Column(precision=38)
    private BigDecimal uuid;

    public VidTc() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCurrentTable() {

        return this.currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {

        this.currentTable = currentTable;
    }

    public Date getDateUpdate() {
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserUpdate() {
        return this.userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

}