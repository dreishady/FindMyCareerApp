package findmycareer;




import java.sql.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Robert Strachan
 * @StudentNO: 9105393616
 * @version 0.5
 * @Date 16/06/2017
 * @Bugs None
 */
public class FMCdb {
    
    private static final boolean DEBUG = Validate.isDebugModeOn();
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    
//    private static String server = settings.getSetting("server");
//    private static String port = settings.getSetting("port");
//    private static String db_name = settings.getSetting("db");
//    private static String user = settings.getSetting("username");
//    private static String password = settings.getSetting("password");
    
    private static String adminUser = Settings.getSetting("adminUsername");
    private static String adminPass = Settings.getSetting("adminPassword");
    private static final String LINE = "\n--------------------------------------"
            + "---------------------------------------------------------------";
    
//    private static String url = "jdbc:mysql://" + server + ":" + port + "/" + db_name;
//    private static String urlNoDb = "jdbc:mysql://" + server + ":" + port;

    
    private static Connection conn;
    private static PreparedStatement pStatement;
    private static Statement statement;
    private static ResultSet rs;
    
    //NON-Prepared Statment
    private static ResultSet databaseQuery(String query)throws SQLException{
        conn = null;
        statement = null;
        rs = null;
//        String url = "jdbc:mysql://" + Settings.getSetting("server") + ":" +
//                Settings.getSetting("port") + "/" + Settings.getSetting("db");
        String user = Settings.getSetting("username");
        String password = Settings.getSetting("password");
        String url = Settings.getSetting("url_db");
        
        try{
            Class.forName(JDBC_DRIVER);
            if(DEBUG) System.out.println("\n|DATABASE CONNECTING|");
            conn = DriverManager.getConnection(url, user, password);
            if(DEBUG) System.out.println("Creating Statment...");
            if(DEBUG) System.out.println(query);
            statement = conn.createStatement();
            if(query.contains("SELECT")){
                rs = statement.executeQuery(query);
                if(DEBUG) System.out.println("\nExecuting SELECT statement...");
            }else{
                statement.executeUpdate(query);
                if(DEBUG) System.out.println("\nExecuting OTHER statement...");
            }
            
            
        }catch(SQLException se){
            
            if(DEBUG) se.printStackTrace();
            Message.dbErrorCheck(se.toString());
        }catch(ClassNotFoundException e){
            if(DEBUG) e.printStackTrace();
            Message.driverErr();
        }
       
        return rs;
    }
    private static ResultSetMetaData metaQuery(String query)throws SQLException{
        conn = null;
        statement = null;
        rs = null;
//        String url = "jdbc:mysql://" + Settings.getSetting("server") + ":" +
//                Settings.getSetting("port") + "/" + Settings.getSetting("db");
        
        String url = Settings.getSetting("url_db");
        String user = Settings.getSetting("username");
        String password = Settings.getSetting("password");
        
        ResultSetMetaData meta = null;
        if(DEBUG) System.out.println(query);
        try{
            Class.forName(JDBC_DRIVER);
            if(DEBUG) System.out.println("\n|DATABASE CONNECTING|");
            conn = DriverManager.getConnection(url, user, password);
            if(DEBUG) System.out.println("Creating Statment...");
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            meta = rs.getMetaData();
            
        }catch(SQLException se){
            if(DEBUG) se.printStackTrace();
            if(se.toString().contains("Communications link failure")){
                Message.dbCommErr();
            }else{
                Message.dbErr();
            }
        }catch(ClassNotFoundException e){
            if(DEBUG) e.printStackTrace();
            Message.driverErr();
        }
       
        return meta;
    }
    //Prepared Statment with bindings
    private static ResultSet databaseQuery(String query, Object[][] binds)throws SQLException{
        conn = null;
        pStatement = null;
        rs = null;
//        String url = "jdbc:mysql://" + Settings.getSetting("server") + ":" +
//                Settings.getSetting("port") + "/" + Settings.getSetting("db");
        
        String url = Settings.getSetting("url_db");
        String user = Settings.getSetting("username");
        String password = Settings.getSetting("password");
        
        int typeIndex = 0; //Binding type
        int argIndex = 1; //Offset for argument
        
        try{
            if(DEBUG) System.out.println("\n|DATABASE CONNECTING|");
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(url, user, password);
            if(DEBUG) System.out.println("Creating Prepared Statment...");
            if(DEBUG) System.out.println(query);
            pStatement = conn.prepareStatement(query);
            
            if(DEBUG) System.out.println("\nBinding " + binds.length + " positions...");
            
            //Bind ? to argument list based on type. Will expanad as needed
            for(int i = 0; i < binds.length; i++){
                switch(binds[i][typeIndex].toString().toLowerCase()){
                    case "int" :
                        if(DEBUG) System.out.println("INT binding found at BINDS: " + i);
                        pStatement.setInt(i + argIndex, Integer.parseInt((String) binds[i][argIndex]));
                        break;
                    case "string" :
                        if(DEBUG) System.out.println("STRING binding found at BINDS: " + i);
                        pStatement.setString(i + argIndex, binds[i][argIndex].toString());
                        break;
                    case "date" :
                        if(DEBUG) System.out.println("STRING binding found at BINDS: " + i);
                        pStatement.setDate(i + argIndex, (Date) binds[i][argIndex]);
                        break;
                    case "time_stamp" :
                        if(DEBUG) System.out.println("STRING binding found at BINDS: " + i);
                        pStatement.setTimestamp(i + argIndex, (Timestamp)binds[i][argIndex]);
                        break;
                }
                if(DEBUG) System.out.println("Assigned: |" + binds[i][argIndex].toString() + "| To (?)POSITION: " + (i + argIndex));
            }
            
            if(DEBUG) System.out.println("All Bindings assigned.");
            
            if(query.contains("SELECT")){
                if(DEBUG) System.out.println("\nExecuting SELECT prepared statement...");
                rs = pStatement.executeQuery();
            }else{
                if(DEBUG) System.out.println("\nExecuting prepared statement..."); 
                pStatement.executeUpdate();
            }
            
             
        }catch(SQLException se){
            if(DEBUG) se.printStackTrace();
            Message.dbErrorCheck(se.toString());
        }catch(ClassNotFoundException e){
            if(DEBUG) e.printStackTrace();
            Message.driverErr();
        }
       
        return rs;
    }
        //Prepared Statment with bindings
    private static ResultSet databaseQuery(String query, Object[][] binds, String user, String pass) throws SQLException{
        conn = null;
        pStatement = null;
        rs = null;
//        String urlNoDb = "jdbc:mysql://" + Settings.getSetting("server") + ":" +
//                Settings.getSetting("port");
        String urlNoDb = Settings.getSetting("url");
        
        
        int typeIndex = 0; //Binding type
        int argIndex = 1; //Offset for argument
        
        try{
            if(DEBUG) System.out.println("\n|DATABASE CONNECTING|");
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(urlNoDb, user, pass);
            if(DEBUG) System.out.println("Creating Prepared Statment...");
            if(DEBUG) System.out.println(query);
            pStatement = conn.prepareStatement(query);
            
            if(DEBUG) System.out.println("\nBinding " + binds.length + " positions...");
            
            //Bind ? to argument list based on type. Will expanad as needed
            for(int i = 0; i < binds.length; i++){
                switch(binds[i][typeIndex].toString().toLowerCase()){
                    case "int" :
                        if(DEBUG) System.out.println("INT binding found at BINDS: " + i);
                        pStatement.setInt(i + argIndex, Integer.parseInt((String) binds[i][argIndex]));
                        break;
                    case "string" :
                        if(DEBUG) System.out.println("STRING binding found at BINDS: " + i);
                        pStatement.setString(i + argIndex, binds[i][argIndex].toString());
                        break;
                    case "date" :
                        if(DEBUG) System.out.println("STRING binding found at BINDS: " + i);
                        pStatement.setDate(i + argIndex, (Date) binds[i][argIndex]);
                        break;
                    case "time_stamp" :
                        if(DEBUG) System.out.println("STRING binding found at BINDS: " + i);
                        pStatement.setTimestamp(i + argIndex, (Timestamp)binds[i][argIndex]);
                        break;
                }
                if(DEBUG) System.out.println("Assigned: |" + binds[i][argIndex].toString() +
                        "| To (?)POSITION: " + (i + argIndex));
            }
            
            if(DEBUG) System.out.println("All Bindings assigned.");
            
            if(query.startsWith("SELECT")){
                if(DEBUG) System.out.println("\nExecuting SELECT prepared statement...");
                rs = pStatement.executeQuery();
            }else{
                if(DEBUG) System.out.println("\nExecuting prepared statement..."); 
                pStatement.executeUpdate();
            }
            
             
        }catch(SQLException se){
            if(DEBUG) se.printStackTrace();
            if(se.toString().contains("Communications link failure")){
                Message.dbCommErr();
            }else{
                Message.dbErr();
            }
        }catch(ClassNotFoundException e){
            if(DEBUG) e.printStackTrace();
            Message.driverErr();
        }
       
        return rs;
    }
    
    
    private static void cleanUp(){
        // Close all connections once done
        if(DEBUG) System.out.println("\n|CLOSING CONNECTIONS|");
        try{           
            if(DEBUG && rs != null) System.out.println("Closing ResultSet...");
            if(rs != null) rs.close();
            rs = null;
        }catch(SQLException e_rs){
            if(DEBUG) System.out.println("ERROR! could not close ResultSet!");
        }try{
            if(DEBUG && statement != null) System.out.println("Closing Statement...");
            if(statement != null) statement.close();
            statement = null;
        }catch(SQLException e_s){
            if(DEBUG) System.out.println("ERROR! could not close Statement!");
        }
        try{
            if(DEBUG && pStatement != null) System.out.println("Closing Prepared Statment...");
            if(pStatement != null) pStatement.close(); 
            pStatement = null;
        }catch(SQLException e_pS){
             if(DEBUG) System.out.println("ERROR! could not close Prepared Statement!");
        }
        try{
            if(DEBUG && conn != null) System.out.println("Closing Database connection...");
            if(conn != null) conn.close(); 
            conn = null;
        }catch(SQLException e_conn){
            if(DEBUG) System.out.println("ERROR! could not close connection!");
        }
        if(DEBUG) System.out.println(LINE);
    }
    
