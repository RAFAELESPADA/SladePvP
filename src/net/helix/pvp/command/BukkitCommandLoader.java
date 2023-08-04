package net.helix.pvp.command;

import org.bukkit.Bukkit;


public class BukkitCommandLoader {

	private BukkitCommandFramework framework;

	public BukkitCommandLoader(BukkitCommandFramework framework) {
		this.framework = framework;
	}

	public int loadCommandsFromPackage(String packageName) {
		int i = 0;
		for (Class<?> commandClass : ClassGetter.getClassesForPackage(framework.getPlugin().getClass(), packageName)) {
			if (CommandClass.class.isAssignableFrom(commandClass)) {
				try {
					CommandClass commands = (CommandClass) commandClass.newInstance();
					framework.registerCommands(commands);
				} catch (Exception e) {
					e.printStackTrace();
					Bukkit.getLogger().warning("Erro ao carregar comandos da classe " + commandClass.getSimpleName() + "!");
				}
				i++;
			}
		}
		return i;
	}

}