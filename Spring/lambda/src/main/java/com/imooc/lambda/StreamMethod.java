package com.imooc.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMethod {

    /**
     * mapToInt()转换为整数
     * filter()按条件过滤
     * sum()只适用于数组中为数字
     */
    @Test
    public void case1() {
        int sum = Arrays.asList("1", "2", "3", "4", "5").stream()
                .mapToInt(i -> Integer.parseInt(i))
                .filter(n -> n % 2 == 0)
                .sum();
        System.out.println(sum);
    }

    /**
     *map()将函数应用在每个元素上
     *collect()将元素作为一个集合返回
     */
    @Test
    public void case2() {
        List<String> list = Arrays.asList("lily", "smith", "martin").stream()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     *distinct()去重
     *sorted()排序
     */
    @Test
    public void case3() {
        List<Integer> list = Arrays.asList(1, 60, 38, 21, 51, 60).stream()
                .distinct()
                .sorted((a, b) -> a - b)
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
