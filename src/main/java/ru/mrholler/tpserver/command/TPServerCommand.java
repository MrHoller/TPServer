package ru.mrholler.tpserver.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.google.common.net.InetAddresses;
import org.apache.commons.validator.routines.DomainValidator;

import java.net.InetSocketAddress;

public class TPServerCommand extends Command {
    public TPServerCommand() {
        super("tpserver");
        setPermission("mrholler.tpserver");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.isPlayer()) {
            sender.sendMessage("Эта команда может быть использована только в игре.");
            return false;
        }

        if (args.length != 2) {
            sender.sendMessage("Пожалуйста, укажите IP-адрес и порт сервера.");
            return false;
        }

        String address = args[0];
        int port = Integer.parseInt(args[1]);

        if (!InetAddresses.isInetAddress(address) || !DomainValidator.getInstance().isValid(address)) {
            sender.sendMessage("IP-адрес или домен введен не верно.");
            return false;
        }

        if (port < 1024 || port > 65535) {
            sender.sendMessage("Порт введен не верно.");
            return false;
        }


        Server.getInstance().getLogger().info("Перенос игрока " + sender.getName() + " на сервер " + address + ":" + port);
        ((Player) sender).transfer(new InetSocketAddress(address, port));
        return true;
    }
}
