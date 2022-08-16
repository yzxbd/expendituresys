package com.yzxbd.bean;

public class SearchCondition {
    private String cname;
    private String pname;
    private String creater;
    private String createtime;

    public SearchCondition() {
    }

    public SearchCondition(String cname, String pname, String creater, String createtime) {
        this.cname = cname;
        this.pname = pname;
        this.creater = creater;
        this.createtime = createtime;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "cname='" + cname + '\'' +
                ", pname='" + pname + '\'' +
                ", creater='" + creater + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
