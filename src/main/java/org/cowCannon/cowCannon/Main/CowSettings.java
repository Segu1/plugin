package org.cowCannon.cowCannon.Main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.cowCannon.cowCannon.CowCannon;

public class CowSettings {

    private static CowSettings instance;
    private File file;
    private YamlConfiguration config;

    private EntityType explodingType;

    private CowSettings(){}

    public void load(){
        file = new File(CowCannon.getInstance().getDataFolder(), "settings.yml");

        if(!file.exists()){
            CowCannon.getInstance().saveResource("settings.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
            //codigo
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        explodingType = EntityType.valueOf(config.getString("Explosion.entity-type"));

    }

    public void save(){
        try{
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String path, Object value){
        config.set(path, value);
        save();
    }

    public static CowSettings getInstance() {
        if(instance == null){
            instance = new CowSettings();
            return  instance;
        }
        return instance;
    }

    public EntityType getExplodingType() {
        return explodingType;
    }

    public void setExplodingType(EntityType type) {
        this.explodingType = type;
    }
}
