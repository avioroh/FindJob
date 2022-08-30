package com.example.hello;

public class JobClass {
    String JobTitle,ID,Location,JobType,ContentAboutTheCompany,JobRequirements,Category,EmployerID;

    public JobClass(String jobTitle,String id, String location, String jobType,
                    String contentAboutTheCompany, String jobRequirements,String category,String employerID) {
        JobTitle = jobTitle;
        ID = id;

        Location = location;
        JobType = jobType;
        ContentAboutTheCompany = contentAboutTheCompany;
        JobRequirements = jobRequirements;
        Category = category;
        EmployerID = employerID;
    }

    public JobClass() {
    }

    public String getEmployerID() {
        return EmployerID;
    }

    public void setEmployerID(String employerID) {
        EmployerID = employerID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    public String getContentAboutTheCompany() {
        return ContentAboutTheCompany;
    }

    public void setContentAboutTheCompany(String contentAboutTheCompany) {
        ContentAboutTheCompany = contentAboutTheCompany;
    }

    public String getJobRequirements() {
        return JobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        JobRequirements = jobRequirements;
    }
}
