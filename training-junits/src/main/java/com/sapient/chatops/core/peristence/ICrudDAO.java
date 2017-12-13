package com.sapient.chatops.core.peristence;

import java.util.List;

public interface ICrudDAO<T> {

	T create(T t);

	T retrieve(Class<T> clazz,Object id);
	
	List<T> retrieveAll(Class<T> clazz,int limit);

	T update(T t);

	boolean delete(Class<T> clazz,T t);

	List<T> retrieveStatusRecords(Class<T> clazz,String date1 , String date2);

	List<T> retrieveStatusRecordsForPerson(Class<T> clazz,String name ,String date1 , String date2);

}
