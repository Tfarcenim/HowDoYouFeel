package tfar.howdoyoufeel;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(HowDoYouFeel.MOD_ID)
public class HowDoYouFeelForge {
    
    public HowDoYouFeelForge() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        MinecraftForge.EVENT_BUS.addListener(this::startTracking);

        if (FMLEnvironment.dist.isClient()) {
            bus.addListener(this::clientSetup);
        }

        // Use Forge to bootstrap the Common mod.
        HowDoYouFeel.init();
    }

    private void clientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(Client::render);
    }

    private void startTracking(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof LivingEntity living) {
            HowDoYouFeel.onEffectsChanged(living);
        }
    }

    public static class Client {
        static void render(RenderLivingEvent.Post event) {
            ClientPacketHandler.onRender(event.getEntity(),event.getPoseStack(),event.getMultiBufferSource());
        }
    }

}