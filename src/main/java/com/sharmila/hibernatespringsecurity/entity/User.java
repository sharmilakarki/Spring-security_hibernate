/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sharmila
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_date",insertable = false)
    private Date addedDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date",nullable = true)
    private Date modifiedDate;
    @Column(name = "enabled")
    private boolean status;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<UserRoles> userRoleList;

    public User() {
    }

    public User(int id, String userName, String password, String email, String address, Date addedDate, Date modifiedDate, boolean status, List<UserRoles> userRoleList) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.addedDate = addedDate;
        this.modifiedDate = modifiedDate;
        this.status = status;
        this.userRoleList = userRoleList;
    }

   

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//
//    public List<UserRoles> getUserRoleList() {
//        return userRoleList;
//    }
//
//    public void setUserRoleList(List<UserRoles> userRoleList) {
//        this.userRoleList = userRoleList;
//    }

    

   

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<UserRoles> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRoles> userRoleList) {
        this.userRoleList = userRoleList;
    }

    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email + ", address=" + address + ", addedDate=" + addedDate + ", modifiedDate=" + modifiedDate + ", status=" + status + '}';
    }

}
