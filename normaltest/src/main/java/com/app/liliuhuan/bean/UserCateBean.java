package com.app.liliuhuan.bean;

/**
 * author: liliuhuan
 * date：2018/7/19
 * version:1.0.0
 * description: UserCateBean${DES}
 */
public class UserCateBean {

    /**
     * id : 2
     * name : name
     * createtime : 1528429740
     * updatetime : 1529458123
     */

    private int id;
    private String name;
    private int createtime;
    private int updatetime;

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

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public int getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "id -> " + id+"    name->" + name;
    }
}
