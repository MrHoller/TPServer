package ru.mrholler.tpserver;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;

import java.net.InetSocketAddress;
import java.util.Objects;

public class TPServer extends PluginBase {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (Objects.equals(label, "tpserver")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Эта команда может быть использована только в игре.");
                return false;
            }
            if (args.length != 2) {
                sender.sendMessage("Пожалуйста, укажите IP-адрес и порт сервера.");
                return false;
            }

            String address = args[0];
            int port = Integer.parseInt(args[1]);

            getLogger().info("Перенос игрока " + sender.getName() + " на сервер " + address + ":" + port);
            ((Player) sender).transfer(new InetSocketAddress(address, port));
        }
        return true;
    }
}
