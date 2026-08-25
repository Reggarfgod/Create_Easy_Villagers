package com.reggarf.mods.create_easy_villagers.mixin.contant.autotrader.gui;

import de.maxhenkel.easyvillagers.Main;
import de.maxhenkel.easyvillagers.blocks.tileentity.AutoTraderTileentity;
import de.maxhenkel.easyvillagers.entity.EasyVillagerEntity;
import de.maxhenkel.easyvillagers.events.GuiEvents;
import de.maxhenkel.easyvillagers.gui.AutoTraderContainer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiEvents.class, remap = false)
public class GuiEventsMixin {

    @Inject(method = "onCycleTrades", at = @At("HEAD"), cancellable = true)
    private static void onCycleTradesAutoTrader(ServerPlayer player, CallbackInfo ci) {
        if (player.containerMenu instanceof AutoTraderContainer container) {
            if (Main.SERVER_CONFIG.tradeCycling != null && !Main.SERVER_CONFIG.tradeCycling.get()) {
                return;
            }
            AutoTraderTileentity trader = container.getTrader();
            if (trader == null) {
                return;
            }
            EasyVillagerEntity villager = trader.getVillagerEntity();
            if (villager == null) {
                return;
            }
            if (villager.getVillagerXp() > 0 || villager.getVillagerData().getLevel() > 1) {
                return;
            }
            if (!trader.hasWorkstation()) {
                return;
            }
            VillagerProfession profession = villager.getVillagerData().getProfession();
            if (profession == VillagerProfession.NONE || profession == VillagerProfession.NITWIT) {
                return;
            }
            villager.setOffers(null);
            EasyVillagerEntity.recalculateOffers(villager);
            trader.setTradeIndex(0);
            trader.setChanged();
            ci.cancel();
        }
    }
}
