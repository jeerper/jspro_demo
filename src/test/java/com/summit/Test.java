package com.summit;

import com.summit.model.Track;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: jeerper
 * @date: 2021-08-20 14:01:44
 */
public class Test {
    public static void main(String[] args) {
        List<Track> tacks = new ArrayList<>();
        Track track = tacks.stream().max(Comparator.comparing(Track::getLength)).get();
    }
}
