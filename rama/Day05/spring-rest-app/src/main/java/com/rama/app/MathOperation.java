package com.rama.app;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MathOperation {
 int n1=0;
 int n2=1;
 int n3=0;
 List<Integer> result= new ArrayList<>(Arrays. asList(0,1)) ;

    public List<Integer> getFibbonaciNumber(int count){
        if(count>0){
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            System.out.print(" "+n3);
            result.add(n3);
            getFibbonaciNumber(count-1);

        }
        return result;
    }

    public String addString(String str1,String str2){
        return str1+str2;
    }
}
