package net.skzEt.EqlanMod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.skzEt.EqlanMod.sound.ModSounds;
import net.skzEt.EqlanMod.util.ModTags;

public class CactusPlushItem extends Item {
    public CactusPlushItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide) {
            BlockPos blockPos = pContext.getClickedPos();
            pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                    ModSounds.OM_NOM_USED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
        }
        return InteractionResult.SUCCESS;
    }
}
