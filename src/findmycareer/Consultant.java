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
public class Consultant extends User {
    private String email;
            
    public Consultant(String first_name, String last_name, String email, String username, String password){
        super(first_name, last_name, username, password);
        this.email = email;

    }
    public Consultant(int id, String first_name, String last_name, String email, String username, String password){
        super(id, first_name, last_name, username, password);
        this.email = email;
    }
    
    public String toString(){
        String message = "ID: " + getId() + " | FIRST NAME: " + getFirst_name() + " | LAST NAME: " + getLast_name();
        return message;
    } 

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
