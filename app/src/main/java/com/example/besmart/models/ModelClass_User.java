package com.example.besmart.models;

public class ModelClass_User {

    private String name;
    private String email;
    private String status;
    private int ideiaApproved;
    private int ideiawritings;
    private String photoLink;

    public ModelClass_User(String name, String email, String status, int ideiaApproved, int ideiawritings, String photoLink) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.ideiaApproved = ideiaApproved;
        this.ideiawritings = ideiawritings;
        this.photoLink = photoLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdeiaApproved() {
        return ideiaApproved;
    }

    public void setIdeiaApproved(int ideiaApproved) {
        this.ideiaApproved = ideiaApproved;
    }

    public int getIdeiawritings() {
        return ideiawritings;
    }

    public void setIdeiawritings(int ideiawritings) {
        this.ideiawritings = ideiawritings;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

}
