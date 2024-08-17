package tfar.howdoyoufeel;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.client.resources.model.AtlasSet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import tfar.howdoyoufeel.network.S2CUpdateEffectsPacket;

import java.util.Set;

public class ClientPacketHandler {

    public static void handleUpdateEffectsPacket(S2CUpdateEffectsPacket s2CUpdateEffectsPacket) {
        Entity entity = Minecraft.getInstance().level.getEntity(s2CUpdateEffectsPacket.entityId);
        if (entity instanceof LivingEntity living) {
            living.getActiveEffectsMap().clear();
            for (MobEffect mobEffect : s2CUpdateEffectsPacket.activeEffects) {
                living.addEffect(new MobEffectInstance(mobEffect, MobEffectInstance.INFINITE_DURATION));
            }
        }
    }

    public static void onRender(LivingEntity entity, PoseStack poseStack, MultiBufferSource multiBufferSource) {
        Set<MobEffect> effectSet = entity.getActiveEffectsMap().keySet();
        if (!effectSet.isEmpty()) {
            renderEffect(entity,effectSet.iterator().next(), poseStack, multiBufferSource);
        }
    }

    private static void renderEffect(LivingEntity entity,MobEffect icon, PoseStack poseStack,
                                     MultiBufferSource buffers) {
        poseStack.pushPose();
        poseStack.translate(0,entity.getNameTagOffsetY() + .25, 0);

        Quaternionf quaternionf = Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation();

        poseStack.mulPose(quaternionf);
        float scale = .5f;
        poseStack.scale(scale,scale,scale);
        MobEffectTextureManager mobeffecttexturemanager = Minecraft.getInstance().getMobEffectTextures();

        TextureAtlasSprite textureatlassprite = mobeffecttexturemanager.get(icon);

        Matrix4f matrix4f = poseStack.last().pose();
        RenderSystem.setShaderTexture(0, textureatlassprite.atlasLocation());
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        float u0 = textureatlassprite.getU0();
        float u1 = textureatlassprite.getU1();
        float v0 = textureatlassprite.getV0();
        float v1 = textureatlassprite.getV1();

        VertexConsumer vertexconsumer = buffers.getBuffer(CustomRenderType.TYPE);

        float b = -.5f;

        vertexconsumer.vertex(matrix4f, 0.5f,0.5f+b, 0).uv(u0,v0).endVertex();
        vertexconsumer.vertex(matrix4f, 0.5f, -0.5f+b, 0).uv(u0,v1).endVertex();
        vertexconsumer.vertex(matrix4f, -0.5f, -0.5f+b, 0).uv(u1,v1).endVertex();
        vertexconsumer.vertex(matrix4f, -0.5f, 0.5f+b, 0).uv(u1,v0).endVertex();

        poseStack.popPose();
    }

    //   /**
    //    * Blits a portion of the specified texture atlas sprite onto the screen at the given coordinates.
    //    * @param pX the x-coordinate of the blit position.
    //    * @param pY the y-coordinate of the blit position.
    //    * @param pBlitOffset the z-level offset for rendering order.
    //    * @param pWidth the width of the blitted portion.
    //    * @param pHeight the height of the blitted portion.
    //    * @param pSprite the texture atlas sprite to blit.
    //    */
    //   public void blit(int pX, int pY, int pBlitOffset, int pWidth, int pHeight, TextureAtlasSprite pSprite) {
    //      this.innerBlit(pSprite.atlasLocation(), pX, pX + pWidth, pY, pY + pHeight, pBlitOffset, pSprite.getU0(), pSprite.getU1(), pSprite.getV0(), pSprite.getV1());
    //   }

}
