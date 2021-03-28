package com.summit.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DiGuiTest {

    @Test
    public void testDiGui01(){
        int sum = sum(10);
        System.out.println("sum--->"+sum); //使用递归计算1-10的累加和
        int jiechen = jiechen(10);
        System.out.println("jiechen--->"+jiechen); //使用递归计算1-10的阶乘
    }
    public int sum(int i){
        if (i==1){
            return i;
        }else {
            return  i +sum(i-1);
        }

    }

    public int jiechen(int n){
        if (n==1){
            return n;
        }else {
            return  n*sum(n-1);
        }

    }
}
