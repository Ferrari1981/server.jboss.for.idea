package model;



import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the chats database table.
 *
 */
@Entity
@Table(name="chats",catalog="storage",schema="dbo")
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
@org.hibernate.annotations.OptimisticLocking(
        type = OptimisticLockType.VERSION)
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name="current_table")
    private BigDecimal currentTable;

    @Version
    // Необязательно: @org.hibernate.annotations.Type(type = "dbtimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_update")
    private Date dateUpdate;

    @Column(name="id_user")
    private int idUser;

    private String name;

    @Column(name="user_update")
    private int userUpdate;

    private BigDecimal uuid;

    @Column(name="uuid_parent")
    private BigDecimal uuidParent;


    /*
     * @ManyToOne (fetch = FetchType.LAZY )
     *
     * @JoinColumn(name="uuid" , updatable = false, insertable =
     * false,referencedColumnName = "chat_uuid") private DataChat dataChat;
     */



    /*
     * public DataChat getDataChat() { return dataChat; }
     *
     * public void setDataChat(DataChat dataChat) { this.dataChat = dataChat; }
     */
    public Chat() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserUpdate() {
        return this.userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getUuidParent() {
        return this.uuidParent;
    }

    public void setUuidParent(BigDecimal uuidParent) {
        this.uuidParent = uuidParent;
    }









}