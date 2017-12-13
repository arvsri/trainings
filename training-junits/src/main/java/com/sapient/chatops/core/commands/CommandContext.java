package com.sapient.chatops.core.commands;

import java.util.Collection;

public class CommandContext {
	
	private String intentName;

	private String commandName;

	private Collection<String> supportedEnvironments;

	private Collection<String> supportedServices;

	private Collection<String> supportedBranches;

	private Collection<String> supportedTransitions;


	private Collection<String> supportedIssueTypes;

	private Collection<String> supportedProjects;
	public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}

	public Collection<String> getSupportedEnvironments() {
		return supportedEnvironments;
	}

	public void setSupportedEnvironments(Collection<String> supportedEnvironments) {
		this.supportedEnvironments = supportedEnvironments;
	}

	public Collection<String> getSupportedServices() {
		return supportedServices;
	}

	public void setSupportedServices(Collection<String> supportedServices) {
		this.supportedServices = supportedServices;
	}

	public Collection<String> getSupportedBranches() {
		return supportedBranches;
	}

	public void setSupportedBranches(Collection<String> supportedBranches) {
		this.supportedBranches = supportedBranches;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public Collection<String> getSupportedTransitions() {
		return supportedTransitions;
	}

	public void setSupportedTransitions(Collection<String> supportedTransitions) {
		this.supportedTransitions = supportedTransitions;
	}

	public Collection<String> getSupportedIssueTypes() {
		return supportedIssueTypes;
	}

	public void setSupportedIssueTypes(Collection<String> supportedIssueTypes) {
		this.supportedIssueTypes = supportedIssueTypes;
	}

	public Collection<String> getSupportedProjects() {
		return supportedProjects;
	}

	public void setSupportedProjects(Collection<String> supportedProjects) {
		this.supportedProjects = supportedProjects;
	}
}
