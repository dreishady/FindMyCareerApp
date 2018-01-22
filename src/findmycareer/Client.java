/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findmycareer;

/**
 *
 * Student No : 6100758617
 * Student name: Waleed Jamaldini
 * bugs : none
 * Date 13/06/2017
 */
public class Client extends User {
      
    private String date_of_birth;
    private String phone_number;
    private String email;
    private String login_time;
    
    
    public Client(String first_name, String last_name, String date_of_birth, String phone_number, String email, String login_time, String username, String password){
        super(first_name, last_name,username, password);
        
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.email = email;
        this.login_time = login_time;

    }
    public Client(int id, String first_name, String last_name, String date_of_birth, String phone_number, String email, String login_time, String username, String password){
        super(id, first_name, last_name,username, password);
        
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.email = email;
        this.login_time = login_time;

    }

 
    
     public String getDate_of_birth() {
        return date_of_birth;
        
    }
      public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
      }
      
       public String getPhone_number() {
        return  phone_number;
    }
   
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    
    public String getEmail() {
        return  email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
      public String getLogin_time() {
        return  login_time;
    }
    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }
   
}