    public static int getColumnSize(String table, int column){
        ResultSetMetaData meta = null;
        String query = "SELECT * FROM " + table;
        int size = 0;
        try{
            size = metaQuery(query).getColumnDisplaySize(column);
        }catch(SQLException se){
            if(DEBUG) se.printStackTrace();
            Message.dbErr();
        }
        return size;
    }
    
    public static void createDatabase(String code, String user, String pass){
        conn = null;
        statement = null;
        rs = null;
//        String urlNoDb = "jdbc:mysql://" + Settings.getSetting("server") + ":" +
//                Settings.getSetting("port");
        String urlNoDb = Settings.getSetting("url");
        
        try{
            Class.forName(JDBC_DRIVER);
            if(DEBUG) System.out.println("\n|DATABASE CONNECTING|");
            conn = DriverManager.getConnection(urlNoDb, user, pass);
            if(DEBUG) System.out.println("Creating Statment...");
            if(DEBUG) System.out.println(code);
            statement = conn.createStatement();
            if(code.contains("SELECT")){
                rs = statement.executeQuery(code);
                if(DEBUG) System.out.println("\n" + code);
            }else{
                statement.executeUpdate(code);
                if(DEBUG) System.out.println("\n" + code);
            }
            
            
        }catch(SQLException se){
            if(DEBUG) se.printStackTrace();
            //Message.dbErr();
        }catch(ClassNotFoundException e){
            if(DEBUG) e.printStackTrace();
            Message.driverErr();
        }finally{
            cleanUp();
        }
    }
    /*
    **
    **      TYPE TABLE COMMANDS
    **
    */
    public static ArrayList getTypes(){
       rs = null;
       ArrayList<Type> typeList = new ArrayList<>();
       String query = "SELECT * FROM type";
       try{
           rs = databaseQuery(query); //Run query without bindings
           while(rs.next()){
               Type type = new Type(rs.getInt("type_id"), rs.getString("name"));
               typeList.add(type); 
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve types!");
       }finally{
           cleanUp(); // Close all connectections
       }
       return typeList;
    }
    
    public static Type getTypeById(int id){
       rs = null;
       Type type = null;
       String query = "SELECT * FROM type WHERE type_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds); //Run query with bindings
           while(rs.next()){
               type = new Type(rs.getInt("type_id"), rs.getString("name"));
           }
       }catch(Exception e){
           if(DEBUG) e.printStackTrace();
           if(DEBUG) System.out.println("ERROR! could not retrieve type!");
       }finally{
           cleanUp();
       }
       return type;
    }
    
