package net.skzEt.EqlanMod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.item.ModItems;
import net.skzEt.EqlanMod.particle.ModParticles;
import net.skzEt.EqlanMod.sound.ModSounds;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Eqlan.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlers {
    @SubscribeEvent
    public static void holyMantle(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();

        if (!entity.level().isClientSide) {
            int tick;
            int tickCount = 30*20;
            if (entity.getOffhandItem().getItem() == ModItems.HOLY_MANTLE.get() || entity.getMainHandItem().getItem() == ModItems.HOLY_MANTLE.get()) {
                if (entity.isInLava() || entity.isOnFire() && !isCooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get())) {
                    Cooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get(), tickCount);
                    event.setCanceled(true);
                    tick = 20 * 20;
                    entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, tick));
                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            ModSounds.HOLY_MANTLE.get(), SoundSource.PLAYERS, 1f, 1f);
                    spawnParticle(entity.level(), entity);
                    Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ModItems.HOLY_MANTLE.get()));
                } else if (!entity.isInLava() && entity.isOnFire() && entity.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                    entity.clearFire();

                } else if (entity.fallDistance >= 4f && !isCooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get())) {
                    Cooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get(), tickCount);
                    event.setCanceled(true);
                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            ModSounds.HOLY_MANTLE.get(), SoundSource.PLAYERS, 1f, 1f);
                    spawnParticle(entity.level(), entity);
                    Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ModItems.HOLY_MANTLE.get()));
                } else if (entity.isInWall() && !isCooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get())) {
                    while (entity.isInWall()) {
                        double Pos = entity.getY();
                        double i = Pos + 1;
                        entity.setPos(entity.getX(), i , entity.getZ());
                        if (!entity.isInWall()) {
                            Cooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get(), tickCount);
                            event.setCanceled(true);
                            tick = 5 * 20;
                            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, tick, 2));
                            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                                    ModSounds.HOLY_MANTLE.get(), SoundSource.PLAYERS, 1f, 1f);
                            spawnParticle(entity.level(), entity);
                            Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ModItems.HOLY_MANTLE.get()));
                            break;
                        }
                    }
                } else if (entity.hasEffect(MobEffects.WITHER) || entity.hasEffect(MobEffects.POISON) && !isCooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get())) {
                    Cooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get(), tickCount);
                    entity.removeAllEffects();
                    tick = 5 * 20;
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, tick, 2));
                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            ModSounds.HOLY_MANTLE.get(), SoundSource.PLAYERS, 1f, 1f);
                    spawnParticle(entity.level(), entity);
                    Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ModItems.HOLY_MANTLE.get()));
                } else if (entity.isAttackable() && !isCooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get())) {
                    Cooldown(entity.createCommandSourceStack().getPlayer(), ModItems.HOLY_MANTLE.get(), tickCount);
                    event.setCanceled(true);
                    tick = 10 * 20;
                    entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, tick, 50));
                    entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, tick));
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, tick, 2));
                    entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, tick, 2));
                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                            ModSounds.HOLY_MANTLE.get(), SoundSource.PLAYERS, 1f, 1f);
                    spawnParticle(entity.level(), entity);
                    Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ModItems.HOLY_MANTLE.get()));
                }
            }

        }
    }
    private static int Cooldown(Player player, Item pItem, int pTick) {
        player.getCooldowns().addCooldown(pItem, pTick);
        return 0;
    }
    private static boolean isCooldown(Player player, Item pItem) {
        return player.getCooldowns().isOnCooldown(pItem);
    }
    private static void spawnParticle(Level level, LivingEntity pos) {
        ((ServerLevel) level).sendParticles(ModParticles.HOLY_MANTLE_PARTICLE.get(),
                pos.getX(), pos.getY(), pos.getZ(), 300,
                0, 0,0, 1.4);
    }
}