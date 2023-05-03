package model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "orders", nullable = true, length = 300)
    private String orders;
    @Basic
    @Column(name = "machina", nullable = true, length = 300)
    private String machina;

    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Basic
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
    private int machinaexp;
    @Basic
    @Column(name = "machall", nullable = true, precision = 0)
    private Long machall;
    @Basic
    @Column(name = "summacina", nullable = true)
    private Object summacina;
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

    public int getMachinaexp() {
        return machinaexp;
    }

    public void setMachinaexp(int machinaexp) {
        this.machinaexp = machinaexp;
    }

    public Long getMachall() {
        return machall;
    }

    public void setMachall(Long machall) {
        this.machall = machall;
    }

    public Object getSummacina() {
        return summacina;
    }

    public void setSummacina(Object summacina) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderTransport that = (OrderTransport) o;

        if (id != that.id) return false;
        if (machinaexp != that.machinaexp) return false;
        if (orders != null ? !orders.equals(that.orders) : that.orders != null) return false;
        if (machina != null ? !machina.equals(that.machina) : that.machina != null) return false;
        if (dateUpdate != null ? !dateUpdate.equals(that.dateUpdate) : that.dateUpdate != null) return false;
        if (kolich != null ? !kolich.equals(that.kolich) : that.kolich != null) return false;
        if (dateorders != null ? !dateorders.equals(that.dateorders) : that.dateorders != null) return false;
        if (machall != null ? !machall.equals(that.machall) : that.machall != null) return false;
        if (summacina != null ? !summacina.equals(that.summacina) : that.summacina != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (userUpdate != null ? !userUpdate.equals(that.userUpdate) : that.userUpdate != null) return false;
        if (currentTable != null ? !currentTable.equals(that.currentTable) : that.currentTable != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (machina != null ? machina.hashCode() : 0);
        result = 31 * result + (dateUpdate != null ? dateUpdate.hashCode() : 0);
        result = 31 * result + (kolich != null ? kolich.hashCode() : 0);
        result = 31 * result + (dateorders != null ? dateorders.hashCode() : 0);
        result = 31 * result + machinaexp;
        result = 31 * result + (machall != null ? machall.hashCode() : 0);
        result = 31 * result + (summacina != null ? summacina.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (userUpdate != null ? userUpdate.hashCode() : 0);
        result = 31 * result + (currentTable != null ? currentTable.hashCode() : 0);
        return result;
    }
}
