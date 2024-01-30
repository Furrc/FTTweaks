package ru.furrc.fttweaks.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.furrc.fttweaks.config.ModConfig;

@Mixin(ItemFrameEntityRenderer.class)
public abstract class MixinRenderFrame<T extends ItemFrameEntity> extends EntityRenderer<T> {

    protected MixinRenderFrame(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", cancellable = true)
    private void doNotRender(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        ModConfig config = ModConfig.get();
        if (config.enableMod && !config.showFrame) {
            ci.cancel();
        }
    }


    @Inject(at = @At("HEAD"), method = "renderLabelIfPresent(Lnet/minecraft/entity/decoration/ItemFrameEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", cancellable = true)
    private void doNotRenderLabel(T itemFrameEntity, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        ModConfig config = ModConfig.get();
        if (config.enableMod && !config.showItemFrameName) {
            ci.cancel();
        }
    }

}
