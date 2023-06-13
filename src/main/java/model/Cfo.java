package model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import javax.transaction.TransactionScoped;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the cfo database table.
 *
 */
@Entity
@Table(name="cfo",catalog="storage",schema="dbo")
@NamedQuery(name="Cfo.findAll", query="SELECT c FROM model.Cfo   c")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate

public class Cfo  implements Serializable {
private static final long serialVersionUID = 1L;

@Id
private Integer id;

private int boss;

private Boolean closed;

@Column(name="current_table")
private BigDecimal currentTable;

        @Basic
        @Column(name = "date_update", nullable = false)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
private Date dateUpdate;

private String kod;

private String name;

private int organization;

private Integer region;

@Column(name="user_update")
private int userUpdate;

private BigDecimal uuid;

public Cfo() {
        }

public Integer getId() {
        return this.id;
        }

public void setId(Integer id) {
        this.id = id;
        }

public int getBoss() {
        return this.boss;
        }

public void setBoss(int boss) {
        this.boss = boss;
        }

public Boolean getClosed() {

        return this.closed;
        }

public void setClosed(Boolean closed) {

        this.closed = closed;
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

public String getKod() {
        return this.kod;
        }

public void setKod(String kod) {
        this.kod = kod;
        }

public String getName() {
        return this.name;
        }

public void setName(String name) {
        this.name = name;
        }

public int getOrganization() {
        return this.organization;
        }

public void setOrganization(int organization) {
        this.organization = organization;
        }

public Integer getRegion() {
        if(this.region==null) {
                return 0;
        }else {
                return this.region;
        }
        }

public void setRegion(Integer region) {
        this.region = region;
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
