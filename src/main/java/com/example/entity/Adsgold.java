package com.example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: jiangm
 * @Description: LHD库下adsgold表、实体类
 * @CreateDate: 2018/8/29 14:59
 * @Version: 空管PBCS平台事后分析系统
 */
@Entity
@Table(name="adsgold")
public class Adsgold {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String atsp;
    private String an;
    private String actype;
    private String operater;
    private String msgdate;
    private String rgs;
    private String msgtype;
    private String lat;
    private String lon;
    private Date sendtime;
    private Date dwnmsgtime;
    private Integer transtime;
    private String media;

//    private Data uploadDate;

    public String getAtsp() {
        return atsp;
    }

    public void setAtsp(String atsp) {
        this.atsp = atsp;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getActype() {
        return actype;
    }

    public void setActype(String actype) {
        this.actype = actype;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getMsgdate() {
        return msgdate;
    }

    public void setMsgdate(String msgdate) {
        this.msgdate = msgdate;
    }

    public String getRgs() {
        return rgs;
    }

    public void setRgs(String rgs) {
        this.rgs = rgs;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getDwnmsgtime() {
        return dwnmsgtime;
    }

    public void setDwnmsgtime(Date dwnmsgtime) {
        this.dwnmsgtime = dwnmsgtime;
    }

    public Integer getTranstime() {
        return transtime;
    }

    public void setTranstime(Integer transtime) {
        this.transtime = transtime;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Adsgold{" +
                "atsp='" + atsp + '\'' +
                ", an='" + an + '\'' +
                ", actype='" + actype + '\'' +
                ", operater='" + operater + '\'' +
                ", msgdate='" + msgdate + '\'' +
                ", rgs='" + rgs + '\'' +
                ", msgtype='" + msgtype + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", sendtime=" + sendtime +
                ", dwnmsgtime=" + dwnmsgtime +
                ", transtime=" + transtime +
                ", media='" + media + '\'' +
                '}';
    }
}
