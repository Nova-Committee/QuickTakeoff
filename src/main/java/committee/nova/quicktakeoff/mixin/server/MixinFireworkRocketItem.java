package committee.nova.quicktakeoff.mixin.server;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkRocketItem.class)
public abstract class MixinFireworkRocketItem {
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isFallFlying()Z"))
    private boolean redirect$use(Player player) {
        if (!player.isFallFlying()) player.tryToStartFallFlying();
        return player.isFallFlying();
    }
}
