package net.skzEt.EqlanMod.sound;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.EqlanMod.Eqlan;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Eqlan.MOD_ID);
    public static final RegistryObject<SoundEvent> CACTUS_USED = registerSoundEvents("cactus_used");
    public static final RegistryObject<SoundEvent> OM_NOM_USED = registerSoundEvents("om_nom_used");
    public static final RegistryObject<SoundEvent> OM_NOM_EXPLODED = registerSoundEvents("om_nom_exploded");
    public static final RegistryObject<SoundEvent> HOLY_MANTLE = registerSoundEvents("holy_mantle_used");
    public static final RegistryObject<SoundEvent> HOLY_SOUND = registerSoundEvents("holy_sound");

    public static final RegistryObject<SoundEvent> BASELINE_BUSINESS = registerSoundEvents("baseline_business");
    public static final ResourceKey<JukeboxSong> BASELINE_BUSINESS_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(Eqlan.MOD_ID, "baseline_business"));


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Eqlan.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
