package com.amadeus.pau.mkt_exp.pojo;

public class Market {
int id;
String mktName;
String country;
String state;
String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMktName() {
        return mktName;
    }

    public void setMktName(String mktName) {
        this.mktName = mktName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
