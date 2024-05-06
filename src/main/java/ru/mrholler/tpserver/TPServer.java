package ru.mrholler.tpserver;

import cn.nukkit.Server;
import cn.nukkit.permission.Permission;
import cn.nukkit.plugin.PluginBase;
import ru.mrholler.tpserver.command.TPServerCommand;

public class TPServer extends PluginBase {

    @Override
    public void onEnable() {
        Server.getInstance().getPluginManager().addPermission(new Permission("mrholler.tpserver", "", Permission.DEFAULT_TRUE));
        Server.getInstance().getCommandMap().register("mrholler", new TPServerCommand());
    }
}
