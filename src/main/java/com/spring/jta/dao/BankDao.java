package com.spring.jta.dao;

import java.util.List;

public interface BankDao<T> {

	public void insert(T obj) throws Exception;

	public void update(T obj) throws Exception;

	public void delete(T obj) throws Exception;

	public void deleteById(Integer _id) throws Exception;

	public T getOne(Integer _id) throws Exception;

	public List<T> getAll() throws Exception;
	
}
