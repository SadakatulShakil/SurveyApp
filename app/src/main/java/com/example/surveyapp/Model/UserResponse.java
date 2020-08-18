package com.example.surveyapp.Model;

import java.io.Serializable;

public class UserResponse implements Serializable {
    private String uAnsId;
    private String timeStamp;
    private String uTextAns;
    private String uNumberAns;
    private String uMultiAns;
    private String uDropDownAns;
    private String uCheckBoxAns;


    public UserResponse() {
    }

    public UserResponse(String uAnsId, String timeStamp, String uTextAns, String uNumberAns, String uMultiAns, String uDropDownAns, String uCheckBoxAns) {
        this.uAnsId = uAnsId;
        this.timeStamp = timeStamp;
        this.uTextAns = uTextAns;
        this.uNumberAns = uNumberAns;
        this.uMultiAns = uMultiAns;
        this.uDropDownAns = uDropDownAns;
        this.uCheckBoxAns = uCheckBoxAns;
    }

    public UserResponse(String timeStamp, String uTextAns, String uNumberAns, String uMultiAns, String uDropDownAns, String uCheckBoxAns) {
        this.timeStamp = timeStamp;
        this.uTextAns = uTextAns;
        this.uNumberAns = uNumberAns;
        this.uMultiAns = uMultiAns;
        this.uDropDownAns = uDropDownAns;
        this.uCheckBoxAns = uCheckBoxAns;
    }


    public String getuAnsId() {
        return uAnsId;
    }

    public void setuAnsId(String uAnsId) {
        this.uAnsId = uAnsId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getuTextAns() {
        return uTextAns;
    }

    public void setuTextAns(String uTextAns) {
        this.uTextAns = uTextAns;
    }

    public String getuNumberAns() {
        return uNumberAns;
    }

    public void setuNumberAns(String uNumberAns) {
        this.uNumberAns = uNumberAns;
    }

    public String getuMultiAns() {
        return uMultiAns;
    }

    public void setuMultiAns(String uMultiAns) {
        this.uMultiAns = uMultiAns;
    }

    public String getuDropDownAns() {
        return uDropDownAns;
    }

    public void setuDropDownAns(String uDropDownAns) {
        this.uDropDownAns = uDropDownAns;
    }

    public String getuCheckBoxAns() {
        return uCheckBoxAns;
    }

    public void setuCheckBoxAns(String uCheckBoxAns) {
        this.uCheckBoxAns = uCheckBoxAns;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "timeStamp='" + timeStamp + '\'' +
                ", uTextAns='" + uTextAns + '\'' +
                ", uNumberAns='" + uNumberAns + '\'' +
                ", uMultiAns='" + uMultiAns + '\'' +
                ", uDropDownAns='" + uDropDownAns + '\'' +
                ", uCheckBoxAns='" + uCheckBoxAns + '\'' +
                '}';
    }
}
