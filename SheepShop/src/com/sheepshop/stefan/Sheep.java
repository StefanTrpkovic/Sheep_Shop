package com.sheepshop.stefan;

public class Sheep 
{    
    private String name;
    private double age;
    private char sex;

    public Sheep(String name, double age, char sex) 
    {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setAge(double age) 
    {
        this.age = age;
    }
    
    public void setSex(char sex) 
    {
        this.sex = sex;
    }
    
    public String getName() 
    {
        return name;
    }

    public double getAge() 
    {
        return age;
    }

    public char getSex() 
    {
        return sex;
    }
}
