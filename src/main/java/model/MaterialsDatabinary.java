package model;

import jakarta.persistence.*;
import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

@Entity
@Table(name = "materials_databinary", schema = "dbo", catalog = "storage")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate
public class MaterialsDatabinary   implements Serializable  {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "image", nullable = true)
    private byte[] image;

    @Column(name = "files", nullable = true)
    private byte[] files;
    @Basic
    @Column(name = "date_update", nullable = true)
    private Date dateUpdate;
    @Basic
    @Column(name = "uuid", nullable = false, precision = 0)
    private long uuid;
    @Basic
    @Column(name = "parent_uuid", nullable = true, precision = 0)
    private Long parentUuid;
    @Basic
    @Column(name = "user_update", nullable = true)
    private Integer userUpdate;
    @Basic
    @Column(name = "current_table", nullable = true, precision = 0)
    private Long currentTable;



    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public Long getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(Long parentUuid) {
        this.parentUuid = parentUuid;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Long getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(Long currentTable) {
        this.currentTable = currentTable;
    }


}
