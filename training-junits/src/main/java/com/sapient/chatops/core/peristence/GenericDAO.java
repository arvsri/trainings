package com.sapient.chatops.core.peristence;

import java.util.List;

public class GenericDAO<T> implements ICrudDAO<T> {

	@Override
	public T create(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T retrieve(Class<T> clazz, Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> retrieveAll(Class<T> clazz, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Class<T> clazz, T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<T> retrieveStatusRecords(Class<T> clazz, String date1,
			String date2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> retrieveStatusRecordsForPerson(Class<T> clazz, String name,
			String date1, String date2) {
		// TODO Auto-generated method stub
		return null;
	}}
