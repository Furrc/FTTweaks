package ru.furrc.fttweaks.config;


import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import static ru.furrc.fttweaks.FTTweaksClient.MOD_ID;

@Config(name = MOD_ID)
public class ModConfig implements ConfigData {

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    public static void save() {
        AutoConfig.getConfigHolder(ModConfig.class).save();
    }

    public static void load() {
        AutoConfig.getConfigHolder(ModConfig.class).load();
    }

    public String configVersion = "v1";

    // main settings
    public boolean enableMod = true;

    // frame settings
    public boolean showItemFrameName = true;
    public boolean showFrame = true;

    // sign settings
    public boolean showSign = true;
    public boolean showSignText = true;


}
