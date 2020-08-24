package com.iiht.evaluation.coronokit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitDetail {

	//private int id;
	//private int coronaKitId;
	private int productId;
	private String name;
	private int amount;
	
	
	public KitDetail(int productId, String name, int amount) {
		//this.id = id;
		//this.coronaKitId = coronaKitId;
		this.productId = productId;
		this.name = name;
		this.amount = amount;
	}

	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCoronaKitId() {
		return coronaKitId;
	}
	public void setCoronaKitId(int coronaKitId) {
		this.coronaKitId = coronaKitId;
	}*/
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
