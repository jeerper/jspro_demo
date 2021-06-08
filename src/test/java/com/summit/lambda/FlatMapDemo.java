package com.summit.lambda;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * @author: jeerper
 * @date: 2021-06-08 10:25:31
 */
public class FlatMapDemo {
    @Test
    public void flatMapDemoTest(){
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
}
