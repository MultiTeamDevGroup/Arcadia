package multiteam.arcadia.setup.music;

import multiteam.arcadia.ArcadiaMod;
import multiteam.arcadia.setup.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;

public class ModMusics {

    //Music and Disc LAZY
    public static final Lazy<SoundEvent> CLOUDY_PARADISE_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ArcadiaMod.MOD_ID, "ambient.arcadia.cloudy_paradise")));
    public static final Lazy<SoundEvent> WINGED_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ArcadiaMod.MOD_ID, "ambient.arcadia.winged")));

    //Zap LAZY
    public static final Lazy<SoundEvent> ZAP_BOTTLE_CAPTURE_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ArcadiaMod.MOD_ID, "entity.arcadia.zap.bottle_capture")));
    public static final Lazy<SoundEvent> ZAP_BOTTLE_RELEASE_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ArcadiaMod.MOD_ID, "entity.arcadia.zap.bottle_release")));
    public static final Lazy<SoundEvent> ZAP_FUNCTION_ACTIVATE_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ArcadiaMod.MOD_ID, "entity.arcadia.zap.function_activate")));



    //Music and Disc
    public static final RegistryObject<SoundEvent> CLOUDY_PARADISE = Registration.SOUND_EVENTS.register("arcadia.ambient.cloudy_paradise", CLOUDY_PARADISE_LAZY);
    public static final RegistryObject<SoundEvent> WINGED = Registration.SOUND_EVENTS.register("arcadia.ambient.winged", WINGED_LAZY);

    //Zap
    public static final RegistryObject<SoundEvent> ZAP_BOTTLE_CAPTURE = Registration.SOUND_EVENTS.register("arcadia.entity.zap.bottle_capture", ZAP_BOTTLE_CAPTURE_LAZY);
    public static final RegistryObject<SoundEvent> ZAP_BOTTLE_RELEASE = Registration.SOUND_EVENTS.register("arcadia.entity.zap.bottle_release", ZAP_BOTTLE_RELEASE_LAZY);
    public static final RegistryObject<SoundEvent> ZAP_FUNCTION_ACTIVATE = Registration.SOUND_EVENTS.register("arcadia.entity.zap.function_activate", ZAP_FUNCTION_ACTIVATE_LAZY);


    public static void register() {

    }
}
