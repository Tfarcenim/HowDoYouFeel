package tfar.howdoyoufeel.network;

import net.minecraft.resources.ResourceLocation;
import tfar.howdoyoufeel.HowDoYouFeel;
import tfar.howdoyoufeel.platform.Services;

import java.util.Locale;

public class PacketHandler {

    public static void registerPackets() {
        Services.PLATFORM.registerClientPacket(S2CUpdateEffectsPacket.class, S2CUpdateEffectsPacket::new);

    }

    public static ResourceLocation packet(Class<?> clazz) {
        return HowDoYouFeel.id(clazz.getName().toLowerCase(Locale.ROOT));
    }

}
