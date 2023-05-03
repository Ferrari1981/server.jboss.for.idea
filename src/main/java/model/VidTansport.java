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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;


    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "date_update", nullable = true)
    private Date dateUpdate;
    @Basic
    @Column(name = "uuid", nullable = true, precision = 0)
    private BigDecimal uuid;
    @Basic
    @Column(name = "user_update", nullable = true)
    private Integer userUpdate;
    @Basic
    @Column(name = "current_table", nullable = true, precision = 0)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VidTansport that = (VidTansport) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (dateUpdate != null ? !dateUpdate.equals(that.dateUpdate) : that.dateUpdate != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (userUpdate != null ? !userUpdate.equals(that.userUpdate) : that.userUpdate != null) return false;
        if (currentTable != null ? !currentTable.equals(that.currentTable) : that.currentTable != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateUpdate != null ? dateUpdate.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (userUpdate != null ? userUpdate.hashCode() : 0);
        result = 31 * result + (currentTable != null ? currentTable.hashCode() : 0);
        return result;
    }
}
