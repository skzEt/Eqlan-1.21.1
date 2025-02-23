package net.skzEt.EqlanMod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.skzEt.EqlanMod.client.ModKeyboardHelper;
import net.skzEt.EqlanMod.item.ModItems;
import net.skzEt.EqlanMod.sound.ModSounds;
import net.skzEt.EqlanMod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OmNomItem extends Item {
    ItemCooldowns itemCooldowns;
    public OmNomItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide) {
            BlockPos blockPos = pContext.getClickedPos();
            Level level = pContext.getLevel();
            BlockState state = pContext.getLevel().getBlockState(blockPos);

            if (!isValuableBlock(state)) {
                pContext.getPlayer().getCooldowns().addCooldown(ModItems.OM_NOM.get(), 65);
                itemCooldowns.addCooldown(this.asItem(), 45);
                pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        ModSounds.OM_NOM_USED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
                level.destroyBlock(blockPos, false);

            } else if (isOreBlock(state)) {
                pContext.getPlayer().getCooldowns().addCooldown(ModItems.OM_NOM.get(), 65);
                pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        ModSounds.OM_NOM_USED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
                level.destroyBlock(blockPos, true);
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                        pContext.getItemInHand().getEquipmentSlot());
            } else {
                pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        ModSounds.OM_NOM_EXPLODED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
                pContext.getPlayer().addEffect(new MobEffectInstance(MobEffects.HARM, 10, 100));
                pContext.getItemInHand().hurtAndBreak(600, pContext.getPlayer(),
                        pContext.getItemInHand().getEquipmentSlot());
            }

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (ModKeyboardHelper.isHoldingShift()) {
            pTooltipComponents.add(Component.literal("Не использовать на бедрок и обсидиан"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.OM_NOM_VALUABLES);
    }
    private boolean isOreBlock(BlockState state) {
        return state.is(ModTags.Blocks.OM_NOM_ORE);
    }
}
