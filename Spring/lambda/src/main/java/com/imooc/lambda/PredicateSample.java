package com.imooc.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateSample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("java", "python", "php", "HTML");
        filter(list,s -> s.indexOf("a") != -1);
    }
    public static void filter(List<String> list, Predicate<String> predicate){
        for (String s: list){
            if (predicate.test(s)){
                System.out.println(s);
            }
        }
    }

}
