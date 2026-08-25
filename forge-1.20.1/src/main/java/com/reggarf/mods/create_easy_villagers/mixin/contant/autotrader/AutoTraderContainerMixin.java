package com.reggarf.mods.create_easy_villagers.mixin.contant.autotrader;

import com.reggarf.mods.create_easy_villagers.api.IAutoTraderContainer;
import de.maxhenkel.easyvillagers.blocks.tileentity.AutoTraderTileentity;
import de.maxhenkel.easyvillagers.entity.EasyVillagerEntity;
import de.maxhenkel.easyvillagers.gui.AutoTraderContainer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AutoTraderContainer.class, remap = false)
public abstract class AutoTraderContainerMixin extends AbstractContainerMenu implements IAutoTraderContainer {

    @Unique
    private DataSlot cev$canCycleSlot;

    protected AutoTraderContainerMixin(MenuType<?> menuType, int id) {
        super(menuType, id);
    }

    @Inject(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;Lde/maxhenkel/easyvillagers/blocks/tileentity/AutoTraderTileentity;Lnet/minecraft/world/Container;Lnet/minecraft/world/Container;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V", at = @At("TAIL"))
    private void cev$initDataSlot(int id, Inventory playerInventory, Container tradeGuiInv, AutoTraderTileentity trader, Container inputInventory, Container outputInventory, ContainerLevelAccess access, CallbackInfo ci) {
        if (trader != null) {
            this.cev$canCycleSlot = this.addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    EasyVillagerEntity villager = trader.getVillagerEntity();
                    if (villager == null) {
                        return 0;
                    }
                    if (villager.getVillagerXp() > 0 || villager.getVillagerData().getLevel() > 1) {
                        return 0;
                    }
                    if (!trader.hasWorkstation()) {
                        return 0;
                    }
                    VillagerProfession profession = villager.getVillagerData().getProfession();
                    if (profession == VillagerProfession.NONE || profession == VillagerProfession.NITWIT) {
                        return 0;
                    }
                    return 1;
                }

                @Override
                public void set(int value) {
                }
            });
        } else {
            this.cev$canCycleSlot = this.addDataSlot(DataSlot.standalone());
        }
    }

    @Override
    public boolean create_easy_villagers$canCycle() {
        return this.cev$canCycleSlot != null && this.cev$canCycleSlot.get() == 1;
    }
}
