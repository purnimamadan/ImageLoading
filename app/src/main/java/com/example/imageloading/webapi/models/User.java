package com.example.imageloading.webapi.models;

public class User {

    public String id;
    public String username;
    public String name;
    public ProfileImage profile_image;
    public Links links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileImage getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(ProfileImage profile_image) {
        this.profile_image = profile_image;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
