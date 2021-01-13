/*
 * Copyright (c) bdew, 2013 - 2015
 * https://github.com/bdew/neiaddons
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.neiaddons.appeng;

import net.bdew.neiaddons.api.SubPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Keyboard;

public class SetFakeSlotCommandHandler implements SubPacketHandler {
    @Override
    public void handle(NBTTagCompound data, EntityPlayerMP player) {
        ItemStack stack = ItemStack.loadItemStackFromNBT(data.getCompoundTag("item"));
        int slotNum = data.getInteger("slot");
        Container cont = player.openContainer;
        if ((cont != null) && AddonAppeng.clsBaseContainer.isInstance(cont)) {
            Slot slot = cont.getSlot(slotNum);
            if ((slot != null) && AddonAppeng.clsSlotFake.isInstance(slot) && SlotHelper.isSlotEnabled(slot)) {

                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                    stack.stackSize = 64;
                    slot.putStack(stack);
                } else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
                    stack.stackSize = 32;
                    slot.putStack(stack);
                } else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                        stack.stackSize = 16;
                        slot.putStack(stack);
                } else {
                    stack.stackSize = 1;
                    slot.putStack(stack);
                }
            }
        }
    }
}
