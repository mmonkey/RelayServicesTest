package com.gmail.mmonkey.RelayServicesTest;

import java.util.ArrayList;
import java.util.Collection;

import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;

import com.github.mmonkey.Relay.MessageRelayResult;
import com.github.mmonkey.Relay.RelayService;

public class TestRelaySubcommand extends TestCommand {

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		
		String message = (args.hasAny("message")) ? ((String) args.getOne("message").get()) : "";
		
		@SuppressWarnings("unchecked")
		Collection<String> players = (Collection<String>) ((args.hasAny("player")) ? args.getAll("player") : new ArrayList<String>());
		
		@SuppressWarnings("unchecked")
		RelayService<String> service = (RelayService<String>) plugin.getGame().getServiceManager().provide(RelayService.class).get();
		
		if (players.isEmpty()) {
			src.sendMessage(Texts.of(TextColors.RED, "You must include players to send a message.").builder().build());
			return CommandResult.success();
		}
		
		MessageRelayResult result = service.sendMessage(players, message);
		src.sendMessage(Texts.of(result.getResult()));
		return CommandResult.success();
		
	}
	
	public TestRelaySubcommand(RelayServiceTest plugin) {
		super(plugin);
	}

}
