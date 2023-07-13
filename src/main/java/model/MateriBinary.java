package model;



import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vid_tc database table.
 *
 */
@Entity
@Table(name="materials_databinary",catalog="storage",schema="dbo")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate
public class MateriBinary implements Serializable {
    private static final long serialVersionUID = 1L;


    public MateriBinary() {

    }
    @Basic
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "uuid", nullable = false, precision = 0)
    private long uuid;

    @Lob
    protected java.sql.Blob image;



    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {

        this.uuid = uuid;
    }
    public java.sql.Blob getImage() {

        return image;
    }

    public void setImage(java.sql.Blob image) {
        this.image = image;
    }

}