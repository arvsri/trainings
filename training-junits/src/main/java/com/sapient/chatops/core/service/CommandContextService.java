package com.sapient.chatops.core.service;

import java.util.List;

import com.sapient.chatops.core.commands.CommandContext;

public interface CommandContextService {


    List<CommandContext> getAllContexts();


    String findClosestMatch(String value, List<String> supportedValues);

}
