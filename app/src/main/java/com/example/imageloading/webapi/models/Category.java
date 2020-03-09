package com.example.imageloading.webapi.models;

public class Category {

    public Integer id;
    public String title;
    public Integer photo_count;
    public Links_ links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPhoto_count() {
        return photo_count;
    }

    public void setPhoto_count(Integer photo_count) {
        this.photo_count = photo_count;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }
}
