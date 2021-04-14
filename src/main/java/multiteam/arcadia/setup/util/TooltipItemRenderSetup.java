package multiteam.arcadia.setup.util;

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

    public static void makeItemBar(List<ITextComponent> tooltip, CompoundNBT nbtTagCompound, Item barItem, Item barItemEmpty, int barLenght, int barFillAmount){
        String spacer = " ";
        for (int i = 0; i < barLenght * 5; i++){
            spacer+=" ";
        }
        tooltip.add(new StringTextComponent(spacer));
        nbtTagCompound.putBoolean("hasItemBar", true);
        nbtTagCompound.putInt("barLenght", barLenght);
        nbtTagCompound.putInt("barFillAmountDisplay", barFillAmount);
        nbtTagCompound.putInt("barItem", Item.getId(barItem));
        nbtTagCompound.putInt("barItemEmpty", Item.getId(barItemEmpty));
        nbtTagCompound.putInt("lineToRender", tooltip.size());
    }
}
