package org.example;

public class User {
    public int id;
    private String name;
    private String city;
    private String role;
    public User(int id,String name,String city,String role){
        role=Character.toUpperCase(role.charAt(0))+role.substring(1);
        name=Character.toUpperCase(name.charAt(0))+name.substring(1);
        city=Character.toUpperCase(city.charAt(0))+city.substring(1);
        this.id=id;
        this.name=name;
        this.city=city;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setCity(String city) {
        city=Character.toUpperCase(city.charAt(0))+city.substring(1);
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        name=Character.toUpperCase(name.charAt(0))+name.substring(1);
        this.name = name;
    }

    public void setRole(String role) {
        role=Character.toUpperCase(role.charAt(0))+role.substring(1);
        this.role = role;
    }
}
