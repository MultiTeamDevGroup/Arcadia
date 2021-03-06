package multiteam.arcadia.setup;

import com.google.common.collect.ImmutableSet;
import multiteam.arcadia.setup.entitys.villager.PointOfInterests;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;

public class VillagerProfessions {

    //EXAMPLE VILLAGER PROFESSION
    //public static final RegistryObject<VillagerProfession> GARDEN_SOLDIER_COMMANDER = Registration.PROFESSIONS.register("garden_soldier_commander", () -> new VillagerProfession("garden_soldier_commander", PointOfInterests.SOLDIER_COMMANDER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_WEAPONSMITH));

    static void register() {}
}
