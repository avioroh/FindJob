package com.example.hello;

public class Employer {

     String CompanyName,Fullname,ID,Adreess,Email;

    public Employer(String companyName, String fullname, String id, String adreess,String email) {
        CompanyName = companyName;
        Fullname = fullname;
        ID = id;
        Adreess = adreess;
        Email = email;
    }
    
    public Employer(){
        
    }



    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAdreess() {
        return Adreess;
    }

    public void setAdreess(String adreess) {
        Adreess = adreess;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
