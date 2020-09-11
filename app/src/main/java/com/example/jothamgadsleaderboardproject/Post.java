package com.example.jothamgadsleaderboardproject;

import android.text.Editable;

import com.google.gson.annotations.SerializedName;

public class Post {
    private String EmailAddress;
    private String Name;
    private String LastName;
    private String LinkToProject;

    public Post(String emailAddress, String name, String lastName, String linkToProject) {
        EmailAddress = emailAddress;
        Name = name;
        LastName = lastName;
        LinkToProject = linkToProject;
    }



    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getName() {
        return Name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getLinkToProject() {
        return LinkToProject;
    }
}
