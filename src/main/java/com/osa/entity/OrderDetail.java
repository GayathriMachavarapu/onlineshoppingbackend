package com.osa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private String orderFullName;
	private String orderFullAddress;
	private String orderContactNumber;
	private String orderAlternateContactNumber;
	private String orderStatus;
	private double orderAmount;
	@OneToOne
	private Product product;
	@OneToOne
	private User user;
	private String transactionId;
	public OrderDetail( String orderFullName, String orderFullAddress, String orderContactNumber,
			String orderAlternateContactNumber, String orderStatus, double orderAmount, Product product, User user,
			String transactionId) {
		super();
		this.orderFullName = orderFullName;
		this.orderFullAddress = orderFullAddress;
		this.orderContactNumber = orderContactNumber;
		this.orderAlternateContactNumber = orderAlternateContactNumber;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
		this.product = product;
		this.user = user;
		this.transactionId = transactionId;
	}
	
	

}
