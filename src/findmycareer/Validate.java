package findmycareer;


import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robert Strachan
 * @StudentNO: 9105393616
 * @version 0.3
 * @Date 13/06/2017
 * @Bugs None
 * 
 */
public class Validate {
    private static final String PATTERN_NAME = "([a-zA-Z])+([_a-zA-Z])$";
    private static final String PATTERN_IP4 = "([0-9]{1,3})+(\\.[0-9]{1,3})+(\\.[0-9]{1,3})+(\\.[0-9]{1,3})$";
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PATTERN_PASS = "^\\S*$";
    private static final String PATTERN_NUMBER = "^[0-9]";
    //private static final Color MY_GREY = new Color(240,240,240);
    
    
    public static boolean isValidString(JTextField tf){
        boolean isValid = false;
        tf.setBackground(Color.white);
        String ts = tf.getText();
        if(ts != null){
            Pattern p = Pattern.compile(PATTERN_NAME, Pattern.CASE_INSENSITIVE);
            Matcher match = p.matcher(ts);
            if(match.find()){
                isValid = true;
            }else{
                tf.setBackground(Color.pink);
                Message.warnPopup("Invaild Characters (letters only).");
            }
        }
        return isValid;
    }
    
    public static boolean isValidUsername(JTextField tf){
        boolean isValid = false;
        tf.setBackground(Color.white);
        String ts = tf.getText();
        if(ts != null){
            Pattern p = Pattern.compile(PATTERN_PASS);
            Matcher match = p.matcher(ts);
            //System.out.println(ts);
            if(match.find() && ts.length() > 1){
                isValid = true;
            }else{
                tf.setBackground(Color.pink);
                Message.warnPopup("Invaild Username (No White space allowed).");
            }
        }
        return isValid;
    }
    public static boolean isValidServer(JTextField tf){
        boolean isValid = false;
        tf.setBackground(Color.white);
        String server = tf.getText();
        if(server != null){
            Pattern p = Pattern.compile(PATTERN_NAME);
            Pattern p1 = Pattern.compile(PATTERN_IP4);
            Matcher name = p.matcher(server);
            Matcher IP4 = p1.matcher(server);
            if(name.find() || IP4.find()){
                isValid = true;
            }else{
                tf.setBackground(Color.pink);
                Message.warnPopup("Invalid Server name");
            }
        }
        return isValid;
    }
    
    public static boolean isValidEmail(JTextField tf){
        boolean isValid = false;
        tf.setBackground(Color.white);
        String ts = tf.getText();
        if(ts != null){
            Pattern p = Pattern.compile(PATTERN_EMAIL);
            Matcher match = p.matcher(ts);
            if(match.find()){
                isValid = true;
            }else{
                tf.setBackground(Color.pink);
                Message.warnPopup("Invaild Email Address.");
            }
        }
        return isValid;
    }
    
    public static boolean isValidTextArea(JTextArea ta){
        boolean isValid = false;
        ta.setBackground(Color.white);
        String text = ta.getText();
        if(text != null && text.length() > 1){
                isValid = true;
            }else{
                ta.setBackground(Color.pink);
                Message.warnPopup("Description Missing!");
            }
        return isValid;
    }
    public static boolean isValidLength(String field, String table, int col){
        boolean isValid = false;
        if(field.length() < FMCdb.getColumnSize(table, col)){
            isValid = true;
        }
        return isValid;
    }
    public static boolean isCbxValid(JComboBox cbx, String check){
        boolean isValid = false;
        if(cbx.getSelectedItem() != null && !cbx.getSelectedItem().toString().contains(check)){
            isValid = true;
        }
        return isValid;
    }
    
    public static boolean isValidPassword(JPasswordField pf, JPasswordField verify){
        boolean isValid = false;
        pf.setBackground(Color.white);
        verify.setBackground(Color.white);
        String pass = pf.getText();
        String vPass = verify.getText();
        if(pass != null && vPass != null){
            if(vPass.contains(pass) && pass.length() > 1){
                Pattern p = Pattern.compile(PATTERN_PASS);
                Matcher match = p.matcher(pass);
                if(match.find()){
                    isValid = true;
                }else{
                    pf.setBackground(Color.pink);
                    Message.warnPopup("Password Invalid.");
                } 
            }else{
               verify.setBackground(Color.pink);
               Message.warnPopup("Password Invalid or does not match.");
            }
        }
        return isValid;
    }
    public static boolean isValidDbPassword(JPasswordField pf, JPasswordField verify){
        boolean isValid = false;
        pf.setBackground(Color.white);
        verify.setBackground(Color.white);
        String pass = pf.getText();
        String vPass = verify.getText();
        if(vPass.contains(pass)){
            isValid = true;
        }else{
            pf.setBackground(Color.pink);
            verify.setBackground(Color.pink);
            Message.warnPopup("Password Invalid or does not match.");
        }
        return isValid;
    }
    public static boolean isValidInteger(JTextField tf){
        boolean isValid = false;
        tf.setBackground(Color.white);
        String myInt = tf.getText();
        if(myInt != null){
            Pattern p = Pattern.compile(PATTERN_NUMBER);
            Matcher match = p.matcher(myInt);
            if(match.find()){
                isValid = true;
            }else{
                tf.setBackground(Color.pink);
                Message.warnPopup("Not a valid number.");
            }
        }
        
        return isValid;
    }
    public static boolean isSalaryValid(JTextField min, JTextField max){
        boolean isValid = false;
        max.setBackground(Color.white);
        if(isValidInteger(min) && isValidInteger(max)){
            if(Integer.parseInt(max.getText()) > Integer.parseInt(min.getText())){
                isValid = true;
            }else{
               max.setBackground(Color.pink);
               Message.warnPopup("Max Salay is lower than min Salary."); 
            }
        }
        return isValid;
    }
    public static boolean isDebugModeOn(){
        boolean isValid = false;
        
        if(Settings.getSetting("debug").equalsIgnoreCase("true")){
            isValid = true;
        }
        return isValid;
    }
    public static boolean isValidConnection(String user, String pass){
        return FMCdb.testConnection(user, pass);
    }
    /*
    **
    **  MESSAGE POPUPS
    **
    */

}
