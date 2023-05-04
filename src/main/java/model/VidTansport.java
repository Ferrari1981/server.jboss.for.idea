package model;

import jakarta.persistence.*;
import org.hibernate.annotations.OptimisticLockType;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "vid_tc", schema = "dbo", catalog = "storage")

@javax.persistence.NamedQuery(name="VidTansport.findAll", query="SELECT vt FROM model.VidTansport  vt")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate

public class VidTansport {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;


    @Temporal( TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "date_update", nullable = true)
    private Date dateUpdate;
    @Basic
    @Column(name = "uuid", nullable = true )
    private BigDecimal uuid;
    @Basic
    @Column(name = "user_update", nullable = true)
    private Integer userUpdate;
    @Basic
    @Column(name = "current_table", nullable = true )
    private BigDecimal currentTable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public BigDecimal getUuid() {

        return uuid;
    }

    public void setUuid(BigDecimal uuid) {

        this.uuid = uuid;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getCurrentTable() {

        return currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {

        this.currentTable = currentTable;
    }

}
