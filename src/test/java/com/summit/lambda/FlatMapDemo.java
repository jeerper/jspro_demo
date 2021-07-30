package com.summit.lambda;

import com.alibaba.fastjson.JSON;
import com.summit.dao.entity.User;
import com.summit.model.Album;
import com.summit.model.Track;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * @author: jeerper
 * @date: 2021-06-08 10:25:31
 */
public class FlatMapDemo {
    @Test
    public void flatMapDemoTest1(){
        List<String> longCodes = new ArrayList<>();
        longCodes.add("5559f2a9b7124fbcbf7282f312c50b7d|34bb19c9c0bb4f3c9a3001f5a4270c6b|bb6a769f1b9c4fa59a0a56c32c3f8f33");
        longCodes.add("fb8eff1c3dc846179369cddc48fd8bf4|136b073c7e3e469ab42e5624f2456f71");
        longCodes.add("9da7edbbbd7c4d43aed4ff467f47fbf1");
        List<String> pLedgerIds = longCodes.stream().flatMap(item -> Arrays.stream(item.split("\\|"))).distinct().collect(Collectors.toList());
        System.out.println("-----pLedgerIds----"+ JSON.toJSONString(pLedgerIds));
        List<String> ledgerIds = pLedgerIds.stream().filter(item -> {
            if ("5559f2a9b7124fbcbf7282f312c50b7d".equals(item) || "34bb19c9c0bb4f3c9a3001f5a4270c6b".equals(item)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println("-----ledgerIds----"+ JSON.toJSONString(ledgerIds));
        ListIterator<String> iterator = ledgerIds.listIterator();
        while (iterator.hasNext()){
            String temp = iterator.next();
            System.out.println("---temp---"+temp);
        }
    }

    @Test
    public void flatMapDemoTest2(){
        List<User> users = new ArrayList<>();
        users.add(new User("1","张飞",10.1));
        users.add(new User("1","张飞",11.1));
        users.add(new User("2","关羽",12.1));
        users.add(new User("2","曹操",13.1));
        users.add(new User("3","李明",14.1));
        users.add(new User("3","张三",15.1));
        Map<String, List<User>> userMap = users.stream().collect(Collectors.groupingBy(User::getId));
        System.out.println("---userMap---"+JSON.toJSONString(userMap));
        List<String> names = userMap.values().stream().flatMap(item -> item.stream().map(User::getName).distinct()).collect(Collectors.toList());
//        List<String> names = userMap.values().stream().flatMap(item -> {
//            Stream<String> stream = item.stream().map(User::getName);
//            return  stream;
//        }).collect(Collectors.toList());
//        List<String> names = userMap.values().stream().flatMap(item->{
//            //List<String> nameList = item.stream().map(User::getName).collect(Collectors.toList());
//            List<String> nameList = item.stream().map(a->{
//                return a.getName();
//            }).collect(Collectors.toList());
//            String[] nameData = nameList.toArray(new String[nameList.size()]);
//            Stream<String> stream = Arrays.stream(nameData);
//            return stream;
//        }).collect(Collectors.toList());
        System.out.println("---names---"+JSON.toJSONString(names));
//        ---userMap---{"1":[{"id":"1","money":10.1,"name":"张飞"},{"id":"1","money":11.1,"name":"刘备"}],"2":[{"id":"2","money":12.1,"name":"关羽"},{"id":"2","money":13.1,"name":"曹操"}],"3":[{"id":"3","money":14.1,"name":"李明"},{"id":"3","money":15.1,"name":"张三"}]}
//        ---names---["张飞","刘备","关羽","曹操","李明","张三"]

    }

    /**
     * 假定选定一组专辑，找出其中所有长度大于 1 分钟的曲目名称。首先
     * 初始化一个 Set 对象，用来保存找到的曲目名称。然后使用 for 循环遍历所有专辑，每次
     * 循环中再使用一个 for 循环遍历每张专辑上的每首曲目，检查其长度是否大于 60 秒，如
     * 果是，则将该曲目名称加入 Set 对象。
     * @param
     * @return
     */
    @Test
    public void findLongTracks_1(){
        Track track1 =  new Track("张飞",1000);
        Track track2 =  new Track("刘备",20);
        Track track3 =  new Track("赵云",61);
        Track track4 =  new Track("曹操",59);
        List<Track> tracks_12 = new ArrayList<>();
        tracks_12.add(track1);
        tracks_12.add(track2);
        List<Track> tracks_34 = new ArrayList<>();
        tracks_34.add(track3);
        tracks_34.add(track4);
        List<Album> albums = new ArrayList<>();
        Album album1 = new Album();
        album1.setTrackList(tracks_12);
        Album album2 = new Album();
        album2.setTrackList(tracks_34);
        albums.add(album1);
        albums.add(album2);
        System.out.println("---albums---"+JSON.toJSONString(albums));
        /**
         * [
         *        {
         * 		"trackList": [
         *            {
         * 				"length": 1000,
         * 				"name": "张飞"
         *            },
         *            {
         * 				"length": 20,
         * 				"name": "刘备"
         *            }
         * 		]
         *    },
         *    {
         * 		"trackList": [
         *            {
         * 				"length": 61,
         * 				"name": "赵云"
         *            },
         *            {
         * 				"length": 59,
         * 				"name": "曹操"
         *            }
         * 		]
         *    }
         * ]
         */
        Set<String> set = albums.stream().flatMap(item -> item.getTrackList().stream())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName())
                .collect(toSet());
        System.out.println("---set---"+JSON.toJSONString(set));

    }
    public Set<String> findLongTracks_2(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        for(Album album : albums) {
            for (Track track : album.getTrackList()) {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        return trackNames;
    }
}
