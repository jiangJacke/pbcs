package com.example.entity;

import javax.persistence.*;

/**
 * @Author: jiangm
 * @Description:
 * @CreateDate: 2018/10/10 9:44
 * @Version: 空管PBCS平台事后分析系统
 */
@Entity
@Table(name="cpdlcgold")
public class CpdlcEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String atsp;
    private String an;
    private String actype;
    private String msgdate;
    private String masrgs;
    private String opsrgs;
    private String upmsgtime;
    private String mastime;
    private Integer mastrip;
    private String fmstime;
    private String dwnmsgtime;
    private Integer acps;
    private Integer downtime;
    private String upid;
    private String dwnid;
    private Integer actp;
    private Integer acp;
    private Integer port;
    private String media;
    private String fi;
    private String ufi;
    private String opactype;
    private String standard;
    private String timeStandard;
    private String beginDate;
    private String endDate;
    private String OPERATER;

    public String getOPERATER() {
        return OPERATER;
    }

    public void setOPERATER(String OPERATER) {
        this.OPERATER = OPERATER;
    }

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

    public String getMsgdate() {
        return msgdate;
    }

    public void setMsgdate(String msgdate) {
        this.msgdate = msgdate;
    }

    public String getMasrgs() {
        return masrgs;
    }

    public void setMasrgs(String masrgs) {
        this.masrgs = masrgs;
    }

    public String getOpsrgs() {
        return opsrgs;
    }

    public void setOpsrgs(String opsrgs) {
        this.opsrgs = opsrgs;
    }

    public String getUpmsgtime() {
        return upmsgtime;
    }

    public void setUpmsgtime(String upmsgtime) {
        this.upmsgtime = upmsgtime;
    }

    public String getMastime() {
        return mastime;
    }

    public void setMastime(String mastime) {
        this.mastime = mastime;
    }

    public Integer getMastrip() {
        return mastrip;
    }

    public void setMastrip(Integer mastrip) {
        this.mastrip = mastrip;
    }

    public String getFmstime() {
        return fmstime;
    }

    public void setFmstime(String fmstime) {
        this.fmstime = fmstime;
    }

    public String getDwnmsgtime() {
        return dwnmsgtime;
    }

    public void setDwnmsgtime(String dwnmsgtime) {
        this.dwnmsgtime = dwnmsgtime;
    }

    public Integer getAcps() {
        return acps;
    }

    public void setAcps(Integer acps) {
        this.acps = acps;
    }

    public Integer getDowntime() {
        return downtime;
    }

    public void setDowntime(Integer downtime) {
        this.downtime = downtime;
    }

    public String getUpid() {
        return upid;
    }

    public void setUpid(String upid) {
        this.upid = upid;
    }

    public String getDwnid() {
        return dwnid;
    }

    public void setDwnid(String dwnid) {
        this.dwnid = dwnid;
    }

    public Integer getActp() {
        return actp;
    }

    public void setActp(Integer actp) {
        this.actp = actp;
    }

    public Integer getAcp() {
        return acp;
    }

    public void setAcp(Integer acp) {
        this.acp = acp;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getFi() {
        return fi;
    }

    public void setFi(String fi) {
        this.fi = fi;
    }

    public String getUfi() {
        return ufi;
    }

    public void setUfi(String ufi) {
        this.ufi = ufi;
    }

    public String getOpactype() {
        return opactype;
    }

    public void setOpactype(String opactype) {
        this.opactype = opactype;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getTimeStandard() {
        return timeStandard;
    }

    public void setTimeStandard(String timeStandard) {
        this.timeStandard = timeStandard;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CpdlcEntity{" +
                "atsp='" + atsp + '\'' +
                ", an='" + an + '\'' +
                ", actype='" + actype + '\'' +
                ", msgdate='" + msgdate + '\'' +
                ", masrgs='" + masrgs + '\'' +
                ", opsrgs='" + opsrgs + '\'' +
                ", upmsgtime='" + upmsgtime + '\'' +
                ", mastime='" + mastime + '\'' +
                ", mastrip=" + mastrip +
                ", fmstime='" + fmstime + '\'' +
                ", dwnmsgtime='" + dwnmsgtime + '\'' +
                ", acps=" + acps +
                ", downtime=" + downtime +
                ", upid='" + upid + '\'' +
                ", dwnid='" + dwnid + '\'' +
                ", actp=" + actp +
                ", acp=" + acp +
                ", port=" + port +
                ", media='" + media + '\'' +
                ", fi='" + fi + '\'' +
                ", ufi='" + ufi + '\'' +
                ", opactype='" + opactype + '\'' +
                ", standard='" + standard + '\'' +
                ", timeStandard='" + timeStandard + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", OPERATER='" + OPERATER + '\'' +
                '}';
    }
}
