package fr.themsou.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.themsou.main.main;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.themsou.BedWars.BedWarsInv;
import fr.themsou.discord.Link;
import fr.themsou.inv.HubInv;
import fr.themsou.methodes.login;
import fr.themsou.rp.inv.RolePlayMainInv;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GeneralCmd implements CommandExecutor, TabCompleter {

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg1, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
//
//	DISCORD
//
			if (cmd.getName().equalsIgnoreCase("discord")) {
				if (args.length >= 1) {
					new Link().linkPlayer(p.getName(), String.join(" ", args));
					p.sendMessage("§3Un message de confirmation vous a été envoyé sur discord");
				} else p.sendMessage("§c/discord <pseudo>");
			}
//
//	MENU GUI
//
			else if (cmd.getName().equalsIgnoreCase("?") || cmd.getName().equalsIgnoreCase("help")) {

				if (p.getWorld() == Bukkit.getWorld("world") || p.getWorld() == Bukkit.getWorld("world_nether") || p.getWorld() == Bukkit.getWorld("world_the_end")) {
					new RolePlayMainInv().openInv(p);
				} else if (p.getWorld() == Bukkit.getWorld("BedWars") || p.getWorld() == Bukkit.getWorld("BedWars-ressources")) {
					new BedWarsInv().openMainInv(p);
				} else {
					new HubInv().openInv(p);
				}

			}


//
//	LOGIN
//		
			else if (cmd.getName().equalsIgnoreCase("l") || cmd.getName().equalsIgnoreCase("login"))
				new login().loginPlayer(p, args);
			else if (cmd.getName().equalsIgnoreCase("reg") || cmd.getName().equalsIgnoreCase("register"))
				new login().RegisterPlayer(p, args);

		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg, String[] args){

		if(sender instanceof Player){

			if(cmd.getName().equalsIgnoreCase("discord")){

				if(args.length == 1){

					ArrayList<String> returns = new ArrayList<>();

					for(Member member : main.guild.getMembers()){
						if(member.getUser().getAsTag().toLowerCase().startsWith(String.join(" ", args))){
							returns.add(member.getUser().getAsTag());
						}
					}
					return returns;
				}

			}
		}

		return Arrays.asList("");
	}
	
	public static List<String> matchTab(List<String> tabs, String toMatch){

		if(toMatch.isEmpty()) return tabs;
		ArrayList<String> matches = new ArrayList<>();
		
		for(int i = 0; i < tabs.size(); i++){
			if(tabs.get(i).toLowerCase().startsWith(toMatch.toLowerCase())){
				matches.add(tabs.get(i));
			}
		}
		
		return matches;
		
	}


}
