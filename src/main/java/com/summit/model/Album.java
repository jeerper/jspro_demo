package com.summit.model;

import java.util.List;

/**
 * @author: jeerper
 * @date: 2021-07-28 10:09:50
 */
public class Album {
    private List<Track> trackList;
    public Album() {
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}
