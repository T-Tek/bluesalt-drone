package com.blusaltdrone.dtos;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationRequestDto {

    @NotBlank(message = "Medication name is required")
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Medication name can only contain letters, numbers, '-' and '_'")
    private String name;

    @Min(value = 1, message = "Weight must be at least 1 gram")
    private int weight;

    @NotBlank(message = "Code is required")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Code can only contain uppercase letters, numbers, and '_'")
    private String code;

    @NotBlank(message = "Image URL is required")
    @Pattern(regexp = "^(http|https)://.*$", message = "Image must be a valid URL")
    private String image;
}
