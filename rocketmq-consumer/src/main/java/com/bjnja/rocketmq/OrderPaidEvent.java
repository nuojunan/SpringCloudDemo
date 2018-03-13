package com.bjnja.rocketmq;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderPaidEvent implements Serializable{
	private static final long serialVersionUID = 1L;

	private String orderId;
    
    private BigDecimal paidMoney;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}

	public OrderPaidEvent(String orderId, BigDecimal paidMoney) {
		super();
		this.orderId = orderId;
		this.paidMoney = paidMoney;
	}
    
}
