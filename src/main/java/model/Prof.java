package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "prof", schema = "dbo", catalog = "storage")
@NamedQuery(name="Prof.findAll", query="SELECT pr FROM Prof pr")
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate

public class Prof implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "user_update", nullable = false)
    private int userUpdate;

    @Column(name = "date_update", nullable = false)
    private Date dateUpdate;

    @Column(name = "current_table", nullable = true, precision = 0)
    private Long currentTable;

    @Column(name = "uuid", nullable = true, precision = 0)
    private Long uuid;

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

    public int getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Long getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(Long currentTable) {
        this.currentTable = currentTable;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }


}
