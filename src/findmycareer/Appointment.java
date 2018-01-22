package findmycareer;


import java.sql.Date;



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
public class Appointment {
    private int id;
    private int client_id;
    private int consultant_id;
    private Date date;
    private String industry_name;
    private String sub_cat_name;
    private String options_name;
    private String career_name;
    private String notes;
    
    
    
    public Appointment(int client_id, int consultant_id, Date date,
            String industry_name, String sub_cat_name, String options_name,
            String career_name, String notes){
        this.client_id = client_id;
        this.consultant_id = consultant_id;
        this.date = date;
        this.industry_name = industry_name;
        this.sub_cat_name = sub_cat_name;
        this.options_name = options_name;
        this.career_name = career_name;
        this.notes = notes;
    }
    public Appointment(int id, int client_id, int consultant_id, Date date,
            String industry_name, String sub_cat_name, String options_name,
            String career_name, String notes){
        this.id = id;
        this.client_id = client_id;
        this.consultant_id = consultant_id;
        this.date = date;
        this.industry_name = industry_name;
        this.sub_cat_name = sub_cat_name;
        this.options_name = options_name;
        this.career_name = career_name;
        this.notes = notes;
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
     * @return the client_id
     */
    public int getClient_id() {
        return client_id;
    }

    /**
     * @param client_id the client_id to set
     */
    public void setClient_id(int client_id) {
        this.client_id = client_id;
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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the industry_name
     */
    public String getIndustry_name() {
        return industry_name;
    }

    /**
     * @param industry_name the industry_name to set
     */
    public void setIndustry_name(String industry_name) {
        this.industry_name = industry_name;
    }

    /**
     * @return the sub_cat_name
     */
    public String getSub_cat_name() {
        return sub_cat_name;
    }

    /**
     * @param sub_cat_name the sub_cat_name to set
     */
    public void setSub_cat_name(String sub_cat_name) {
        this.sub_cat_name = sub_cat_name;
    }

    /**
     * @return the options_name
     */
    public String getOptions_name() {
        return options_name;
    }

    /**
     * @param options_name the options_name to set
     */
    public void setOptions_name(String options_name) {
        this.options_name = options_name;
    }

    /**
     * @return the career_name
     */
    public String getCareer_name() {
        return career_name;
    }

    /**
     * @param career_name the career_name to set
     */
    public void setCareer_name(String career_name) {
        this.career_name = career_name;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Override
    public String toString(){
        String message = "ID: " + getId() + " | CONSULTANT ID: " + getConsultant_id() + " | DATE: " + getDate();
        return message;
    }

    
    
}
