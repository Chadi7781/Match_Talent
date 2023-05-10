
package com.mycompany.entities;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author firas
 */
public class Utilisateur {
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String contact;
    
    private String role;

    public Utilisateur() {
    }

    public Utilisateur(int id, String name, String email, String password, String contact, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.role = role;
    }

    public Utilisateur(String name, String email, String contact, String role) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.role = role;
    }

    public Utilisateur(String name, String email, String password, String contact, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}