/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findmycareer;

/**
 * @author Robert Strachan
 * @StudentNO: 9105393616
 * @version 0.1
 * @Date 15/06/2017
 * @Bugs None
 * 
 */
public class Administrator extends User{

    public Administrator(String first_name, String last_name, String username, String password){
        super(first_name, last_name, username, password);
    }
        
    public Administrator(int id, String first_name, String last_name, String username, String password){
        super(id, first_name, last_name, username, password);
    }
    
}
