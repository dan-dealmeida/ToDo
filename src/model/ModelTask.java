/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zelen
 */
public class ModelTask {
    
    String name;
    Date date = new Date();
    String description;
    
    
    
    
    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setDate(String date){
      
      SimpleDateFormat ft = new SimpleDateFormat ("MM/DD/YY"); 
      String input = date; 

      Date t;
      try {
         t = ft.parse(input); 
         this.date = t; 
      } catch (ParseException e) { 
        // System.out.println("Unparseable using " + ft); 
      }
    }
    public void setDescription(String description){
        this.description = description;
    }
    
    
    
    
    
    //Getters
    public String getName(){
        return name;
    }
    public Date getDate(){
        return date;
    }
    public String getDescription(){
        return description;
    }
    
    

}
    
    
