package multiteam.arcadia.main.block;

import multiteam.arcadia.main.Registration;
import multiteam.arcadia.main.block.cloud.*;
import multiteam.arcadia.main.item.ModItemRegistry;
import multiteam.arcadia.main.particles.ModParticleRegistry;
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

    public static final RegistryObject<Block> CLOUD_BLOCK = RegistrationTool.registerWithItem("cloud_block", () -> new CloudBlock(BlockBehaviour.Properties.of(CLOUD_MATERIAL, MaterialColor.TERRACOTTA_WHITE).strength(0, 0).sound(SoundType.WOOL).emissiveRendering(ModBlockRegistry::always).isViewBlocking(ModBlockRegistry::never).noCollission(), true, ModParticleRegistry.CLOUD_PARTICLE), new Item.Properties().tab(ModItemRegistry.ARCADIA_ITEM_GROUP), Registration.BLOCKS, Registration.ITEMS);
    public static final RegistryObject<Block> CLOUD_BLOCK_STORMY = RegistrationTool.registerWithItem("cloud_block_stormy", () -> new StormyCloudBlock(BlockBehaviour.Properties.of(CLOUD_MATERIAL, MaterialColor.TERRACOTTA_BLACK).strength(0, 0).sound(SoundType.WOOL).emissiveRendering(ModBlockRegistry::always).isViewBlocking(ModBlockRegistry::never).noCollission(), true, ModParticleRegistry.CLOUD_PARTICLE_STORMY), new Item.Properties().tab(ModItemRegistry.ARCADIA_ITEM_GROUP), Registration.BLOCKS, Registration.ITEMS);
    public static final RegistryObject<Block> CLOUD_BLOCK_JELLY = RegistrationTool.registerWithItem("cloud_block_jelly", () -> new JellyCloudBlock(BlockBehaviour.Properties.of(CLOUD_MATERIAL, MaterialColor.COLOR_LIGHT_GREEN).strength(0, 0).sound(SoundType.WOOL).emissiveRendering(ModBlockRegistry::always).isViewBlocking(ModBlockRegistry::never).noCollission(), true, ModParticleRegistry.CLOUD_PARTICLE_JELLY), new Item.Properties().tab(ModItemRegistry.ARCADIA_ITEM_GROUP), Registration.BLOCKS, Registration.ITEMS);
    public static final RegistryObject<Block> CLOUD_BLOCK_PURPLE = RegistrationTool.registerWithItem("cloud_block_purple", () -> new PurpleCloudBlock(BlockBehaviour.Properties.of(CLOUD_MATERIAL, MaterialColor.COLOR_PURPLE).strength(0, 0).sound(SoundType.WOOL).emissiveRendering(ModBlockRegistry::always).isViewBlocking(ModBlockRegistry::never).noCollission(), false, ModParticleRegistry.CLOUD_PARTICLE_PURPLE), new Item.Properties().tab(ModItemRegistry.ARCADIA_ITEM_GROUP), Registration.BLOCKS, Registration.ITEMS);
    public static final RegistryObject<Block> CLOUD_BLOCK_SILVER = RegistrationTool.registerWithItem("cloud_block_silver", () -> new SilverCloudBlock(BlockBehaviour.Properties.of(CLOUD_MATERIAL, MaterialColor.COLOR_LIGHT_GRAY).strength(0, 0).sound(SoundType.WOOL).emissiveRendering(ModBlockRegistry::always).isViewBlocking(ModBlockRegistry::never).noCollission(), true, ModParticleRegistry.CLOUD_PARTICLE_SILVER), new Item.Properties().tab(ModItemRegistry.ARCADIA_ITEM_GROUP), Registration.BLOCKS, Registration.ITEMS);
    public static final RegistryObject<Block> CLOUD_BLOCK_GOLD = RegistrationTool.registerWithItem("cloud_block_gold", () -> new GoldCloudBlock(BlockBehaviour.Properties.of(CLOUD_MATERIAL, MaterialColor.GOLD).strength(0, 0).sound(SoundType.WOOL).emissiveRendering(ModBlockRegistry::always).isViewBlocking(ModBlockRegistry::never).noCollission(), true, ModParticleRegistry.CLOUD_PARTICLE_GOLD), new Item.Properties().tab(ModItemRegistry.ARCADIA_ITEM_GROUP), Registration.BLOCKS, Registration.ITEMS);
    public static final RegistryObject<Block> CLOUD_BLOCK_ANGRY = RegistrationTool.registerWithItem("cloud_block_angry", () -> new AngryCloudBlock(BlockBehaviour.Properties.of(CLOUD_MATERIAL, MaterialColor.COLOR_RED).strength(0, 0).sound(SoundType.WOOL).emissiveRendering(ModBlockRegistry::always).isViewBlocking(ModBlockRegistry::never).noCollission(), true, ModParticleRegistry.CLOUD_PARTICLE_ANGRY), new Item.Properties().tab(ModItemRegistry.ARCADIA_ITEM_GROUP), Registration.BLOCKS, Registration.ITEMS);

    public static void register(){}

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

}
