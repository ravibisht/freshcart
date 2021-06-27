package com.freshcart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;


@Component
@Entity
@DynamicUpdate
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String password;

    private String email;

    @Column(name = "mobileNo")
    private String mobileNo;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

/*  @OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Project> projects=new ArrayList<Project>();*/


//--------------------------  Constructors ------------------------------------

    public User() {
    }

    public User(int id, String username, String firstName, String lastName,
                String password, String email, String mobileNo, String bio,
                String profilePicture, String profession, String resetToken,
                Date createdDate, Date updatedDate) {

        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.profilePicture = profilePicture;
        this.resetToken = resetToken;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    //---------------------------To String ----------------------------------------

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", firstname=" + firstName + ", lastname=" + lastName
                + ", password=" + password + ", email=" + email + ", mobileNo=" + mobileNo
                + ", profilePicture=" + profilePicture + ", reset_token=" + resetToken
                + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
    }


//--------------------   Getters And Setters ---------------------------------------

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobile_no) {
        this.mobileNo = mobile_no;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date CreatedDate) {
        createdDate = CreatedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String reset_token) {
        this.resetToken = reset_token;
    }

}
