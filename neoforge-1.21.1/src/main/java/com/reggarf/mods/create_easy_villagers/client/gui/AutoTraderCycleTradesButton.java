package com.reggarf.mods.create_easy_villagers.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.reggarf.mods.create_easy_villagers.api.IAutoTraderContainer;
import de.maxhenkel.easyvillagers.Main;
import de.maxhenkel.easyvillagers.gui.AutoTraderContainer;
import de.maxhenkel.easyvillagers.gui.AutoTraderScreen;
import de.maxhenkel.easyvillagers.net.MessageCycleTrades;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Collections;

public class AutoTraderCycleTradesButton extends AbstractButton {

    private static final ResourceLocation ARROW_BUTTON = ResourceLocation.fromNamespaceAndPath("easy_villagers", "textures/gui/container/arrow_button.png");
    public static final int WIDTH = 18;
    public static final int HEIGHT = 14;

    private final AutoTraderScreen screen;

    public AutoTraderCycleTradesButton(int x, int y, AutoTraderScreen screen) {
        super(x, y, WIDTH, HEIGHT, Component.empty());
        this.screen = screen;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.visible = canCycle(this.screen.getMenu());
        if (!this.visible) {
            return;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        if (this.isHoveredOrFocused()) {
            guiGraphics.blit(ARROW_BUTTON, getX(), getY(), 0.0F, 14.0F, WIDTH, HEIGHT, 32, 32);
            guiGraphics.renderTooltip(this.screen.getMinecraft().font, Collections.singletonList(Component.translatable("tooltip.easy_villagers.cycle_trades").getVisualOrderText()), mouseX, mouseY);
        } else {
            guiGraphics.blit(ARROW_BUTTON, getX(), getY(), 0.0F, 0.0F, WIDTH, HEIGHT, 32, 32);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

    @Override
    public void onPress() {
        cycle();
    }

    public static void cycle() {
        PacketDistributor.sendToServer(new MessageCycleTrades());
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    public static boolean canCycle(AutoTraderContainer menu) {
        if (Main.SERVER_CONFIG.tradeCycling != null && !Main.SERVER_CONFIG.tradeCycling.get()) {
            return false;
        }
        if (menu instanceof IAutoTraderContainer autoTraderContainer) {
            return autoTraderContainer.create_easy_villagers$canCycle();
        }
        return false;
    }
}
