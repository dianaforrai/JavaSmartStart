package com.gl.app.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private long id;

    // customer name must not be null or empty
    @NotEmpty
    private String customerName;

    // customer email must not be empty or null
    // customer must have a valid email id
    @NotEmpty
    @Email
    private String customerEmail;

    private Set<FreightDto> freights;
}