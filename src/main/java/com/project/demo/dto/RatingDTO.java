package com.project.demo.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDTO {

    @DecimalMin(value = "0")
    @DecimalMax(value = "5")
	private int value;

}
