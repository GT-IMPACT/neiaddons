package net.bdew.neiaddons.appeng;

import net.bdew.neiaddons.api.SubPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SetFakeSlotCommandHandler implements SubPacketHandler {

    public Minecraft mc;

    @Override
    public void handle(NBTTagCompound data, EntityPlayerMP player) {
        ItemStack stack = ItemStack.loadItemStackFromNBT(data.getCompoundTag("item"));
        int slotNum = data.getInteger("slot");
        Container cont = player.openContainer;
        if ((cont != null) && AddonAppeng.clsBaseContainer.isInstance(cont)) {
            Slot slot = cont.getSlot(slotNum);
            if ((slot != null) && AddonAppeng.clsSlotFake.isInstance(slot) && SlotHelper.isSlotEnabled(slot)) {

                if (cont.slotClick(slot.slotNumber, -1, 0, player) == null) stack.stackSize = 64;
                if (cont.slotClick(slot.slotNumber, 1, 0, player) == null) stack.stackSize = 1;

                slot.putStack(stack);
            }
        }
    }
}
