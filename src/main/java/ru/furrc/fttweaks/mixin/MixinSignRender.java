package ru.furrc.fttweaks.mixin;

import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.furrc.fttweaks.config.ModConfig;

@Mixin(SignBlockEntityRenderer.class)
public abstract class MixinSignRender implements BlockEntityRenderer<SignBlockEntity> {

    @Inject(at = @At("HEAD"), method = "renderSign", cancellable = true)
    private void doNotRenderSign(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, WoodType woodType, Model model, CallbackInfo ci) {
        ModConfig config = ModConfig.get();
        if (config.enableMod && !config.showSign) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "renderText", cancellable = true)
    private void doNotRenderSign(BlockPos pos, SignText signText, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int lineHeight, int lineWidth, boolean front, CallbackInfo ci) {
        ModConfig config = ModConfig.get();
        if (config.enableMod && !config.showSignText) {
            ci.cancel();
        }
    }

}
