package net.skzEt.EqlanMod.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.skzEt.EqlanMod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier TWITCH = new ForgeTier(1500, 4, 3f, 20,
                    ModTags.Blocks.NEEDS_TWITCH_TOOLS, () -> Ingredient.of(ModItems.TWITCH_DIAMOND.get()),
            ModTags.Blocks.INCORRECT_FOR_TWITCH_TOOL);
}
