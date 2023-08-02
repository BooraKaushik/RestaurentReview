package com.project.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemDTO {
	private long id;
	private float price;
	private float calories;
	private FoodDTO food;

}
