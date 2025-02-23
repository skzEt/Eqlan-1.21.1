package net.skzEt.EqlanMod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.EqlanMod.Eqlan;

public class ModEffect {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Eqlan.MOD_ID);

    public static final RegistryObject<MobEffect> HYDROPHOBIA_EFFECT = MOB_EFFECTS.register("hydrophobia",
            () -> new HydrophobiaEffect(MobEffectCategory.NEUTRAL, 172380));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
