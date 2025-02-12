package uk.co.anttheantster.antsangelring.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import uk.co.anttheantster.antsangelring.AntsAngelRing;
import uk.co.anttheantster.antsangelring.item.ModItems;

@EventBusSubscriber(modid = AntsAngelRing.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class AngelRingEvent {

    @SubscribeEvent
    public static void onAngelRingUse(PlayerTickEvent.Pre event){
        if (ModList.get().isLoaded("accessories")) return;

        Player player = event.getEntity();
        if (player.isCreative() || player.isSpectator()) return;

        boolean hasAngelRing = player.getInventory().contains(ModItems.ANGEL_RING.get().getDefaultInstance());

        if (hasAngelRing){
            startFlight(player);
        } else {
            stopFlight(player);
        }
        player.onUpdateAbilities();
    }

    private static void startFlight(Player player){
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();
    }
    private static void stopFlight(Player player){
        player.getAbilities().mayfly = false;
        player.getAbilities().flying = false;
        player.onUpdateAbilities();
    }
}
