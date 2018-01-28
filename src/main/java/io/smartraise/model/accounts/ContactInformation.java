package io.smartraise.model.accounts;

import org.springframework.data.annotation.Id;

/**
 * Contact information for a user
 */
public class ContactInformation {

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phone;

    public ContactInformation(String username) {
        this.username = username;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.address = "372 Memorial Drive";
        this.city = "Cambridge";
        this.state = "MA";
        this.country = "USA";
        this.zipCode = "02139";
        this.phone = "123-456-7890";
    }

    public ContactInformation() {
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.country = "";
        this.zipCode = "";
        this.phone = "";
    }
//
//    private ContactInformation(String username, String firstName, String lastName, String email) {
//        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }

    public String getUsername() {
        return username;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    public ContactInformation getPrivate() {
//        return new ContactInformation(
//                this.username,
//                this.firstName,
//                this.lastName,
//                this.email);
//    }
}
