package org.fkit.oa.identity.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OA_ID_ROLE")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name = "NAME",length = 50)
    private String name;

    @Column(name = "REMARK",length = 300)
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "CREATER",referencedColumnName = "USER_ID",foreignKey = @ForeignKey(name = "FK_ROLE_CREATER"))
    private User creater;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "MODIFIER",referencedColumnName = "USER_ID",foreignKey = @ForeignKey(name = "FK_ROLE_MODIFIER"))
    private User modifier;

    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    /*
    * 角色与用户存在多对多关联
    * 多对多关系都是通过创建中间表来进行关联处理
    * @JoinTable注解来指定中间表,第一个name为中间表名
    * joinColumns 用来指定中间表中关联自己ID的字段
    * inverseJoinColumns 用来指定中间表中关联对方ID的字段
    * */
    @ManyToMany(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinTable(name = "OA_ID_USER_ROLE",joinColumns = @JoinColumn(name = "ROLE_ID",referencedColumnName = "ID"),inverseJoinColumns = @JoinColumn(name = "USER_ID",referencedColumnName = "USER_ID"))
    private Set<User> users=new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getModifier() {
        return modifier;
    }

    public void setModifier(User modifier) {
        this.modifier = modifier;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
