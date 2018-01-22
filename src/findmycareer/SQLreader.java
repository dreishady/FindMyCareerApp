/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findmycareer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JProgressBar;

/**
/**
 * @author Robert Strachan
 * @StudentNO: 9105393616
 * @version 0.2
 * @Date 16/06/2017
 * @Bugs none
 */
public class SQLreader {
    private static final boolean DEBUG = Validate.isDebugModeOn();
    
    private static final String LINE = "\n--------------------------------------"
            + "---------------------------------------------------------------";
    
    private static final File DB_FILE = new File("./db/database.sql");
    
    public static void create(JProgressBar bar, String dBname, String user, String pass){
        int count = 0;
        InputStream db = null;
        String oldName = Settings.getSetting("db");
        Scanner sc = null;
        try{
            if(DEBUG) System.out.println("\n|OPENING SQL FILE|");
            db = new FileInputStream(DB_FILE);
            //BufferedReader db = new BufferedReader(new FileReader(DB_FILE));
            String line = "";
            if(!dBname.equals(oldName)){
                line = "DROP SCHEMA IF EXISTS `" + oldName + "` ;";
                if(DEBUG) System.out.println("Droping old table...");
            }
            sc = new Scanner(db);
            if(DEBUG) System.out.println("Reading lines...");
            while(sc.hasNextLine()){
                if(line.contains(";")){
                    count++;
                    bar.setValue(count);
                    line = line.replace(";", " ");
                    line = line.replace("fmc", dBname);
                    //System.out.println(line);
                    FMCdb.createDatabase(line, user, pass);
                    line = "";
                    Thread.sleep(50);
                }else{
                    line += sc.nextLine();
                }
                if(DEBUG) System.out.println("Finished reading.");
            }  
            //System.out.println("LINES: " + count);
        }catch(IOException ie){
            if(DEBUG) ie.printStackTrace();
        }catch(Exception e){
            if(DEBUG) e.printStackTrace();
        }finally{
            if(DEBUG) System.out.println("\n|CLOSING SQL FILE|");
            try{
                if(DEBUG && db != null) System.out.println("Closing database file...");
                if(db != null) db.close();
            }catch(IOException ie2){
                if(DEBUG) ie2.printStackTrace();
            }
            if(DEBUG) System.out.println(LINE);
        }
    }
    
    public static int countCodeLines(){
        int lines = 0;
        InputStream db = null;
        Scanner sc = null;
        try{
           if(DEBUG) System.out.println("\n|OPENING SQL FILE|");
           db = new FileInputStream(DB_FILE);
           sc = new Scanner(db);
           while(sc.hasNextLine()){
               if(sc.nextLine().contains(";")){
                   lines++;
               }
           }
           if(DEBUG) System.out.println("SQL lines counted| " + lines);
        }catch(Exception e){
            if(DEBUG) e.printStackTrace();
        }finally{
            if(DEBUG) System.out.println("\n|CLOSING SQL FILE|");
            try{
                if(DEBUG && db != null) System.out.println("Closing database file...");
                if(db != null) db.close();
            }catch(IOException ie2){
                if(DEBUG) ie2.printStackTrace();
            }
            if(DEBUG) System.out.println(LINE);
        }
        return lines;
    }
    
}
