package com.imooc;

/**
 * Created by Administrator on 2017/12/9.
 */
public class Person {
    private String name;
    private Integer age;

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

    public void say(){
        System.out.println("say hello");
    }
    public String say(String msg){
        System.out.println("say "+msg);
        return "xx";
    }
    private Integer say(String msg,Integer age){
        System.out.println("say "+msg+age);
        return age;
    }
    public Person(String name,Integer age){

    }
    public Person(){

    }
}
