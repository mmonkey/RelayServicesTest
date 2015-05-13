package com.gmail.mmonkey.RelayServicesTest;

import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

public class TestCommand implements CommandExecutor {

	public RelayServiceTest plugin;
	
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		return CommandResult.empty();
	}
	
	public TestCommand(RelayServiceTest plugin) {
		this.plugin = plugin;
	}

}
