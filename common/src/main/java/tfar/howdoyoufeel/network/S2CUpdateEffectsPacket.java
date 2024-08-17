package tfar.howdoyoufeel.network;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import tfar.howdoyoufeel.ClientPacketHandler;

import java.util.HashSet;
import java.util.Set;

public class S2CUpdateEffectsPacket implements S2CModPacket {

    public final Set<MobEffect> activeEffects;
    public final int entityId;

    public S2CUpdateEffectsPacket(Set<MobEffect> activeEffects, LivingEntity entity) {
        this.activeEffects = activeEffects;
        entityId = entity.getId();
    }

    public S2CUpdateEffectsPacket(FriendlyByteBuf buf) {
        int size = buf.readInt();
        activeEffects = new HashSet<>(size);
        for (int i = 0; i < size;i++) {
            activeEffects.add(buf.readById(BuiltInRegistries.MOB_EFFECT));
        }
        entityId = buf.readInt();
    }

    @Override
    public void handleClient() {
        ClientPacketHandler.handleUpdateEffectsPacket(this);
    }

    @Override
    public void write(FriendlyByteBuf to) {
        to.writeInt(activeEffects.size());
        for (MobEffect effect : activeEffects) {
            to.writeId(BuiltInRegistries.MOB_EFFECT,effect);
        }
        to.writeInt(entityId);
    }
}
