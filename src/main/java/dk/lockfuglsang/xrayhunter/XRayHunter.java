package dk.lockfuglsang.xrayhunter;

import dk.lockfuglsang.xrayhunter.command.MainCommand;
import dk.lockfuglsang.xrayhunter.coreprotect.CoreProtectHandler;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Bukkit Plugin for hunting X-Rayers using the CoreProtect API
 */
public class XRayHunter extends JavaPlugin {
    private static final Logger log = Logger.getLogger(XRayHunter.class.getName());

    private CoreProtectAPI api;

    public void onEnable() {
        api = null;
        CoreProtectAPI coreProtectAPI = getCoreProtect();
        if (coreProtectAPI == null) {
            log.info("No valid CoreProtect plugin was found!");
        }
        this.api = coreProtectAPI;
        getCommand("xhunt").setExecutor(new MainCommand(this));
    }

    // package protected
    private CoreProtectAPI getCoreProtect() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("CoreProtect");
        if (plugin instanceof CoreProtect && plugin.isEnabled()) {
            CoreProtect coreProtect = (CoreProtect) plugin;
            CoreProtectAPI api = coreProtect.getAPI();
            if (api != null && api.APIVersion() >= 2 && CoreProtectHandler.getAdaptor() != null) {
                return api;
            }
        }
        return null;
    }

    public CoreProtectAPI getAPI() {
        if (api == null) {
            CoreProtectAPI coreProtectAPI = getCoreProtect();
            if (coreProtectAPI != null) {
                api = coreProtectAPI;
            }
        }
        return api;
    }
}
