package ru.furrc.fttweaks.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.furrc.fttweaks.config.ModMenuIntegration;

public class KeyBindClient {

    private static KeyBinding openSettings;

    public static void Init() {
        openSettings = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Open settings",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_MINUS,
                "FurTown Tweaks"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openSettings.wasPressed()) {
                MinecraftClient minecraftClient = MinecraftClient.getInstance();

                if (minecraftClient != null) {
                    minecraftClient.setScreen(ModMenuIntegration.createConfigScreen(minecraftClient.currentScreen));
                }
            }
        });
    }

}
