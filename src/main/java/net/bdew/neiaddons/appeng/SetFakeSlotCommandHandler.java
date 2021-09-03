package net.bdew.neiaddons.appeng;

import net.bdew.neiaddons.api.SubPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SetFakeSlotCommandHandler implements SubPacketHandler {
    @Override
    public void handle(NBTTagCompound data, EntityPlayerMP player) {
        ItemStack stack = ItemStack.loadItemStackFromNBT(data.getCompoundTag("item"));
        int slotNum = data.getInteger("slot");
        Container cont = player.openContainer;
        if ((cont != null) && AddonAppeng.clsBaseContainer.isInstance(cont)) {
            Slot slot = cont.getSlot(slotNum);
            if ((slot != null) && AddonAppeng.clsSlotFake.isInstance(slot) && SlotHelper.isSlotEnabled(slot)) {

                ItemStack targetStack = slot.getStack();
                if (null != targetStack) {
                    if (stack.isItemEqual(targetStack)) {
                        stack.stackSize = slot.getStack().stackSize + 1;
                        if (stack.stackSize > 127) { // add this check if no patch this in AE encoder
                            stack.stackSize = 127;
                        }
                    }
                }
                slot.putStack(stack);
            }
        }
    }
}