    public static void addType(String name){
        String query = "INSERT INTO type VALUES(NULL, ?)";
        String[][] binds = {{"string", name}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not add type!");
        }finally{
            cleanUp();
        }
    }
    
    public static void deleteTypeById(int id){
        String query = "DELETE FROM type WHERE type_id = ?";
        String[][] binds = {{"int", Integer.toString(id)}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not delete type!");
        }finally{
            cleanUp();
        }
    }
    
    public static void updateTypeById(int id, String name){
        String query = "UPDATE type SET name = ? WHERE type_id = ?";
        String[][] binds = {{"string", name},{"int", Integer.toString(id)}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not update type!");
        }finally{
            cleanUp();
        }
    }
    /*
    **
    **      INDUSTRY TABLE COMMANDS
    **
    */
    public static ArrayList getIndustries(){
       rs = null;
       ArrayList<Industry> industyList = new ArrayList<>();
       String query = "SELECT * FROM industry";
       try{
           rs = databaseQuery(query);
           while(rs.next()){
               Industry industry = new Industry(rs.getInt("industry_id"), rs.getString("name"));
               industyList.add(industry);
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve industries!");
       }finally{
           cleanUp();
       }
       return industyList;
    }
    
    public static Industry getIndustryById(int id){
       rs = null;
       Industry industry = null;
       String query = "SELECT * FROM industry WHERE industry_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               industry = new Industry(rs.getInt("industry_id"), rs.getString("name"));
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve industries!");
       }finally{
           cleanUp();
       }
       return industry;
    }
    
