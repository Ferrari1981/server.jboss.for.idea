package model;

import io.reactivex.rxjava3.core.Flowable;
import jakarta.persistence.*;
import org.hibernate.annotations.OptimisticLockType;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "order_tc", schema = "dbo", catalog = "storage")

@javax.persistence.NamedQuery(name="OrderTransport.findAll", query="SELECT ort FROM model.OrderTransport  ort")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate

public class OrderTransport {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "orders", nullable = true, length = 300)
    private String orders;
    @Basic
    @Column(name = "machina", nullable = true, length = 300)
    private String machina;

    @Basic
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name = "date_update", nullable = true)
    private Date dateUpdate;
    @Basic
    @Column(name = "kolich", nullable = true)
    private Integer kolich;
    @Basic
    @Column(name = "dateorders", nullable = true)
    private Date dateorders;
    @Basic
    @Column(name = "machinaexp", nullable = false)
    private Integer machinaexp;
    @Basic
    @Column(name = "machall", nullable = true, precision = 0)
    private Long machall;
    @Basic
    @Column(name = "summacina", nullable = true)
    private float summacina;
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

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getMachina() {
        return machina;
    }

    public void setMachina(String machina) {
        this.machina = machina;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getKolich() {
        return kolich;
    }

    public void setKolich(Integer kolich) {
        this.kolich = kolich;
    }

    public Date getDateorders() {
        return dateorders;
    }

    public void setDateorders(Date dateorders) {
        this.dateorders = dateorders;
    }

    public Integer getMachinaexp() {

        return machinaexp;
    }

    public void setMachinaexp(Integer machinaexp) {

        this.machinaexp = machinaexp;
    }

    public Long getMachall() {
        return machall;
    }

    public void setMachall(Long machall) {
        this.machall = machall;
    }

    public float getSummacina() {

        return summacina;
    }

    public void setSummacina(float summacina) {

        this.summacina = summacina;
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
