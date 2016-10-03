package com.sheepshop.stefan;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Calculation 
{
    ArrayList<Sheep> sheepList = new ArrayList<>();
    int dayShaved = 0;
    double milk = 0.0;
    int woolSkins = 0;
    
    public void readXmlFile(String xmlFileName)
    {
        try
        {
            File fXmlFile = new File(xmlFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("mountainsheep");

            for(int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;

                    sheepList.add(new Sheep(eElement.getAttribute("name"), 
                        Double.parseDouble(eElement.getAttribute("age")), 
                        eElement.getAttribute("sex").charAt(0)));
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) 
        {
            e.getMessage();
        }
    }
    
    public void calculate(int T)
    {
        for(int i = 0; i < sheepList.size(); i++)
        {
            if(sheepList.get(i).getAge() > 1)
            {
                woolSkins+=1;
            }

            for(int j = 0; j < T; j++)
            {
                double age = sheepList.get(i).getAge();
                
                if(age + (j*0.01) > 10)
                {
                    break;
                }

                milk += 50 - (((age*100)+j)*0.03);

                if(((age + (j*0.01)) == 1.0) || 
                        (dayShaved + (8 + age + (j*0.01)) <= j && age + (j*0.01) > 1.0))
                {
                    dayShaved = j;
                    woolSkins+=1;
                }
            }
        }

        System.out.println("\nIn stock:");
        System.out.println("\t" + milk + " litres of milk");
        System.out.println("\t" + woolSkins + " skins of wool");
        System.out.println("Herd:");
        
        for(int i = 0; i < sheepList.size(); i++)
        {            
            double ageCalc = sheepList.get(i).getAge() + (T*0.01);

            if(ageCalc > 10)
            {
                System.out.println("\tUnfortunately "  + sheepList.get(i).getName() +  " has died at age 10");
            }
            else
            {
                System.out.println("\t" + sheepList.get(i).getName() + " " + ageCalc + " years old");
            }
        }
    }
}