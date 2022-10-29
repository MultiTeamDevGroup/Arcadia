package multiteam.arcadia.main.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CloudVial extends Item {
    private final int tooltipColor;
    private final String tooltipName;

    public CloudVial(Properties p_41383_, int tooltipColor, String tooltipName) {
        super(p_41383_);
        this.tooltipColor = tooltipColor;
        this.tooltipName = tooltipName;
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.arcadia." + this.tooltipName).withStyle(Style.EMPTY.withColor(tooltipColor)));
    }
}
