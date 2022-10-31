package com.example.dulichnote.model;

public class PlaceItem {

    public final String name;
    public final String desc;
    public final String linkPhoto;

    public PlaceItem(String name, String desc, String linkPhoto) {
        this.name = name;
        this.desc = desc;
        this.linkPhoto = linkPhoto;
    }

    @Override
    public String toString() {
        return "PlaceItem{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", linkPhoto='" + linkPhoto + '\'' +
                '}';
    }
}
