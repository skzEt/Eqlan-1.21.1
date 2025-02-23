package net.skzEt.EqlanMod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.skzEt.EqlanMod.effect.ModEffect;

public class ModFoods {
    public static final FoodProperties DUMPLING = new FoodProperties.Builder().nutrition(4)
            .saturationModifier(0.1f).effect(new MobEffectInstance(ModEffect.HYDROPHOBIA_EFFECT.getHolder().get(), 400), 0.5f).build();
    public static final FoodProperties DUMPLING_SUN = new FoodProperties.Builder().nutrition(8)
            .saturationModifier(0.1f).effect(new MobEffectInstance(MobEffects.REGENERATION, 300), 1f).build();
    public static final FoodProperties OVERCOOKED_DUMPLING = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.1f).effect(new MobEffectInstance(MobEffects.HARM, 1), 1f).build();
    public static final FoodProperties LARVA = new FoodProperties.Builder().nutrition(1)
            .saturationModifier(0.1f).build();
    public static final FoodProperties COOKED_LARVA = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.1f).build();


}
