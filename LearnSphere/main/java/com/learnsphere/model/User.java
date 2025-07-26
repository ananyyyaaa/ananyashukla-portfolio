package com.learnsphere.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    public enum Role {
        STUDENT,
        INSTRUCTOR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String location;
    private String website;
 

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getProfilePicture() {
        return profilePicture;
    }
  
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }
  
    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }
  
    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }
  
    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCreatedAt() {
        return createdAt;
    }
  
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
  
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Role getRole() {
        return role;
    }
  
    public void setRole(Role role) {
        this.role = role;
    }
 


        private String resetPasswordToken;

       

        public String getResetPasswordToken() {
            return resetPasswordToken;
        }

        public void setResetPasswordToken(String resetPasswordToken) {
            this.resetPasswordToken = resetPasswordToken;
        }
  

   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) &&
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
