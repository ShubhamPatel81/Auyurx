package com.auyurx.Forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserForm {
    @NotBlank(message = "Name is Required")
    @Size(min = 3, message = "Minimum 3 character required")
    private String firstName;
    @Size(min = 3, message = "Minimum 3 character required")
    private String lastName;

    @Getter
    @Email(message = "Email is Required")
    private String email;

    @Getter
    @NotBlank(message = "Password is Required")
    @Size(min = 6, message = "Minimum 6 character is required")
    private String password;

    @Getter
    @NotBlank
    private String phoneNumber;

    public String getName() {
        return firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.firstName = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
