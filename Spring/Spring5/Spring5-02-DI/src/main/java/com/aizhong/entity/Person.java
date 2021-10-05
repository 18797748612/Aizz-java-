package com.aizhong.entity;

import java.util.*;

public class Person {
    private String name;
    private Integer age;
    private String[] hobbys;
    private List<String> emails;
    private Set<String> qqs;
    private Map<String, String> addrs;
    private Properties p;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getHobbys() {
        return hobbys;
    }

    public void setHobbys(String[] hobbys) {
        this.hobbys = hobbys;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Set<String> getQqs() {
        return qqs;
    }

    public void setQqs(Set<String> qqs) {
        this.qqs = qqs;
    }

    public Map<String, String> getAddrs() {
        return addrs;
    }

    public void setAddrs(Map<String, String> addrs) {
        this.addrs = addrs;
    }

    public Properties getP() {
        return p;
    }

    public void setP(Properties p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbys=" + Arrays.toString(hobbys) +
                ", emails=" + emails +
                ", qqs=" + qqs +
                ", addrs=" + addrs +
                ", p=" + p +
                '}';
    }
}
