package ru.harionovsky.bunstore.models;
// Generated 24.10.2017 16:31:27 by Hibernate Tools 4.3.1

import java.io.Serializable;


/**
 * Wares generated by hbm2java
 */

public class Ware implements Serializable {


     private int id;
     private String code;
     private String name;
     private String description;

    public Ware() {
    }

	
    public Ware(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    public Ware(int id, String code, String name, String description) {
       this.id = id;
       this.code = code;
       this.name = name;
       this.description = description;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}