package net.skzEt.EqlanMod.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record TableOfUpgradeItemRecipeInput(ItemStack input) implements RecipeInput
{
    @Override
    public ItemStack getItem(int i) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }
}
