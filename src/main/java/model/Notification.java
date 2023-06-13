package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the notifications database table.
 *
 */
@Entity
@Table(name="notifications",catalog="storage",schema="dbo")
@NamedQuery(name="Notification.findAll", query="SELECT n FROM  model. Notification n")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate

public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer clock;

    @Column(name="current_table")
    private BigDecimal currentTable;



    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "ru", timezone="Russia/Moscow")
    @Column(name="date_start")
    private Date dateStart;



    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS", locale = "ru", timezone="Russia/Moscow")
    private Date dateUpdate;

    @Column(name="id_user")
    private int idUser;

    private Integer rights;

    @Column(name="user_update")
    private int userUpdate;

    private BigDecimal uuid;

    public Notification() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClock() {
        return this.clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public BigDecimal getCurrentTable() {
        return this.currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {
        this.currentTable = currentTable;
    }

    public Date getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateUpdate() {
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Integer getRights() {
        return this.rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public int getUserUpdate() {
        return this.userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

}