package com.sheepshop.stefan;

import java.util.Scanner;

public class MainClass 
{
    public static void main(String[] args) 
    {
        Scanner reader = new Scanner(System.in);
        String xmlFileName = "Input herd.xml";
        
        System.out.println("Hello! Welcome to the Sheep Shop.");
        System.out.println("Please enter the elapsed time in days:");
        int T = reader.nextInt();
        
        Calculation calculation = new Calculation();
        calculation.readXmlFile(xmlFileName);
        calculation.calculate(T);
    }
}
