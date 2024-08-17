package tfar.howdoyoufeel;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import tfar.howdoyoufeel.network.S2CUpdateEffectsPacket;

public class ClientPacketHandler {

    public static void handleUpdateEffectsPacket(S2CUpdateEffectsPacket s2CUpdateEffectsPacket) {
        Entity entity = Minecraft.getInstance().level.getEntity(s2CUpdateEffectsPacket.entityId);
        if (entity instanceof LivingEntity living) {
            living.getActiveEffectsMap().clear();
            for (MobEffect mobEffect : s2CUpdateEffectsPacket.activeEffects) {
                living.addEffect(new MobEffectInstance(mobEffect,MobEffectInstance.INFINITE_DURATION));
            }
        }
    }
}
