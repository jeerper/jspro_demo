package com.summit.model;

/**
 * @author: jeerper
 * @date: 2021-07-28 10:11:07
 */
public class Track {
    private String name ;
    private int length;

    public Track() {
    }

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

