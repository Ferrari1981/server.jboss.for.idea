package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the order_tc database table.
 *
 */
@Entity
@Table(name="order_tc")
@NamedQuery(name="OrderTc.findAll", query="SELECT o FROM model.OrderTc o")

@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate


public class OrderTc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique=true, nullable=false)
    private int id;

    private Integer cfo;

    @Column(name="current_table", precision=38)
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update", length=1)
    private Date dateUpdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "ru", timezone="Russia/Moscow")
    private Date dateorders;

    @Column(name="gos_nomer")
    private Integer gosNomer;

    @Column(name="number_order")
    private String numberOrder;

    private Integer status;

    @Column(name="user_update")
    private Integer userUpdate;

    @Column(precision=38)
    private BigDecimal uuid;

    @Column(name="vid_trasport")
    private Integer vidTrasport;

    public OrderTc() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCfo() {

        return this.cfo;
    }

    public void setCfo(Integer cfo) {
        this.cfo = cfo;
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

    public Date getDateorders() {
        return this.dateorders;
    }

    public void setDateorders(Date dateorders) {
        this.dateorders = dateorders;
    }

    public Integer getGosNomer() {

        return this.gosNomer;
    }

    public void setGosNomer(Integer gosNomer) {

        this.gosNomer = gosNomer;
    }

    public String getNumberOrder() {

        return this.numberOrder;
    }

    public void setNumberOrder(String numberOrder) {

        this.numberOrder = numberOrder;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getVidTrasport() {
        return this.vidTrasport;
    }

    public void setVidTrasport(Integer vidTrasport) {

        this.vidTrasport = vidTrasport;
    }

}