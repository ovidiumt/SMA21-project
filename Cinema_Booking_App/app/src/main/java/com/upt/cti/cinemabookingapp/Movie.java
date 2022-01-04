package com.upt.cti.cinemabookingapp;

import java.util.HashMap;

public class Movie {

    private String name;
    private String imgUrl;
    private String firstHour;
    private String secondHour;
    private String thirdHour;
    private String id;

    public Movie(){

    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getFirstHour(){
        return firstHour;
    }

    public String getSecondHour(){
        return secondHour;
    }

    public String getThirdHour(){
        return thirdHour;
    }

    public String getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setFirstHour(String firstHour){
        this.firstHour = firstHour;
    }

    public void setSecondHour(String secondHour){
        this.secondHour = secondHour;
    }

    public void setThirdHour(String thirdHour){
        this.thirdHour = thirdHour;
    }

    public void setId(String id){
        this.id = id;
    }
}
