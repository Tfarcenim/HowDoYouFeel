package tfar.howdoyoufeel;

import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(HowDoYouFeel.MOD_ID)
public class HowDoYouFeelForge {
    
    public HowDoYouFeelForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        HowDoYouFeel.init();
    }

}