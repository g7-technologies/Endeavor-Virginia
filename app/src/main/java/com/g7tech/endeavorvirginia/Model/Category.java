package com.g7tech.endeavorvirginia.Model;

public class Category {

    private int id;
    private String type_name;

    public Category(){}

    public Category(int id, String type_name){
        this.id = id;
        this.type_name = type_name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String type_name){
        this.type_name = type_name;
    }

    public int getId(){
        return this.id;
    }

    public String getType_name(){
        return this.type_name;
    }

}