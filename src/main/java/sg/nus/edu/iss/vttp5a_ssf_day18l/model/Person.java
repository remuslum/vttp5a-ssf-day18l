package sg.nus.edu.iss.vttp5a_ssf_day18l.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {
    @NotNull(message = "Id cannot be blank")
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, message = "Name must consist of more than 2 characrers")
    private String fullName;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Phone number must be at least 7 digits")
    private String phone;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "invalid email format")
    private String email;

    @NotNull(message = "Date of Birth cannot be blank")
    private LocalDate dateOfBirth;

    public Person(){
        
    }
    
    public Person(Integer id, String fullName, String phone, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return id + "," + fullName + "," + phone + "," + email + "," + formatter.format(dateOfBirth);
    }
}
