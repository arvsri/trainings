package com.sapient.chatops.core.commands;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.sapient.chatops.core.ServiceProviders;
import com.sapient.chatops.core.peristence.ICrudDAO;
import com.sapient.chatops.core.service.CommandContextService;

public class CommandContextServiceProvider implements CommandContextService {
	
	private static final double LEVENSHTEIN_THRESHOLD = 0.3;

	public CommandContextServiceProvider(){
		// Verify that setup with static data is already in place
		ICrudDAO<CommandContext> dao = ServiceProviders.getInstance().getPeristenceService().getDAO(CommandContext.class);
		List<CommandContext> contexts = dao.retrieveAll(CommandContext.class, 1);
		
		if(CollectionUtils.isEmpty(contexts)){
			// Logic to load all the contexts from configuration file
		}
	}
	
	public List<CommandContext> getAllContexts(){
		ICrudDAO<CommandContext> dao = ServiceProviders.getInstance().getPeristenceService().getDAO(CommandContext.class);
		return dao.retrieveAll(CommandContext.class, 100);
	}
	
	
	/**
	 * Find the closest match by following algorithm  
	 * 1. Convert the value and supported values to upper case and trim any spaces
	 * 2. Check if unique supported value exists that contains value
	 * 3. Check if unique supported value exists that is contained in value
	 * 4. Check the Levenshetien distance with each supported values - find the match with minimum value and if the minimum value is with in threshold
	 * 
	 * @param value
	 * @param supportedValues
	 * @return
	 */
	public String findClosestMatch(String value, List<String> supportedValues){
		if(StringUtils.isBlank(value) || CollectionUtils.isEmpty(supportedValues)){
			return null;
		}
		
		String val = value.toUpperCase().trim();
		List<String> supportedVal = supportedValues.stream().map((v) -> { return v.toUpperCase().trim();}).collect(Collectors.toList());
		
		// always match with any thing
		if(supportedVal.size() == 1 && supportedVal.get(0).equals("ANY")){
			return value;
		}
		
		List<String> matchedVal = supportedVal.stream().filter((v) -> { return StringUtils.contains(v, val);}).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(matchedVal) && matchedVal.size() == 1){
			return supportedValues.get(supportedVal.indexOf(matchedVal.get(0)));
		}
		
		matchedVal = supportedVal.stream().filter((v) -> { return StringUtils.contains(val, v);}).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(matchedVal) && matchedVal.size() == 1){
			return supportedValues.get(supportedVal.indexOf(matchedVal.get(0)));
		}
		
		List<ValueDistacePair<String>> valDists = 
				supportedVal.stream().map((v) -> { return new ValueDistacePair<String>(v,StringUtils.getLevenshteinDistance(val, v));}).collect(Collectors.toList());
		Collections.sort(valDists);
		
		ValueDistacePair<String> lowestDist = valDists.remove(0);
		if(lowestDist.getDistance() <= lowestDist.getVal().length() * LEVENSHTEIN_THRESHOLD){
			
			ValueDistacePair<String> secondLowest = valDists.size() > 0 ? valDists.remove(0) : null;
			if(secondLowest == null || secondLowest.getDistance() > secondLowest.getVal().length() * LEVENSHTEIN_THRESHOLD){
				return supportedValues.get(supportedVal.indexOf(lowestDist.getVal()));
			}
		}
		return null;
		
		
	}
	
	public static class ValueDistacePair<T> implements Comparable<ValueDistacePair<T>> {
		private T val;
		
		private Integer distance;

		public ValueDistacePair(T val, int distance) {
			this.val = val;
			this.distance = distance;
		}

		public T getVal() {
			return val;
		}

		public Integer getDistance() {
			return distance;
		}

		@Override
		public int compareTo(ValueDistacePair<T> arg0) {
			return this.distance.compareTo(arg0.getDistance());
		}

	}

}
