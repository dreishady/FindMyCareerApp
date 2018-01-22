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
 * @Date 11/06/2017
 * @Bugs None
 */
public class Career {
    
    private int id;
    private int options_id;
    private int consultant_id;
    private String name;
    private String desc;
    private String demand;
    private int min_salary;
    private int max_salary;
    
    
    public Career(int options_id, int con_id, String name, String desc,
            String demand, int min, int max){
        this.options_id = options_id;
        this.consultant_id = con_id;
        this.name = name;
        this.desc = desc;
        this.demand = demand;
        this.min_salary = min;
        this.max_salary = max;
        
    }
    public Career(int id, int options_id, int con_id, String name, String desc,
            String demand, int min, int max){
        this.id = id;
        this.options_id = options_id;
        this.consultant_id = con_id;
        this.name = name;
        this.desc = desc;
        this.demand = demand;
        this.min_salary = min;
        this.max_salary = max;
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the options_id
     */
    public int getOptions_id() {
        return options_id;
    }

    /**
     * @param options_id the options_id to set
     */
    public void setOptions_id(int options_id) {
        this.options_id = options_id;
    }

    /**
     * @return the consultant_id
     */
    public int getConsultant_id() {
        return consultant_id;
    }

    /**
     * @param consultant_id the consultant_id to set
     */
    public void setConsultant_id(int consultant_id) {
        this.consultant_id = consultant_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the demand
     */
    public String getDemand() {
        return demand;
    }
    
    public int getDemandForSlider(){
        int d = 0;
        switch(getDemand().toLowerCase()){
            case "low":
                d = 0;
                break;
            case "medium":
                d = 1;
                break;
            case "high":
                d = 2;
                break;
            default:
                d = 0;
                break;
        }
        return d;
    }

    /**
     * @param demand the demand to set
     */
    public void setDemand(String demand) {
        this.demand = demand;
    }

    /**
     * @return the min_salary
     */
    public int getMin_salary() {
        return min_salary;
    }

    /**
     * @param min_salary the min_salary to set
     */
    public void setMin_salary(int min_salary) {
        this.min_salary = min_salary;
    }

    /**
     * @return the max_salary
     */
    public int getMax_salary() {
        return max_salary;
    }

    /**
     * @param max_salary the max_salary to set
     */
    public void setMax_salary(int max_salary) {
        this.max_salary = max_salary;
    }
    
    public String cbxString(){
        String msg = Integer.toString(getId()) + " " + getName();
        return msg;
    }
    
    @Override
    public String toString(){
        String message = "ID: " + getId() + " | OPTION ID: " + getOptions_id() +
                " | CONSULTANT ID: " + getConsultant_id() + " | NAME: " + getName() + 
                " | DESCRIPTION: " + getDesc() + " | DEMAND: " + getDemand() + 
                " | MIN SALARY: " + getMin_salary() + " | MAX SALARY: " + getMax_salary();
        return message;
    }
    
    
    
}
