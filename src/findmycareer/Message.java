package findmycareer;


import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Robert Strachan
 * @StudentNO: 9105393616
 * @version 0.3
 * @Date 18/06/2017
 * @Bugs None
 */
public class Message {
    public static void warnPopup(String msg){
        msg = "<html><b>" + msg + "</b><br><br>Please try again.";
        JOptionPane.showMessageDialog(null, msg, "WARNING", JOptionPane.WARNING_MESSAGE);
    }
    public static void okPopup(String msg){      
        JOptionPane.showMessageDialog(null, msg, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void dbErr(){      
        JOptionPane.showMessageDialog(null, "Database Query error.", "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);
    }
    public static void dbCommErr(){      
        JOptionPane.showMessageDialog(null, "<html><b>Database Communiction Error."
                + "</b><br><br>Unable to connect with Database.<br><br>"
                + "Check Connection and try again.</html>", "CONNECTION ERROR", JOptionPane.ERROR_MESSAGE);
    }
    public static void dbNotFoundErr(){      
        JOptionPane.showMessageDialog(null, "<html><b>Database not found."
                + "</b><br><br>Unable locate Database on SQL server.<br><br>"
                + "Check Database name and try again.</html>", "UNKOWN DATABASE", JOptionPane.ERROR_MESSAGE);
    }
    public static void dbDeniedErr(){      
        JOptionPane.showMessageDialog(null, "<html><b>Access denied to Database."
                + "</b><br><br>"
                + "Check Database username and password and try again.</html>", "ACCESS DENIED", JOptionPane.ERROR_MESSAGE);
    }
    public static void driverErr(){      
        JOptionPane.showMessageDialog(null, "Missing database driver.", "DRIVER ERROR", JOptionPane.ERROR_MESSAGE);
    }
    public static void error(String msg){      
        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void dbErrorCheck(String ex){
        if(ex.contains("Communications link failure")){
            dbCommErr();
        }else if(ex.contains("Unknown database")){
            dbNotFoundErr();
        }else if(ex.contains("Access denied")){
            dbDeniedErr();
        }else{
            dbErr();
        }
        
        
//        if(exception.contains("Communications link failure")){
//            dbCommErr();
//        }
    }
}
