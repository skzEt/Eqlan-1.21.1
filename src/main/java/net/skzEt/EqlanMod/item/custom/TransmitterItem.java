package net.skzEt.EqlanMod.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.Tags;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.client.ModKeyboardHelper;
import net.skzEt.EqlanMod.item.ModItems;
import net.skzEt.EqlanMod.sound.ModSounds;

import java.io.File;
import java.util.List;
import java.util.Random;

public class TransmitterItem extends Item {
    private static int Count = 0;
    public TransmitterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        int random = new Random().nextInt(1, 7);
        int tickCount = 300*20;
        BlockPos pos = pContext.getClickedPos();
        if (Count == 3) {
            pContext.getLevel().playSound(null, pContext.getPlayer().getX(), pContext.getPlayer().getY(), pContext.getPlayer().getZ(),
                    ModSounds.BLYAT.get(), SoundSource.PLAYERS, 0.8f, 1f);
            pContext.getLevel().explode(pContext.getPlayer(), pos.getX(), pos.getY(), pos.getZ(), 30f, Level.ExplosionInteraction.BLOCK);
            pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                    pContext.getItemInHand().getEquipmentSlot());
        }
        if (!pContext.getPlayer().getCooldowns().isOnCooldown(ModItems.TRANSMITTER.get())) {
            switch (random) {
                case 1:
                    pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                            ModSounds.FNAF_1.get(), SoundSource.PLAYERS, 1f, 1f);
                    Count++;
                    pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
                    break;
                case 2:
                    pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                            ModSounds.FNAF_2.get(), SoundSource.PLAYERS, 1f, 1f);
                    Count++;
                    pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
                    break;
                case 3:
                    pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                            ModSounds.FNAF_3.get(), SoundSource.PLAYERS, 1f, 1f);
                    Count++;
                    pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
                    break;
                case 4:
                    pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                            ModSounds.FNAF_4.get(), SoundSource.PLAYERS, 1f, 1f);
                    Count++;
                    pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
                    break;
                case 5:
                    pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                            ModSounds.FNAF_5.get(), SoundSource.PLAYERS, 1f, 1f);
                    Count++;
                    pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
                    break;
                case 6:
                    pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                            ModSounds.FNAF_6.get(), SoundSource.PLAYERS, 1f, 1f);
                    Count++;
                    pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
                    break;
                case 7:
                    pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                            SoundEvents.CREEPER_PRIMED, SoundSource.PLAYERS, 1f, 1f);
                    Count++;
                    pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
                    break;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (ModKeyboardHelper.isHoldingShift()) {
            pTooltipComponents.add(Component.literal("НЕ ИСПОЛЬЗОВАТЬ 10 РАЗ! ВОЗВРАТУ НЕ ПРЕНАДЛЕЖИТ"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
