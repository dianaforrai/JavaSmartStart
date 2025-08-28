package com.gl.app.payload;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreightDto {

    private long orderId;

    @NotNull(message = "Freight origin must not be null")
    private String origin;

    @Size(min = 4,max = 10,message = "Freight destination must be in between 4 to 10 characters long")
    @NotNull(message = "Freight destination must not be null")
    private String destination;
}
