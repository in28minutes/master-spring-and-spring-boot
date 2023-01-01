package com.in28minutes.learnspringframework.pojoAndBean;

import java.io.Serializable;

class Pojo {
    private String text;
    private int number;

    @Override
    public String toString() {
        return "Pojo{" +
                "text='" + text + '\'' +
                ", number=" + number +
                '}';
    }
}

class JavaBean implements Serializable { // EJB
    private String text;
    private int number;

    // public no-arg constructor
    public JavaBean() { }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

public class SpringBeanVsJavaBean {

    public static void main(String[] args) {

    }

}
