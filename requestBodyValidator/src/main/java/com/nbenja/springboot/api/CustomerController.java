package com.nbenja.springboot.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import javax.validation.Valid;

/**
 *
 */
@RestController
public class CustomerController {

    @PostMapping("/customer")
    public ResponseEntity createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        if(result.hasErrors()) {
            return  new ResponseEntity(result.getAllErrors().stream().collect(Collectors.mapping(ObjectError::getDefaultMessage, Collectors.toList())),HttpStatus.BAD_GATEWAY);
        }
     return new ResponseEntity(customer, HttpStatus.CREATED);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new customerValidator());
    }
}

class Customer {
    private String firstName;
    private String lastName;
    private int age;
    private String email;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, true);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }
}

class customerValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", "Last name can't be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Email can't be empty");
        Customer customer = (Customer) o;
    }
}