    public static void addIndustry(String name){
        String query = "INSERT INTO Industry VALUES(NULL, ?)";
        String[][] binds = {{"string", name}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not add Industry!");
        }finally{
            cleanUp();
        }
    }
    
    public static void deleteIndustryById(int id){
        String query = "DELETE FROM industry WHERE industry_id = ?";
        String[][] binds = {{"int", Integer.toString(id)}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not delete industry!");
        }finally{
            cleanUp();
        }
    }
    
    public static void updateIndustryById(Industry industry){
        String query = "UPDATE industry SET name = ? WHERE industry_id = ?";
        String[][] binds = {{"string", industry.getName()},{"int", Integer.toString(industry.getID())}};
        try{
            databaseQuery(query);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not update industry!");
        }finally{
            cleanUp();
        }
    }
    /*
    **
    **      SUB-CATEGORY TABLE COMMANDS
    **
    */
    public static ArrayList getSubCategories(){
       rs = null;
       ArrayList<SubCategory> subCatList = new ArrayList<>();
       String query = "SELECT * FROM sub_category";
       try{
           rs = databaseQuery(query);
           while(rs.next()){
               SubCategory subCategory = new SubCategory(rs.getInt("sub_cat_id"),
                       rs.getString("name"), rs.getInt("industry_id"));
               subCatList.add(subCategory);
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Sub-Categories!");
       }finally{
           cleanUp();
       }
       return subCatList;
    }
    
    public static SubCategory getSubCategoryById(int id){
       rs = null;
       SubCategory subCategory = null;
       String query = "SELECT * FROM sub_category WHERE sub_cat_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               subCategory = new SubCategory(rs.getInt("sub_cat_id"),
                       rs.getString("name"), rs.getInt("industry_id"));
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Sub-Categories!");
       }finally{
           cleanUp();
       }
       return subCategory;
    }
    public static ArrayList getSubCategoryByIndustryId(int id){
       rs = null;
       SubCategory subCategory = null;
       ArrayList<SubCategory> subCatList = new ArrayList<>();
       String query = "SELECT * FROM sub_category WHERE industry_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               subCategory = new SubCategory(rs.getInt("sub_cat_id"),
                       rs.getString("name"), rs.getInt("industry_id"));
               subCatList.add(subCategory);
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Sub-Categories!");
       }finally{
           cleanUp();
       }
       return subCatList;
    }
    
    public static void addSubCategory(SubCategory subCat){
        String query = "INSERT INTO sub_category VALUES(NULL, ?, ?)";
        String[][] binds = {{"string", subCat.getName()},{"int", Integer.toString(subCat.getIndustryID())}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not add Sub-Category!");
        }finally{
            cleanUp();
        }
    }
    
    public static void deleteSubCategoryById(int id){
        String query = "DELETE FROM sub_category WHERE sub_cat_id = ?";
        String[][] binds = {{"int", Integer.toString(id)}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not delete Sub-Category!");
        }finally{
            cleanUp();
        }
    }
    
    public static void updateSubCatById(SubCategory subcat){
        String query = "UPDATE sub_category SET name = ?, industry_id = ? WHERE industry_id = ?";
        String[][] binds = {{"string", subcat.getName()},{"int", Integer.toString(subcat.getIndustryID())},
            {"int", Integer.toString(subcat.getID())}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not update Sub-Category!");
        }finally{
            cleanUp();
        }
    }
    /*
    **
    **      OPTION TABLE COMMANDS
    **
    */
    public static ArrayList getOptions(){
       rs = null;
       ArrayList<Option> optionList = new ArrayList<>();
       String query = "SELECT * FROM options";
       try{
           rs = databaseQuery(query);
           while(rs.next()){
               Option option = new Option(rs.getInt("options_id"), rs.getInt("sub_cat_id"),
                       rs.getInt("type_id"), rs.getString("name"));
               optionList.add(option);
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve options!");
       }finally{
           cleanUp();
       }
       return optionList;
    }
    
    public static Option getOptionById(int id){
       rs = null;
       Option option = null;
       String query = "SELECT * FROM options WHERE options_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               option = new Option(rs.getInt("options_id"), rs.getInt("sub_cat_id"),
                       rs.getInt("type_id"), rs.getString("name"));
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve options!");
       }finally{
           cleanUp();
       }
       return option;
    }
    public static ArrayList getOptionBySubCatId(int id){
       rs = null;
       Option option = null;
       ArrayList<Option> optionList = new ArrayList<>();
       String query = "SELECT * FROM options WHERE sub_cat_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               option = new Option(rs.getInt("options_id"), rs.getInt("sub_cat_id"),
                       rs.getInt("type_id"), rs.getString("name"));
               optionList.add(option);
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve options!");
       }finally{
           cleanUp();
       }
       return optionList;
    }
    
    public static void addOption(Option option){
        String query = "INSERT INTO options VALUES(NULL, ?, ?, ?)";
        String[][] binds = {{"int", Integer.toString(option.getSubCatID())},
            {"int", Integer.toString(option.getTypeID())},{"string", option.getName()}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not add Option!");
        }finally{
            cleanUp();
        }
    }
    
    public static void deleteOptionById(int id){
        String query = "DELETE FROM options WHERE option_id = ?";
        String[][] binds = {{"int", Integer.toString(id)}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not delete Option!");
        }finally{
            cleanUp();
        }
    }
    public static void updateOptionById(Option option){
        String query = "UPDATE options SET sub_cat_id = ?, type_id = ?, name = ? WHERE options_id = ?";
        String[][] binds = {{"int", Integer.toString(option.getSubCatID())},
            {"int", Integer.toString(option.getTypeID())},{"string", option.getName()}, {"int", Integer.toString(option.getID())}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not update Option!");
        }finally{
            cleanUp();
        }
    }
        /*
    **
    **      CONSULTANT TABLE COMMANDS
    **
    */
    
    public static ArrayList getConsultants(){
       rs = null;
       ArrayList<Consultant> consultantList = new ArrayList<>();
       String query = "SELECT * FROM consultant";
       try{
           rs = databaseQuery(query);
           while(rs.next()){
               Consultant con = new Consultant(rs.getInt("consultant_id"), rs.getString("first_name"),
                       rs.getString("last_name"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
               consultantList.add(con);
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Consultant!");
       }finally{
           cleanUp();
       }
       return consultantList;
    }
    
    public static Consultant getConsultantById(int id){
       rs = null;
       Consultant con = null;
       String query = "SELECT * FROM consultant WHERE consultant_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               con = new Consultant(rs.getInt("consultant_id"), rs.getString("first_name"),
                       rs.getString("last_name"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Consultant!");
       }finally{
           cleanUp();
       }
       return con;
    }
    
    public static void addConsultant(Consultant con){

       String query = "INSERT INTO consultant VALUES(NULL, ?, ?, ?, ?, ?)";
       String[][] binds = {{"string", con.getFirst_name()},{"string", con.getLast_name()},
           {"string", con.getEmail()}, {"string", con.getUsername()},{"string", con.getPassword()}};
       try{
           rs = databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Consultant!");
       }finally{
           cleanUp();
       }
    }
    public static void deleteConsultantById(int id){

       String query = "DELETE FROM consultant WHERE consultant_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not delete Consultant!");
       }finally{
           cleanUp();
       }
    }
    public static void updateConsultantById(Consultant con){

       String query = "UPDATE consultant SET first_name = ?, last_name = ?, email = ?,"
               + " username = ?, password = ? WHERE consultant_id = ?";
       String[][] binds = {{"string", con.getFirst_name()},{"string", con.getLast_name()}, {"string", con.getEmail()},{"string", con.getUsername()},
           {"string", con.getPassword()}, {"int", Integer.toString(con.getId())}};
       try{
           rs = databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not update Consultant!");
       }finally{
           cleanUp();
       }
    }
    /*
    **
    **      CAREER TABLE COMMANDS
    **
    */
    public static ArrayList getCareers(){
       rs = null;
       ArrayList<Career> careerList = new ArrayList<>();
       String query = "SELECT * FROM career";
       try{
           rs = databaseQuery(query);
           while(rs.next()){
               Career career = new Career(rs.getInt("career_id"), rs.getInt("options_id"),
                       rs.getInt("consultant_id"), rs.getString("name"),
                       rs.getString("description"), rs.getString("demand"),
                       rs.getInt("min_salary"), rs.getInt("max_salary"));
               careerList.add(career);
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Careers!");
       }finally{
           cleanUp();
       }
       return careerList;
    }
    
    public static ArrayList getCareersByConsultantId(int id){
       rs = null;
       ArrayList<Career> careerList = new ArrayList<>();
       String query = "SELECT * FROM career WHERE consultant_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               Career career = new Career(rs.getInt("career_id"), rs.getInt("options_id"),
                       rs.getInt("consultant_id"), rs.getString("name"),
                       rs.getString("description"), rs.getString("demand"),
                       rs.getInt("min_salary"), rs.getInt("max_salary"));
               careerList.add(career);
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Careers!");
       }finally{
           cleanUp();
       }
       return careerList;
    }
    
    public static Career getCareerById(int id){
       rs = null;
       Career career = null;
       
       String query = "SELECT * FROM career WHERE career_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               career = new Career(rs.getInt("career_id"), rs.getInt("options_id"),
                       rs.getInt("consultant_id"), rs.getString("name"),
                       rs.getString("description"), rs.getString("demand"),
                       rs.getInt("min_salary"), rs.getInt("max_salary"));
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Career!");
       }finally{
           cleanUp();
       }
       return career;
    }
    
    public static ArrayList getCareerByOptionId(int id){
       rs = null;
       Career career = null;
       ArrayList<Career> careerList = new ArrayList<>();
       String query = "SELECT * FROM career WHERE options_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               career = new Career(rs.getInt("career_id"), rs.getInt("options_id"),
                       rs.getInt("consultant_id"), rs.getString("name"),
                       rs.getString("description"), rs.getString("demand"),
                       rs.getInt("min_salary"), rs.getInt("max_salary"));
               careerList.add(career);
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Careers!");
       }finally{
           cleanUp();
       }
       return careerList;
    }
    
    public static void addCareer(Career career){
        rs = null;
       String query = "INSERT INTO career VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
       String[][] binds = {{"int", Integer.toString(career.getOptions_id())}, {"int", Integer.toString(career.getConsultant_id())},
           {"string", career.getName()}, {"string", career.getDesc()}, {"string", career.getDemand()}, {"int", Integer.toString(career.getMin_salary())},
           {"int", Integer.toString(career.getMax_salary())}};
       try{
           rs = databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not add Career!");
       }finally{
           cleanUp();
       }    
    }
    
    
    public static void deleteCareerById(int id){
       rs = null;
       String query = "DELETE FROM career WHERE career_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not delete Career!");
       }finally{
           cleanUp();
       }
    }
    
    public static void updateCareerById(Career career){
       rs = null;
       String query = "UPDATE career SET options_id = ?, consultant_id = ?, name = ?,"
               + " description = ?, demand = ?, min_salary = ?, max_salary = ? WHERE career_id = ?";
       String[][] binds = {{"int", Integer.toString(career.getOptions_id())}, {"int", Integer.toString(career.getConsultant_id())},
           {"string", career.getName()}, {"string", career.getDesc()}, {"string", career.getDemand()}, {"int", Integer.toString(career.getMin_salary())},
           {"int", Integer.toString(career.getMax_salary())}, {"int", Integer.toString(career.getId())}};
       try{
           databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not update Career!");
       }finally{
           cleanUp();
       }
    }
    /*
    **
    **      APPOINTMENT TABLE COMMANDS
    **
    */
    public static ArrayList getAppointments(){
       rs = null;
       ArrayList<Appointment> appList = new ArrayList<>();
       String query = "SELECT * FROM appointment";
       try{
           rs = databaseQuery(query);
           while(rs.next()){
               Appointment app = new Appointment(rs.getInt("appoint_id"), rs.getInt("client_id"),
                       rs.getInt("consultant_id"), rs.getDate("date"),
                       rs.getString("industry_name"), rs.getString("category_name"),
                       rs.getString("options_name"), rs.getString("career_name"),
                       rs.getString("notes"));
               appList.add(app);
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Appointments!");
       }finally{
           cleanUp();
       }
       return appList;
    }
    
    public static ArrayList getAppointmentsByConsultantId(int id){
       rs = null;
       ArrayList<Appointment> appList = new ArrayList<>();
       String query = "SELECT * FROM appointment WHERE consultant_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               Appointment app = new Appointment(rs.getInt("appoint_id"), rs.getInt("client_id"),
                       rs.getInt("consultant_id"), rs.getDate("date"),
                       rs.getString("industry_name"), rs.getString("category_name"),
                       rs.getString("options_name"), rs.getString("career_name"),
                       rs.getString("notes"));
               appList.add(app);
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Appointments!");
       }finally{
           cleanUp();
       }
       return appList;
    }
    
    public static Appointment getAppointmentById(int id){
       rs = null;
       Appointment app = null;
       String query = "SELECT * FROM appointment WHERE appoint_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               app = new Appointment(rs.getInt("appoint_id"), rs.getInt("client_id"),
                       rs.getInt("consultant_id"), rs.getDate("date"),
                       rs.getString("industry_name"), rs.getString("category_name"),
                       rs.getString("options_name"), rs.getString("career_name"),
                       rs.getString("notes"));
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Appointment!");
       }finally{
           cleanUp();
       }
       return app;
    }
    
    public static void updateAppointmentById(Appointment appoint){
       rs = null;
       String query = "UPDATE appointment SET client_id = ?, consultant_id = ?,"
               + " date = ?, industry_name = ?, category_name = ?, options_name = ?,"
               + " career_name = ?, notes = ? WHERE appoint_id = ?";
       Object[][] binds = {{"int", Integer.toString(appoint.getClient_id())},
           {"int", Integer.toString(appoint.getConsultant_id())}, {"date", appoint.getDate()},
           {"string", appoint.getIndustry_name()}, {"string", appoint.getSub_cat_name()},
           {"string", appoint.getOptions_name()}, {"string", appoint.getCareer_name()},
           {"string", appoint.getNotes()}, {"string", appoint.getId()}};
       try{
           databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not update Appointments!");
       }finally{
           cleanUp();
       }
    }
        // Andrei Rico
    // 3106107616
    public static void addAppointments(int client_id, int consultant_id, Date date,
            String industry_name, String sub_cat_name, String options_name,
            String career_name){
         String query = "INSERT INTO appointment VALUES(NULL, ?, ?, ?, ?,?,?,?, NULL)";
         Object[][] binds = {{"int",Integer.toString( client_id)},{"int",Integer.toString( consultant_id)},{"date", date},
             {"string", industry_name},{"string", sub_cat_name},{"string", options_name},{"string",career_name}};
       try{
           rs = databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Consultant!");
       }finally{
           cleanUp();
       }
    }
    
    /*
    **
    **      CLIENT TABLE COMMANDS
    **
    */
    public static ArrayList getClients(){
       rs = null;
       ArrayList<Client> clientList = new ArrayList<>();
       String query = "SELECT * FROM client";
       try{
           rs = databaseQuery(query);
           while(rs.next()){
               Client c = new Client(rs.getInt("client_id"), rs.getString("first_name"),
                       rs.getString("last_name"), rs.getString("date_of_birth"),
                       rs.getString("phone_number"), rs.getString("email"),
                       rs.getString("login_time"), rs.getString("username"),
                       rs.getString("password"));
               clientList.add(c);
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Clients!");
       }finally{
           cleanUp();
       }
       return clientList;
    }
    
      // Cleint Part start here ...
    /**
 *
 * Student No : 6100758617
 * Student name: Waleed Jamaldini
 * bugs : none
 * Date 13/06/2017
 */
       public static ArrayList getCleints(){
       rs = null;
        ArrayList<Client> clientList = new ArrayList<>();
        String query = "SELECT * FROM client";
        try{
            rs = databaseQuery(query);
            while(rs.next()){
                Client client = new Client(rs.getInt("client_id"),
                        rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("date_of_birth"), rs.getString("phone_number"),
                        rs.getString("email"), rs.getString("login_time"),
                        rs.getString("username"), rs.getString("password"));
                 clientList.add(client);
            }
            }catch(Exception e) {
                if(DEBUG) System.out.println("ERROR! could not retrieve Cleints!");
                }finally{
                    cleanUp(); // Close all connectections
                }
            return clientList;
        }
         
    public static void addClient (Client c){

       String query = "INSERT INTO Client VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
       String[][] binds = {{"string", c.getFirst_name()},{"string", c.getLast_name()},
           {"string", c.getDate_of_birth()}, {"string", c.getPhone_number()},
          {"string", c.getEmail()}, {"string", c.getLogin_time()},
          {"string", c.getUsername()},{"string", c.getPassword()}};
       try{
           rs = databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Clients!");
       }finally{
           cleanUp();
       }          
    }
         public static void updateClient(Client c){
             
        String query = "UPDATE client SET first_name = ? , last_name = ?, date_of_birth = ?," +
      "phone_number = ?, email = ?, login_time = ?, username = ?, password = ?  WHERE client_id = ?";
         String[][] binds = {{"string", c.getFirst_name()},{"string", c.getLast_name()},
           {"string", c.getDate_of_birth()}, {"string", c.getPhone_number()},
          {"string", c.getEmail()}, {"string", c.getLogin_time()},
          {"string", c.getUsername()},{"string", c.getPassword()},{"int", Integer.toString(c.getId())}};
        try{
            databaseQuery(query, binds);
        }catch(Exception e){
            if(DEBUG) System.out.println("ERROR! Could not update Clients!");
        }finally{
            cleanUp();
            
        }
    }
        public static Client getClientById(int id){
       rs = null;
       Client c = null;
       String query = "SELECT * FROM client WHERE client_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               c = new Client(rs.getInt("client_id"), rs.getString("first_name"),
                       rs.getString("last_name"), rs.getString("date_of_birth"),
                       rs.getString("phone_number"), rs.getString("email"),
                       rs.getString("login_time"), rs.getString("username"),
                       rs.getString("password"));
           }
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Clients!");
       }finally{
           cleanUp();
       }
       return c;
    }
    
    /*
    **
    **      ADMINISTRTOR TABLE COMMANDS
    **
    */
    public static ArrayList getAdmins(){
       rs = null;
       Administrator admin = null;
       ArrayList<Administrator> adminList = new ArrayList<>();
       String query = "SELECT * FROM administrator";
       try{
           rs = databaseQuery(query);
           while(rs.next()){
               admin = new Administrator(rs.getInt("administrator_id"), rs.getString("first_name"),
                       rs.getString("last_name"), rs.getString("username"),
                       rs.getString("password"));
               adminList.add(admin);
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Administrators!");
       }finally{
           cleanUp();
       }
       return adminList;
    }
    
    public static Administrator getAdminById(int id){
       rs = null;
       Administrator admin = null;
       String query = "SELECT * FROM administrator WHERE administrator_id = ?";
       String[][] binds = {{"int", Integer.toString(id)}};
       try{
           rs = databaseQuery(query, binds);
           while(rs.next()){
               admin = new Administrator(rs.getInt("administrator_id"), rs.getString("first_name"),
                       rs.getString("last_name"), rs.getString("username"),
                       rs.getString("password"));
           }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not retrieve Administrator!");
       }finally{
           cleanUp();
       }
       return admin;
    }
    
    public static void updateAdmin(Administrator admin){
        rs = null;
        String query = "UPDATE administrator SET first_name = ?, last_name = ?,"
                + " username = ?, password = ? WHERE administrator_id = ?";
        String[][] binds = {{"string", admin.getFirst_name()}, {"string",
            admin.getLast_name()}, {"string", admin.getUsername()},
            {"string", admin.getPassword()}, {"int", Integer.toString(admin.getId())}};
               try{
           databaseQuery(query, binds);
       }catch(Exception e){
           if(DEBUG) System.out.println("ERROR! could not Update Administrator!");
       }finally{
           cleanUp();
       }
    }
    

    /*
    **
    **      MISC COMMANDS
    **
    */
    public static boolean testConnection(String user, String pass){
        boolean isValid = true;
        String url = Settings.getSetting("url_db");
        
        try{
            Class.forName(JDBC_DRIVER);
            if(DEBUG) System.out.println("\n|DATABASE CONNECTING|");
            conn = DriverManager.getConnection(url, user, pass);
        }catch(SQLException | ClassNotFoundException se){
            if(DEBUG) se.printStackTrace();
            isValid = false;
        }finally{
            cleanUp();
        }
        return isValid;
    }
    
    public static void changeDbuser(String user, String pass, String oldUser){
        String dbName = Settings.getSetting("db");
        String query1 = "GRANT SELECT, INSERT, DELETE, UPDATE ON " + dbName + ".* TO ?@'localhost' IDENTIFIED BY ?";
        String[][] binds1 = {{"string", user}, {"string", pass}};
        String query2 = "GRANT SELECT, INSERT, DELETE, UPDATE ON " + dbName + ".* TO ?@'%' IDENTIFIED BY ?";
        String[][] binds2 = {{"string", user}, {"string", pass}};
        
        try{
            if(!oldUser.equals("root") && !oldUser.equals("admin")){
                if(!user.equals("root") && !user.equals("admin")){
                    String query = "DROP USER IF EXISTS ?";
                    String[][] binds = {{"string", oldUser}};
                    databaseQuery(query, binds, adminUser, adminPass);
                    databaseQuery(query1, binds1, adminUser, adminPass);
                    databaseQuery(query2, binds2, adminUser, adminPass);
                }
            }else{
                databaseQuery(query1, binds1, adminUser, adminPass);
                databaseQuery(query2, binds2, adminUser, adminPass);
            }
       }catch(SQLException e){
           if(DEBUG) System.out.println("ERROR! could not change database user!");
       }finally{
           cleanUp();
       }
    }
    
}
