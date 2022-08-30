package com.example.hello;

public class Candidate {
    String Fullname,ID,Adreess,Phone,Academic_studies,Exp,Lenguages, Email;



    public Candidate (String fullname, String id, String address, String phone,
                      String academic_studies, String exp, String lenguages, String email){
        Fullname = fullname;
        ID = id;
        Adreess = address;
        Phone = phone;
        Academic_studies = academic_studies;
        Exp = exp;
        Lenguages = lenguages;
        Email = email;
    }



    public Candidate(){

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

    public String getAddress() {
        return Adreess;
    }

    public void setAddress(String address) {
        Adreess = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAcademic_studies() {
        return Academic_studies;
    }

    public void setAcademic_studies(String academic_studies) {
        Academic_studies = academic_studies;
    }

    public String getExp() {
        return Exp;
    }

    public void setExp(String exp) {
        Exp = exp;
    }
    public String getLenguages() {
        return Lenguages;
    }

    public void setLenguages(String lenguages) {
        Lenguages = lenguages;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
