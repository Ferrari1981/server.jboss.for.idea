package model;


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
public class OrderTc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique=true, nullable=false)
    private int id;

    @Column(name="current_table", precision=38)
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update", length=1)
    private Date dateUpdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length=1)
    private Date dateorders;

    private Integer kolich;

    @Column(precision=38)
    private BigDecimal machall;

    @Column(length=300)
    private String machina;

    @Column(nullable=false)
    private Integer machinaexp;

    @Column(length=300)
    private String orders;

    private float summacina;

    @Column(name="user_update")
    private Integer userUpdate;

    @Column(precision=38)
    private BigDecimal uuid;

    public OrderTc() {
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

    public Date getDateorders() {
        return this.dateorders;
    }

    public void setDateorders(Date dateorders) {
        this.dateorders = dateorders;
    }

    public Integer getKolich() {
        return this.kolich;
    }

    public void setKolich(Integer kolich) {
        this.kolich = kolich;
    }

    public BigDecimal getMachall() {
        return this.machall;
    }

    public void setMachall(BigDecimal machall) {
        this.machall = machall;
    }

    public String getMachina() {
        return this.machina;
    }

    public void setMachina(String machina) {
        this.machina = machina;
    }

    public Integer getMachinaexp() {
        return this.machinaexp;
    }

    public void setMachinaexp(Integer machinaexp) {
        this.machinaexp = machinaexp;
    }

    public String getOrders() {
        return this.orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public float getSummacina() {
        return this.summacina;
    }

    public void setSummacina(float summacina) {
        this.summacina = summacina;
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