package multiteam.arcadia.setup.music;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;

public class ModMusics {

    public static final Lazy<SoundEvent> CLOUDY_PARADISE_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ArcadiaMod.MOD_ID, "ambient.arcadia.cloudy_paradise")));
    public static final Lazy<SoundEvent> WINGED_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ArcadiaMod.MOD_ID, "ambient.arcadia.winged")));

    public static final RegistryObject<SoundEvent> CLOUDY_PARADISE = Registration.SOUND_EVENTS.register("arcadia.ambient.cloudy_paradise", CLOUDY_PARADISE_LAZY);
    public static final RegistryObject<SoundEvent> WINGED = Registration.SOUND_EVENTS.register("arcadia.ambient.winged", WINGED_LAZY);

    public static void register() {

    }
}
