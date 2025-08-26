package com.glogistics.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @NotBlank(message = "Customer name is required")
    private String custName;

    @NotBlank(message = "Contact number is required")
    private String custContactNo;

    @Email(message = "Invalid email format")
    private String custEmail;

    @NotBlank(message = "City cannot be empty")
    private String custCity;

    @Pattern(regexp = "Bangalore|Mysore|Mangalore|Chennai|Hyderabad|Mumbai|Pune|Noida",
            message = "Address must be from allowed cities")
    private String custAddress;

    public String getCustEmail() {
        return custEmail;
    }

    public String getCustAddress() {
        return custAddress;
    }

}
