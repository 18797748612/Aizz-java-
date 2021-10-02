package com.aizhong.pojo;

public class Cat extends Animal{
    public String name = "Cat";
    private int age = 10;
    public void eat(){
        System.out.println("Cat eat");
    }
    public void sleep(){
        System.out.println("Cat sleep");
    }
}
