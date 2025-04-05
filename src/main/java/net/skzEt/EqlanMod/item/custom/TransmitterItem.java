package net.skzEt.EqlanMod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.skzEt.EqlanMod.client.ModKeyboardHelper;
import net.skzEt.EqlanMod.item.ModItems;
import net.skzEt.EqlanMod.sound.ModSounds;

import java.util.List;
import java.util.Random;

public class TransmitterItem extends Item {
    public TransmitterItem(Properties pProperties) {
        super(pProperties);
    }

    private SoundEvent Sounds[] = new SoundEvent[] {
            ModSounds.FNAF_1.get(),
            ModSounds.FNAF_2.get(),
            ModSounds.FNAF_3.get(),
            ModSounds.FNAF_4.get(),
            ModSounds.FNAF_5.get(),
            ModSounds.FNAF_6.get()
    };

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        int tickCount = 300*20;
        BlockPos pos = pContext.getClickedPos();
        if (1 == new Random().nextInt(1, 10)) {
            pContext.getLevel().playSound(null, pContext.getPlayer().getX(), pContext.getPlayer().getY(), pContext.getPlayer().getZ(),
                    ModSounds.BLYAT.get(), SoundSource.PLAYERS, 0.8f, 1f);
            pContext.getLevel().explode(pContext.getPlayer(), pos.getX(), pos.getY(), pos.getZ(), 30f, Level.ExplosionInteraction.BLOCK);
            pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                    pContext.getItemInHand().getEquipmentSlot());
        }
        if (!pContext.getPlayer().getCooldowns().isOnCooldown(ModItems.TRANSMITTER.get())) {
            pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                    Sounds[new Random().nextInt(Sounds.length)], SoundSource.PLAYERS, 1f, 1f);
            pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
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
