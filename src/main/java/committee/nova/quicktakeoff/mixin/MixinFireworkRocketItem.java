package committee.nova.quicktakeoff.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkRocketItem.class)
public abstract class MixinFireworkRocketItem {
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isFallFlying()Z"))
    private boolean redirect$use(Player player) {
        if (!(player instanceof LocalPlayer l)) return player.isFallFlying();
        if (!player.isFallFlying() && player.tryToStartFallFlying())
            l.connection.send(new ServerboundPlayerCommandPacket(l, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
        return player.isFallFlying();
    }
}
