package com.example.dulichnote.model;

public class PlaceItem {

    public final String name;
    public final String desc;
    public final String linkPhoto;
    public  final double lat, lgn;


    public PlaceItem(String name, String desc, String linkPhoto, double lat, double lgn) {
        this.name = name;
        this.desc = desc;
        this.linkPhoto = linkPhoto;
        this.lat = lat;
        this.lgn = lgn;
    }

    @Override
    public String toString() {
        return "PlaceItem{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", linkPhoto='" + linkPhoto + '\'' +
                ", lat=" + lat +
                ", lgn=" + lgn +
                '}';
    }
}
