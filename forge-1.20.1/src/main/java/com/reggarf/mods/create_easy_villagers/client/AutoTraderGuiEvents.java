package com.reggarf.mods.create_easy_villagers.client;

import com.reggarf.mods.create_easy_villagers.client.gui.AutoTraderCycleTradesButton;
import de.maxhenkel.easyvillagers.ClientConfig;
import de.maxhenkel.easyvillagers.Main;
import de.maxhenkel.easyvillagers.gui.AutoTraderScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class AutoTraderGuiEvents {

    @SubscribeEvent
    public void onOpenScreen(ScreenEvent.Init.Post event) {
        if (!(event.getScreen() instanceof AutoTraderScreen screen)) {
            return;
        }
        if (Minecraft.getInstance().player == null) {
            return;
        }
        if (Main.SERVER_CONFIG.getConfigSpec().isLoaded() && !Main.SERVER_CONFIG.tradeCycling.get()) {
            return;
        }
        if (Main.CLIENT_CONFIG.cycleTradesButtonLocation.get().equals(ClientConfig.CycleTradesButtonLocation.NONE)) {
            return;
        }

        int x = screen.getGuiLeft() + 142;
        int y = screen.getGuiTop() + 60;

        event.addListener(new AutoTraderCycleTradesButton(x, y, screen));
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        if (Minecraft.getInstance().player == null) {
            return;
        }
        if (event.getKey() != Main.CYCLE_TRADES_KEY.getKey().getValue() || event.getAction() == 0) {
            return;
        }
        if (Main.SERVER_CONFIG.getConfigSpec().isLoaded() && !Main.SERVER_CONFIG.tradeCycling.get()) {
            return;
        }
        if (!(Minecraft.getInstance().screen instanceof AutoTraderScreen screen)) {
            return;
        }
        if (!AutoTraderCycleTradesButton.canCycle(screen.getMenu())) {
            return;
        }

        AutoTraderCycleTradesButton.cycle();
    }
}
