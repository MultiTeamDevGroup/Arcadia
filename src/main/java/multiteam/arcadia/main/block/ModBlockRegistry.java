package multiteam.arcadia.main.block;

import multiteam.arcadia.main.Registration;
import multiteam.arcadia.main.block.cloud.CloudBlock;
import multiteam.arcadia.main.item.ModItemRegistry;
import multiteam.multicore_lib.setup.utilities.generic.RegistrationTool;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockRegistry {
    public static final Material CLOUD_MATERIAL = (new Material(MaterialColor.NONE, false, false, false, false, false, false, Material.ICE.getPushReaction()));

    public static final RegistryObject<Block> CLOUD_BLOCK = RegistrationTool.registerWithItem("cloud_block", () -> new CloudBlock(BlockBehaviour.Properties.of(CLOUD_MATERIAL).strength(0, 0).sound(SoundType.WOOL).emissiveRendering(ModBlockRegistry::always).isViewBlocking(ModBlockRegistry::never).noCollission(), true), new Item.Properties().tab(ModItemRegistry.ARCADIA_ITEM_GROUP), Registration.BLOCKS, Registration.ITEMS);

    public static void register(){}

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

}
