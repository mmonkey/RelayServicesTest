package com.gmail.mmonkey.RelayServicesTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.args.GenericArguments;
import org.spongepowered.api.util.command.spec.CommandSpec;

import com.google.common.base.Optional;

@Plugin(id = RelayServiceTest.ID, name = RelayServiceTest.NAME, version = RelayServiceTest.VERSION)
public class RelayServiceTest {
	
	public static final String ID = "RelayServiceTest";
	public static final String NAME = "RelayServiceTest";
	public static final String VERSION = "0.0.1";
	
	private Game game;
	private Optional<PluginContainer> pluginContainer;
	private static Logger logger;
	
	public Game getGame() {
		return this.game;
	}
	
	public Optional<PluginContainer> getPluginContainer() {
		return this.pluginContainer;
	}
	
	public static Logger getLogger() {
		return logger;
	}
	
	@Subscribe
	public void onPreInit(PreInitializationEvent event) {
		
		this.game = event.getGame();
		this.pluginContainer = game.getPluginManager().getPlugin(RelayServiceTest.NAME);
		RelayServiceTest.logger = game.getPluginManager().getLogger(pluginContainer.get());
		
		getLogger().info(String.format("Starting up %s v%s.", RelayServiceTest.NAME, RelayServiceTest.VERSION));
		
	}
	
	@Subscribe
	public void init(InitializationEvent event) {
		
		HashMap<List<String>, CommandSpec> subcommands = new HashMap<List<String>, CommandSpec>();
	
		/**
		 * /test relay
		 */
		subcommands.put(Arrays.asList("relay"), CommandSpec.builder()
			.setDescription(Texts.of("Test RelayService"))
			.setExtendedDescription(Texts.of("You must have an activated contact to use this."))
			.setExecutor(new TestRelaySubcommand(this))
			.setArguments(
				GenericArguments.flags()
					.valueFlag(GenericArguments.string(Texts.of("player")), "p")
					.buildWith(GenericArguments.remainingJoinedStrings(Texts.of("message"))))
			.build());
		
		/**
		 * /test
		 */
		CommandSpec testCommand = CommandSpec.builder()
			.setDescription(Texts.of("Test Relay Services"))
			.setExtendedDescription(Texts.of("Test RelayService and TemplatingService"))
			.setExecutor(new TestCommand(this))
			.setChildren(subcommands)
			.build();
		
		this.game.getCommandDispatcher().register(this, testCommand, "test");
		
	}
	
}
