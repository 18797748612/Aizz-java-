package com.aizhong.entity;

public class User {
    private Integer age;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(Integer age) {
        this.age = age;
    }

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
    public User(String name, Integer age) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
