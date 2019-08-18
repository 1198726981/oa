package org.fkit.oa.identity.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "OA_ID_POPEDOM")
public class Popedom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    /*
    * 权限与模块多对一关联
    * ？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
    * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Module.class)
    @JoinColumn(name = "MODULE_CODE",referencedColumnName = "CODE",foreignKey = @ForeignKey(name = "FK_POPEDOM_MODULE"))
    private Module module;

    /*
     * 权限与操作多对一关联
     * ??????????????????????????????????????????????
     * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Module.class)
    @JoinColumn(name = "OPERA_CODE",referencedColumnName = "CODE",foreignKey = @ForeignKey(name = "FK_POPEDOM_OPERA"))
    private Module opera;

    /*
     * 权限与角色多对一关联
     * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Role.class)
    @JoinColumn(name = "ROLE_ID",referencedColumnName = "ID",foreignKey = @ForeignKey(name = "FK_POPEDOM_ROLE"))
    private Role role;

    /*
     * 权限创建人与用户多对一关联
     * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "CREATER",referencedColumnName = "USER_ID",foreignKey = @ForeignKey(name = "FK_POPEDOM_CREATER"))
    private User creater;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Module getOpera() {
        return opera;
    }

    public void setOpera(Module opera) {
        this.opera = opera;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
