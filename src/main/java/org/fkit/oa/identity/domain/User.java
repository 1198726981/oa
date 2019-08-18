package org.fkit.oa.identity.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OA_ID_USER",indexes = {@Index(columnList = "NAME",name = "IDX_USER_NAME")})
//@Index注解可以理解成一个索引的声明，name为索引名称
/*
* @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)为需要缓存的类添加缓存标示,缓存方案为读写模式
* 读写模式在更新缓存的时候会把缓存里面的数据换成一个锁，其它事务如果去取相应的缓存数据，发现被锁了，直接就去数据库查询；
* 一级缓存为session事务级别、仅限于当前session、生命周期随事务结束而结束，二级缓存为sessionfactory应用级别、所有session共享、生命周期为整个应用结束而结束
 * */
public class User implements Serializable {

    @Id
    @Column(name = "USER_ID",length = 50)
    private String userId;

    @Column(name = "PASS_WORD",length = 50)
    private String passWord;

    @Column(name = "NAME",length = 50)
    private String name;

    @Column(name = "SEX")
    private Short sex=1;
    /*
    * 用户与部门存在多对一关联 部门 FK(OA_ID_DEPT)
    * FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载。
    * FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载。
    * 关联的实体对象通常是懒加载
    * @JoinColumn 注解的作用：用来指定与所操作实体或实体集合相关联的数据库表中的列字段,要写在主控方（指能够主动改变关联关系的一方）
    * 第一个name不管是@ManyToOne还是@OneToMany都指的是外键表即多的一方的列名，referencedColumnName则为关联类（表）在数据库中的关联字段名，第三个属性则为外键名
    * 如果你不加 @JoinColumn(name=)这个注释的话,那么JBOSS就会自动帮你生成一张中间表
    * 推荐另外生成一个中间表好一些，因为这样的话，对原来两张表的结构不对造成任何影响。在遗留系统的时候很多用，有些时候，一些表都是以前就建好了的，
    * 要改表的结构是不太可能的，所以这个时候中间的表就显得很重要了，它可以在不侵入原来表的情况下构建出一种更清淅更易管理的关系
    * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Dept.class)
    @JoinColumn(name = "DEPT_ID",referencedColumnName = "ID",foreignKey = @ForeignKey(name = "FK_USER_DEPT"))
    private Dept dept;
    //select u from User u where u.dept.id=?

   /*
   * 用户与职位存在多对一关联 职位 FK(OA_ID_JOB)
   * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Job.class)
    @JoinColumn(name = "JOB_CODE",referencedColumnName = "CODE",foreignKey = @ForeignKey(name = "FK_USER_JOB"))
    private Job job;

    @Column(name = "EMAIL",length = 50)
    private String email;

    @Column(name = "TEL",length = 50)
    private String tel;

    @Column(name = "PHONE",length = 50)
    private String phone;

    @Column(name = "QQ_NUM",length = 50)
    private String qqNum;

    @Column(name = "QUESTION")
    private Short question;

    @Column(name = "ANSWER",length = 200)
    private String answer;

    /*
     * 状态 0新建，1审核，2不通过审核，3冻结
     * */
    @Column(name = "STATUS")
    private Short status=0;

    /*
    * 用户创建人与用户存在多对一关联(FK(OA_ID_USER))
    * 更改外键约束名
    * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "CREATER",referencedColumnName = "USER_ID",foreignKey = @ForeignKey(name = "FK_USER_CREATER"))
    private User creater;

    /*
    * @Temporal(TemporalType.TIMESTAMP)
    * 在页面端取值：2011-04-12 22:51:34.0
    * */
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    /*
    * 用户修改人与用户存在多对一关联(FK(OA_ID_USER))
    * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "MODIFIER",referencedColumnName = "USER_ID",foreignKey = @ForeignKey(name = "FK_USER_MODIFIER"))
    private User modifier;

    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    /*
    * 部门审核人与用户存在多对一关联(FK(OA_ID_USER))
    * */
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "CHECKER",referencedColumnName = "USER_ID",foreignKey = @ForeignKey(name = "FK_USER_CHECKER"))
    private User checker;

    @Column(name = "CHECK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkDate;

    /*
    * 用户与角色存在多对多关联
    * mappedBy双向关系必须设置，避免双方都建立外键字段,@mappedBy所在实体是关系的被拥有方即关系在Role表中维护,值为拥有方的属性
    * */
    @ManyToMany(fetch = FetchType.LAZY,targetEntity = Role.class,mappedBy = "users")
    private Set<Role>roles=new HashSet<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public Short getQuestion() {
        return question;
    }

    public void setQuestion(Short question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
