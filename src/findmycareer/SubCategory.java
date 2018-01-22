package findmycareer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author Robert Strachan
 * @StudentNO: 9105393616
 * @version 0.1
 * @Date 31/05/2017
 * @Bugs None
 */
public class SubCategory {
    private int id;
    private String name;
    private int industryID;
    
    
    public SubCategory(String name, int industryID){
        this.name = name;
        this.industryID = industryID;
    }
    
    public SubCategory(int id, String name, int industryID){
        this.id = id;
        this.name = name;
        this.industryID = industryID;
    }
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setIndustryID(int industryID){
        this.industryID = industryID;
    }
    public int getIndustryID(){
        return this.industryID;
    }
    public String cbxString(){
        String msg = Integer.toString(getID()) + " " + getName();
        return msg;
    }
    @Override
    public String toString(){
        String message = "ID: " + getID() + " | NAME: " + getName() + 
                " | INDUSTRY ID: " + getIndustryID();
        return message;
    }
}
