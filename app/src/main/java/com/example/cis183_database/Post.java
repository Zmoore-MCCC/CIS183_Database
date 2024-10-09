package com.example.cis183_database;

public class Post
{
    private int id;
    private int u_id;
    private String category;
    private String postData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPost() {
        return postData;
    }

    public void setPost(String post) {
        this.postData = post;
    }
}
