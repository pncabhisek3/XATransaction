package com.spring.jta.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "hdfc_account")
public class HdfcAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer _id;
	private BigDecimal amount;

	public HdfcAccount() {
	}

	public HdfcAccount(Integer _id, BigDecimal amount) {
		this._id = _id;
		this.amount = amount;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "HdfcAccount [_id=" + _id + ", amount=" + amount + "]";
	}

}
