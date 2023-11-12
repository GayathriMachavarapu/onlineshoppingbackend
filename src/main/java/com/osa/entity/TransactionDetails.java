package com.osa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDetails {
	
	private String orderId;
	private String curreny;
	private int amount;
	private String key;

}
