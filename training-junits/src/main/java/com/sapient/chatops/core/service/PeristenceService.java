package com.sapient.chatops.core.service;

import com.sapient.chatops.core.peristence.ICrudDAO;

public interface PeristenceService {
	
	<T> ICrudDAO<T> getDAO(Class<T> t);
	
}
