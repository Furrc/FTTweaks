package ru.furrc.fttweaks.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModMenuIntegration::createConfigScreen;
    }

    private static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("FurTown Tweaks"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ModConfig config = ModConfig.get();

        builder.setDefaultBackgroundTexture(new Identifier("minecraft:textures/block/honeycomb_block.png"));

        builder.getOrCreateCategory(Text.translatable("fttweaks.config.generalCategory"))
                .addEntry(entryBuilder.startBooleanToggle(
                                Text.translatable("fttweaks.config.enableMod"),
                                config.enableMod)
                        .setDefaultValue(true)
                        .setSaveConsumer(value -> config.enableMod = value)
                        .build());

        builder.getOrCreateCategory(Text.translatable("fttweaks.config.entity"))
                .addEntry(entryBuilder.startBooleanToggle(
                                Text.translatable("fttweaks.config.showItemFrameName"),
                                config.showItemFrameNameTag)
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> config.showItemFrameNameTag = value)
                        .build());

        builder.transparentBackground();

        return builder.setSavingRunnable(ModConfig::save).build();
    }

}
