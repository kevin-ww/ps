package com.philips.cn.hr.pps.common;

/**
 * Created by kevin on 2015/1/8.
 */
public class Column {

    private int idx;

    private String name;

    private String comment;

    public Column(int idx, String name) {
        this(idx, name, null);
    }

    public Column(int idx, String name, String comment) {
        this.idx = idx;
        this.name = name;
        this.comment = comment;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
