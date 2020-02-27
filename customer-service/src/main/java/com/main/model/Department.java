package com.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Department {
	@Id
	@GeneratedValue
	private int id;
	private String price;
	private String items;

	private String type;

	public int getId() {
		return id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	public String getName() {
		return price;
	}

	public void setName(String name) {
		this.price = name;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String Items) {
		this.items = Items;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
