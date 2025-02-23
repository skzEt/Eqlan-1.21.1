package net.skzEt.EqlanMod.effect;

import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidType;

public class HydrophobiaEffect extends MobEffect {
    protected HydrophobiaEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide && pLivingEntity.isInWater()) {
            pLivingEntity.setJumping(true);
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 2));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 400, 200));
            if (pLivingEntity.getHealth() <= 6) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 400, 10));
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 400, 100));
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
