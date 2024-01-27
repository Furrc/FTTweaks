package ru.furrc.fttweaks.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.furrc.fttweaks.config.ModConfig;

@Mixin(EntityRenderer.class)
public class MixinRenderNameTag<T extends Entity> {

    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void doNotRender(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        ModConfig config = ModConfig.get();

        if (config.enableMod) {
            if (!config.showItemFrameNameTag && entity instanceof ItemFrameEntity) {
                ci.cancel();
            }
        }
    }

}
