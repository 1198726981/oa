package org.fkit.oa.identity.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OA_ID_JOB")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Job implements Serializable {
    @Id
    @Column(name = "CODE",length = 100)
    private String code;

    @Column(name = "NAME",length = 50)
    private String name;

    /*
    * 职位说明
    * */
    @Column(name = "REMARK",length = 300)
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
