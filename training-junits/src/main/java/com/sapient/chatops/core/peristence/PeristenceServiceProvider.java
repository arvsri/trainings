package com.sapient.chatops.core.peristence;

import com.sapient.chatops.core.service.PeristenceService;

public class PeristenceServiceProvider implements PeristenceService {

	@Override
	public <T> ICrudDAO<T> getDAO(Class<T> t) {
		return new GenericDAO<T>();
	}

}
