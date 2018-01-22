package findmycareer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author Robert Strachan
 * @StudentNO: 9105393616
 * @version 0.3
 * @Date 17/06/2017
 * @Bugs None
 */

/*
server=localhost
port=3306
db=fmc
username=admin
password=password
*/
public class Settings {
    
    
//    private static final String SETTINGS_FILE = 
//           "settings.properties";
    
    private static final File SETTINGS_FILE = new File("settings.properties");
    
    private static final String LINE = "\n--------------------------------------"
            + "---------------------------------------------------------------";
    
    
    
    static Properties prop = new Properties();
  
    
    public static String getSetting(String key){
        //InputStream readSettings = null;
        InputStream readSettings = null;
        String value = "";
         try{
            if(DEBUG) System.out.println("\n|OPENING PROPERTIES FILE|");
            //output = new FileOutputStream("config.properties");
            if(DEBUG) System.out.println("Getting setting at |" + key + "|");
            //readSettings = new FileInputStream(SETTINGS_FILE);
            if(!SETTINGS_FILE.exists()){
                readSettings = Settings.class.getResourceAsStream("settings.properties");
                if(DEBUG) System.out.println("External file not found reading from internal file...");
            }else{
                readSettings = new FileInputStream(SETTINGS_FILE);
                if(DEBUG) System.out.println("External file found...");
            }
            
            prop.load(readSettings);
            value = prop.getProperty(key);
            if(DEBUG) System.out.println("Setting at |" + key + "| is |" + value + "|");
        }catch(IOException xe){
            //xe.printStackTrace();
             if(DEBUG) xe.printStackTrace();
             Message.error("<html><center>Could not read settings file.</html>");
        }finally{
            if(DEBUG) System.out.println("\n|CLOSING PROPERTIES FILE|");
            try{
                if(DEBUG && readSettings != null) System.out.println("Closing readSettings Input Stream...");
                if(readSettings != null) readSettings.close();
            }catch(IOException ie2){
                if(DEBUG) ie2.printStackTrace();
            }
            if(DEBUG) System.out.println(LINE);
        }
         return value;
    }
    
    public static void setSetting(String key, String value){
        OutputStream writeSettings = null;
        InputStream readSettings = null;
        
         try{
            if(DEBUG) System.out.println("\n|OPENING PROPERTIES FILE|");
            //readSettings = new FileInputStream(SETTINGS_FILE);
            if(!SETTINGS_FILE.exists()){
                readSettings = Settings.class.getResourceAsStream("settings.properties");
                if(DEBUG) System.out.println("External file not found reading from internal file...");
            }else{
                readSettings = new FileInputStream(SETTINGS_FILE);
                if(DEBUG) System.out.println("External file found...");
            }
            prop.load(readSettings);
            writeSettings = new FileOutputStream(SETTINGS_FILE);
            if(DEBUG) System.out.println("Wrtting to external file...");
            prop.setProperty(key, value);
            if(DEBUG) System.out.println("Writting setting at |" + key + "| to |" + value + "|");
            prop.store(writeSettings, null);
        }catch(IOException xe){
            //xe.printStackTrace();
            if(DEBUG) xe.printStackTrace();
            Message.error("<html><center>Could not write to settings file.</html>");
        }finally{
            if(DEBUG) System.out.println("\n|CLOSING PROPERTIES FILE|");
            try{
                if(DEBUG && readSettings != null) System.out.println("Closing readSettings Input Stream...");
                if(readSettings != null) readSettings.close();
                if(DEBUG && writeSettings != null) System.out.println("Closing writeSettings Output Stream...");
                if(writeSettings != null) writeSettings.close();
            }catch(IOException ie2){
                if(DEBUG) ie2.printStackTrace();
            }
            if(DEBUG) System.out.println(LINE);
        }
    }
    
    private static final boolean DEBUG = Validate.isDebugModeOn();
}
