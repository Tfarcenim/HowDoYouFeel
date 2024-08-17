package tfar.howdoyoufeel;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;


import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.resources.ResourceLocation;


public class CustomRenderType extends RenderType {
    public CustomRenderType(String $$0, VertexFormat $$1, VertexFormat.Mode $$2, int $$3, boolean $$4, boolean $$5, Runnable $$6, Runnable $$7) {
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6, $$7);
    }

    //https://github.com/UpcraftLP/Orderly/blob/master/src/main/resources/assets/orderly/textures/ui/default_health_bar.png
    public static final ResourceLocation MOB_EFFECT_ATLAS = new ResourceLocation("textures/atlas/mob_effects.png");
    public static final RenderType TYPE = getRenderType();


    private static RenderType getRenderType() {
        RenderType.CompositeState renderTypeState = RenderType.CompositeState.builder()
                .setShaderState(RenderStateShard.POSITION_TEX_SHADER)
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setTextureState(new RenderStateShard.TextureStateShard(MOB_EFFECT_ATLAS, true, false))
                .setDepthTestState(RenderStateShard.LEQUAL_DEPTH_TEST)
                .createCompositeState(false);
        return RenderType.create("mob_effects", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, true, true, renderTypeState);
    }
}
