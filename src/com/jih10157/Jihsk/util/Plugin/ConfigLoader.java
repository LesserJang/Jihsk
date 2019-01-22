package com.jih10157.Jihsk.util.Plugin;

import com.jih10157.Jihsk.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigLoader {

    private static FileConfiguration config;
    public static Boolean autoUpdate;
    public static String emailServerHost;
    public static int emailPort;
    public static String emailUsername;
    public static String emailPassword;
    public static Boolean emailPasswordRequired;
    public static Boolean emailTLSRequired;
    private static Main main;
    public ConfigLoader(Main main) {
        ConfigLoader.main = main;
    }

    public void Load() {
        if(!(new File(main.getDataFolder() + File.separator + "config.yml")).exists()) {
            main.saveDefaultConfig();
        }
        main.reloadConfig();
        config = main.getConfig();
        autoUpdate = config.getBoolean("AutoUpdate");
        emailServerHost = config.getString("Email.EmailServer");
        emailPort = config.getInt("Email.EmailPort");
        emailPasswordRequired = config.getBoolean("Email.EmailAuthRequired");
        emailTLSRequired = config.getBoolean("Email.EmailTLSRequired");
        emailUsername = config.getString("Email.EmailUsername");
        emailPassword = config.getString("Email.EmailPassword");
    }
}
