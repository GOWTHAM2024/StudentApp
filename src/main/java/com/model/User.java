package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "user") //uniqueConstraints = { @UniqueConstraint(columnNames = { "mailid"})})

public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private int id;

    @Column(unique = true)
    private String mailid;

    @Column(name = "password")
    private String password;

    @Column(name = "confirmpassword")
    private String confirmPassword;

    @Column(name = "forgetpassword")
    private String forgetPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn (name = "role_id")
    private Set<Role> role=new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getconfirmPassword() {
        return confirmPassword;
    }

    public void setconfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getForgetPassword() {
        return forgetPassword;
    }

    public void setForgetPassword(String forgetPassword) {
        this.forgetPassword = forgetPassword;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [confirmPassword=" + confirmPassword + ", forgetPassword=" + forgetPassword + ", id=" + id
                + ", mailid=" + mailid + ", password=" + password + ", role=" + role + "]";
    }

    
}