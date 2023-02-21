package com.contact.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    @NotBlank(message = "id is mandatory")
    private String idContact;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 5 , message = "Value should be greater then equal to 5")
    @Size(max = 20 , message = "Value should be less then equal to 20")
    private String name;

    @NotBlank(message = "email is mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid email address")
    private String email;
    @NotBlank(message = "phone is mandatory")
    private String phone;
}
