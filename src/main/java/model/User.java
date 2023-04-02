package model;



import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the users database table.
 *
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Column(unique=true, nullable=false)
    private Integer id;

    @Column(length=50)
    private String baza;

    @Column(name="current_table", precision=38)
    private BigDecimal currentTable;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;

    @Column(nullable=false)
    private int fio;

    @Column(length=255)
    private String kod;

    private boolean locked;

    @Column(nullable=false, length=100)
    private String login;

    @Column(nullable=false, length=50)
    private String password;

    private int regoin;

    @Column(nullable=false)
    private Integer rights;

    @Column(length=255)
    private String telephone;

    public User() {
    }

    public Integer getId() {

        return this.id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getBaza() {
        return this.baza;
    }

    public void setBaza(String baza) {
        this.baza = baza;
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

    public int getFio() {
        return this.fio;
    }

    public void setFio(int fio) {
        this.fio = fio;
    }

    public String getKod() {
        return this.kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public boolean getLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRegoin() {
        return this.regoin;
    }

    public void setRegoin(int regoin) {
        this.regoin = regoin;
    }

    public Integer getRights() {

        return this.rights;
    }

    public void setRights(Integer rights) {
        this.rights = rights;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}