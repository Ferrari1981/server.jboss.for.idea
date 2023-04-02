package model;



import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the view_data_modification database table.
 *
 */
@Entity
@Table(name="view_data_modification")
@NamedQuery(name="ViewDataModification.findAll", query="SELECT v FROM ViewDataModification v")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.DIRTY)
@org.hibernate.annotations.DynamicUpdate
public class ViewDataModification implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name="ID_ПРАВ", nullable=false)
    private Integer idПрав;

    @Column(name="[ИМЯ ПРАВ]", nullable=false, length=100)
    private String имя_прав;

    @Column(name="[ИМЯ ИЗ МОДИФИКАЦИИ СЕРВЕР]", nullable=false, length=200)
    private String имя_из_модификации_сервер;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="[ДАТА ВЕРСИИ СЕРВЕРА]", nullable=false)
    private Date дата_версии_сервера;

    @Column(name="ID_СОРТИРОВКА_ТАБЛИЦ", nullable=false)
    private Integer idСортировкаТаблиц;

    @Column(name="type_query", nullable=false, length=100)
    private String typeQuery;

    @Column(nullable=false, length=200)
    private String description;

    @Column(name="[ТЕКУЩАЯ ВЕРСИЯ  ТАБЛИЦЫ]", precision=36)
    private BigDecimal текущая_версия__таблицы;









    public ViewDataModification() {
    }

    public Date getДата_версии_сервера() {
        return this.дата_версии_сервера;
    }

    public void setДата_версии_сервера(Date дата_версии_сервера) {
        this.дата_версии_сервера = дата_версии_сервера;
    }

    public String getИмя_из_модификации_сервер() {
        return this.имя_из_модификации_сервер;
    }

    public void setИмя_из_модификации_сервер(String имя_из_модификации_сервер) {
        this.имя_из_модификации_сервер = имя_из_модификации_сервер;
    }

    public String getИмя_прав() {
        return this.имя_прав;
    }

    public void setИмя_прав(String имя_прав) {
        this.имя_прав = имя_прав;
    }

    public BigDecimal getТекущая_версия__таблицы() {
        return this.текущая_версия__таблицы;
    }

    public void setТекущая_версия__таблицы(BigDecimal текущая_версия__таблицы) {
        this.текущая_версия__таблицы = текущая_версия__таблицы;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdПрав() {
        return this.idПрав;
    }

    public void setIdПрав(Integer idПрав) {
        this.idПрав = idПрав;
    }

    public Integer getIdСортировкаТаблиц() {
        return this.idСортировкаТаблиц;
    }

    public void setIdСортировкаТаблиц(Integer idСортировкаТаблиц) {
        this.idСортировкаТаблиц = idСортировкаТаблиц;
    }

    public String getTypeQuery() {
        return this.typeQuery;
    }

    public void setTypeQuery(String typeQuery) {
        this.typeQuery = typeQuery;
    }

}