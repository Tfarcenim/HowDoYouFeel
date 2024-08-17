package tfar.howdoyoufeel.mixin;

import net.minecraft.world.entity.LivingEntity;
import tfar.howdoyoufeel.HowDoYouFeel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "tickEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;updateInvisibilityStatus()V"))
    private void onEffectsChanged(CallbackInfo ci) {
        HowDoYouFeel.onEffectsChanged((LivingEntity)(Object) this);
    }
}