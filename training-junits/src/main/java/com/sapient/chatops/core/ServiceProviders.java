package com.sapient.chatops.core;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.sapient.chatops.core.service.CommandContextService;
import com.sapient.chatops.core.service.PeristenceService;

public class ServiceProviders {
	
	private static ServiceLoader<PeristenceService> persistenceServiceLoader = ServiceLoader.load(PeristenceService.class);
	private static ServiceLoader<CommandContextService> commandContextServiceLoader = ServiceLoader.load(CommandContextService.class);

	private static ServiceProviders instance = null;
	
	private ServiceProviders() {};
	
	public static ServiceProviders getInstance() {
		if (instance == null) {
			instance = new ServiceProviders();
		}
		return instance;
	}
	
	/**
	 * Setter for the purpose of unit testing only 
	 * 
	 * @param provider
	 */
	public static void setInstance(ServiceProviders provider) {
		if (provider != null) {
			ServiceProviders.instance = provider;
		}
	}
	
	public PeristenceService getPeristenceService() {
		Iterator<PeristenceService> iterator = persistenceServiceLoader.iterator();
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}

	public CommandContextService getCommandContextService() {
		Iterator<CommandContextService> iterator = commandContextServiceLoader.iterator();
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}
	
	
}
