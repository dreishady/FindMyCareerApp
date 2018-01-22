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
public class Option {
    private int id;
    private int sub_cat_id;
    private int type_id;
    private String name;
    
    
    public Option(int sub_cat_id, int type_id, String name){
        this.sub_cat_id = sub_cat_id;
        this.type_id = type_id;
        this.name = name;
    }
    
    public Option(int id, int sub_cat_id, int type_id, String name){
        this.id = id;
        this.sub_cat_id = sub_cat_id;
        this.type_id = type_id;
        this.name = name;
    }
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return this.id;
    }    
    public void setSubCatID(int id){
        this.id = id;
    }
    public int getSubCatID(){
        return this.sub_cat_id;
    }    
    public void setTypeID(int id){
        this.id = id;
    }
    public int getTypeID(){
        return this.type_id;
    }    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public String cbxString(){
        String msg = Integer.toString(getID()) + " " + getName();
        return msg;
    }
    @Override
    public String toString(){
        String message = "ID: " + getID() + " | SUB-CATEGORY ID: " + getSubCatID() +
                " | TYPE ID: " + getTypeID() +  " | NAME: " + getName();
        return message;
    }
}
