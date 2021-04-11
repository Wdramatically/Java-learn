package com.imooc.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionSample {
    public static void main(String[] args) {
        Function<List<Integer>, Integer> maxValueFunction = list -> {
            int max = 0;
            for (Integer i : list) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        };
        Integer maxValue = maxValueFunction.apply(Arrays.asList(1, 15, 200, 5, 36));
        System.out.println("集合中的最大值：" + maxValue);
    }
}
