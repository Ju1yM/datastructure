package com.className.demo;

public class person implements Comparable<person> {

    String name;
    int age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(person p) {
        return -this.name.compareTo(p.getName());
    }

}
