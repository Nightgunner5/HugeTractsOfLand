package me.kingrat.plugins.hugetractsofland;

import java.io.File;
import java.util.List;
import org.bukkit.World.Environment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class HugeTractsOfLand extends JavaPlugin {
    
    public Configuration config;
    final HugeTractsOfLand plugin = this;

    @Override
    public File getDataFolder() {
        return super.getDataFolder();
    }

    
    private static final String ConfigHeader = 
            
        "# HugeTractsOfLand Configuration!\r\n" +
        "#\r\n" +
        "# This file manages all worlds. You can define a new world easily.\r\n" +
        "# To make a world generate, make sure you set 'ready' to true.\r\n";
    
    public void onDisable() {
        
        System.out.println(this + " is now disabled!");
        
    }

    public void onEnable() {
        
        if(!getDataFolder().exists()){makeConfig();}else{loadConfig();}
        
        System.out.println(this + " is now enabled!"); 
        List<String> worldconfig = config.getStringList("worlds", null);
        
        for (String world : worldconfig) {
                
                //if(getServer().getWorld(worldname) == null){
                    //getServer().createWorld(worldname, Environment.NORMAL);

           // }        
        }   
    } 
    
    public void makeConfig() {
        
        try {
            
            config = getConfiguration();
            config.setHeader(ConfigHeader);
            config.setProperty("worlds.world1", "world1");
            config.setProperty("worlds.world1.worldname", "flatworld");
            config.setProperty("worlds.world1.worldtype", "NORMAL");
            config.setProperty("worlds.world1.material1", "GRASS");
            config.setProperty("worlds.world1.material2", "DIRT");
            config.setProperty("worlds.world1.material3", "STONE");
            config.setProperty("worlds.world1.ready", false);
            config.save();
            
    } catch (IllegalArgumentException ex) {
                
        System.out.println(this + " failed to initiate config. Check for errors!");
        
        }      
    }
    
    public void loadConfig() {
        
    }
}


