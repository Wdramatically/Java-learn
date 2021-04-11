package com.imooc.lambda;

public class LambdaSample {

    public static void main(String[] args) {
        MathOperation addition = (a,b) -> a+b+0f;
        Float operation = addition.Operation(1, 5);
        System.out.println(operation);
    }
}
