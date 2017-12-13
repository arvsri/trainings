package com.sapient.chatops.core.commands;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.sapient.chatops.core.ServiceProviders;
import com.sapient.chatops.core.peristence.ICrudDAO;
import com.sapient.chatops.core.service.PeristenceService;

/**
 * Unit test case for {@link CommandContextServiceProvider}
 * 
 * @author asri68
 *
 */
public class CommandContextServiceProviderTest {

	@Test
	public void testFindClosestMatch() throws Exception{
		
		ServiceProviders.setInstance(Mockito.mock(ServiceProviders.class));
		Mockito.when(ServiceProviders.getInstance().getPeristenceService()).thenReturn(Mockito.mock(PeristenceService.class));
		Mockito.when(ServiceProviders.getInstance().getPeristenceService().getDAO(CommandContext.class)).thenReturn(Mockito.mock(ICrudDAO.class));
		
		List<CommandContext> contexts = new ArrayList<CommandContext>();
		contexts.add(new CommandContext());
		Mockito.when(ServiceProviders.getInstance().getPeristenceService().getDAO(CommandContext.class).retrieveAll(CommandContext.class, 1)).thenReturn(contexts);

		
		List<String> supportedServices = new ArrayList<String>();
		supportedServices.add("alert-service");
		supportedServices.add("wolf-service");
		
		CommandContextServiceProvider provider = new CommandContextServiceProvider();
		Assert.assertEquals("Correct match not found !","wolf-service", provider.findClosestMatch("wolf-service", supportedServices));
		Assert.assertEquals("Correct match not found !","wolf-service", provider.findClosestMatch("wolf", supportedServices));
		Assert.assertEquals("Correct match not found !","wolf-service", provider.findClosestMatch("Wolf", supportedServices));
		Assert.assertEquals("Correct match not found !","wolf-service", provider.findClosestMatch("Wolf service", supportedServices));
		Assert.assertNull("Incorrect match not found !", provider.findClosestMatch("abc service", supportedServices));
		Assert.assertNull("incorrect match not found !",provider.findClosestMatch("service", supportedServices));
		
		
	}
	
}
