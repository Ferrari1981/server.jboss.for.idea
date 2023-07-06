package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "modificationserver", schema = "dbo", catalog = "storage")
@DynamicUpdate
@OptimisticLocking(type= OptimisticLockType.DIRTY)
public class ModificationServerEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 2147483647)
    private String name;


    @Basic
    @Column(name = "versionserver", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    private Date versionserver;
    @Basic
    @Column(name = "type_query", nullable = false, length = 100)
    private String typeQuery;
    @Basic
    @Column(name = "description", nullable = false, length = 2147483647)
    private String description;
    @Basic
    @Column(name = "versionserverversion", nullable = true, precision = 0)
    private Long versionserverversion;




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

    public Date getVersionserver() {
        return versionserver;
    }

    public void setVersionserver(Date versionserver) {
        this.versionserver = versionserver;
    }

    public String getTypeQuery() {
        return typeQuery;
    }

    public void setTypeQuery(String typeQuery) {
        this.typeQuery = typeQuery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersionserverversion() {
        return versionserverversion;
    }

    public void setVersionserverversion(Long versionserverversion) {
        this.versionserverversion = versionserverversion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModificationServerEntity that = (ModificationServerEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (versionserver != null ? !versionserver.equals(that.versionserver) : that.versionserver != null)
            return false;
        if (typeQuery != null ? !typeQuery.equals(that.typeQuery) : that.typeQuery != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (versionserverversion != null ? !versionserverversion.equals(that.versionserverversion) : that.versionserverversion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (versionserver != null ? versionserver.hashCode() : 0);
        result = 31 * result + (typeQuery != null ? typeQuery.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (versionserverversion != null ? versionserverversion.hashCode() : 0);
        return result;
    }
}
