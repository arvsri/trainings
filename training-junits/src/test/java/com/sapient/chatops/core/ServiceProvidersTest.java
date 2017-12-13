package com.sapient.chatops.core;

import org.junit.Assert;
import org.junit.Test;

import com.sapient.chatops.core.service.CommandContextService;


public class ServiceProvidersTest {

	@Test
	public void verifyGetCommandContextService(){
		
		ServiceProviders serviceProvider = ServiceProviders.getInstance(); 
		CommandContextService commandContextService = serviceProvider.getCommandContextService();
		Assert.assertNotNull("Command Context Service Not Found !!",commandContextService);
	}
	
}
