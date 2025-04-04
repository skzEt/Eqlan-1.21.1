package net.skzEt.EqlanMod.particle;


import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.skzEt.EqlanMod.Eqlan;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Eqlan.MOD_ID);

    public static final Supplier<SimpleParticleType> HOLY_MANTLE_PARTICLE = PARTICLE_TYPE.register("holy_mantle_particle",
            () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPE.register(eventBus);
    }
}
