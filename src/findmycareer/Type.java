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
public class Type {
    private int id;
    private String name;
    
    public Type(int id, String name){
        this.id = id;
        this.name = name;
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
    public String cbxString(){
        String msg = Integer.toString(getID()) + " " + getName();
        return msg;
    }
    @Override
    public String toString(){
        String message = "ID: " + getID() + " | NAME: " + getName();
        return message;
    }
}
