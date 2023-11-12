package com.osa.entity;

import java.util.List;

import javax.persistence.Entity;

import com.osa.entity.OrderProductQuantity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInput {
	private String fullName;
	private String fullAddress;
	private String contactNumber;
	private String alternateContactNumber;
	private String transactionId;
	private List<OrderProductQuantity> orderProductQuantityList;
	

}
