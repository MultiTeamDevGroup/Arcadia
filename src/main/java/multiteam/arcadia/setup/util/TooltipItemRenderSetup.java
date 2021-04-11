package multiteam.arcadia.setup.util;

import multiteam.arcadia.setup.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class TooltipItemRenderSetup {

    public static void putItems(List<ITextComponent> tooltip, CompoundNBT nbtTagCompound, Item[] itemsToRender){
        tooltip.add(new StringTextComponent(" "));
        nbtTagCompound.putBoolean("hasTooltipItem", true);
        int[] itemIdsToRender = new int[itemsToRender.length];
        for (int i = 0; i < itemIdsToRender.length; i++){
            itemIdsToRender[i] = Item.getId(itemsToRender[i]);
        }
        nbtTagCompound.putIntArray("itemsToRender", itemIdsToRender);
        nbtTagCompound.putInt("lineToRender", tooltip.size());
    }

    public static void putItem(List<ITextComponent> tooltip, CompoundNBT nbtTagCompound, Item itemToRender){
        tooltip.add(new StringTextComponent(" "));
        nbtTagCompound.putBoolean("hasTooltipItem", true);
        int[] itemIdsToRender = {Item.getId(itemToRender)};
        nbtTagCompound.putIntArray("itemsToRender", itemIdsToRender);
        nbtTagCompound.putInt("lineToRender", tooltip.size());
    }

}
