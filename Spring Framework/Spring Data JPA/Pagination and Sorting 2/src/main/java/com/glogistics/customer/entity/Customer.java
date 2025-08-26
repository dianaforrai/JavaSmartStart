package com.glogistics.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @NotBlank(message = "Customer name is mandatory")
    private String custName;

    @NotBlank(message = "Contact number is mandatory")
    private String custContactNo;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String custEmail;

    @NotBlank(message = "City is mandatory")
    private String custCity;

    @NotBlank(message = "Address is mandatory")
    private String custAddress;

    public String getCustEmail() {
        return custEmail;
    }
    public String getCustAddress() {
        return custAddress;
    }

    public String getCustCity() {
        return custCity;
    }
}
