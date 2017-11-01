package ru.harionovsky.bunstore.models;
// Generated 27.10.2017 11:14:31 by Hibernate Tools 4.3.1



/**
 * Orders generated by hbm2java
 */
public class Orders  implements java.io.Serializable {


     private int id;
     private String fio;
     private String phone;
     private String address;
     private Boolean isdone;

    public Orders() {
    }

	
    public Orders(int id, String fio, String phone, String address) {
        this.id = id;
        this.fio = fio;
        this.phone = phone;
        this.address = address;
    }
    public Orders(int id, String fio, String phone, String address, Boolean isdone) {
       this.id = id;
       this.fio = fio;
       this.phone = phone;
       this.address = address;
       this.isdone = isdone;
    }
    public Orders(String fio, String phone, String address) {
        this.fio = fio;
        this.phone = phone;
        this.address = address;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getFio() {
        return this.fio;
    }
    
    public void setFio(String fio) {
        this.fio = fio;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public Boolean getIsdone() {
        return (this.isdone == null ? false : this.isdone);
    }
    
    public void setIsdone(Boolean isdone) {
        this.isdone = isdone;
    }




}


