package net.skzEt.EqlanMod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.skzEt.EqlanMod.block.entity.custom.TableOfUpgradeItemEntity;

public record TableOfUpgradeItemRecipe(Ingredient inputItem, ItemStack output) implements Recipe<TableOfUpgradeItemRecipeInput> {


    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(TableOfUpgradeItemRecipeInput pInput, Level level) {
        if(level.isClientSide) {
            return false;
        }

        return inputItem.test(pInput.getItem(0));
    }

    @Override
    public ItemStack assemble(TableOfUpgradeItemRecipeInput tableOfUpgradeItemRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TABLE_OF_UPGRADE_ITEM_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.TABLE_OF_UPGRADE_ITEM_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<TableOfUpgradeItemRecipe> {
        public static final MapCodec<TableOfUpgradeItemRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(TableOfUpgradeItemRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(TableOfUpgradeItemRecipe::output)
        ).apply(inst, TableOfUpgradeItemRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, TableOfUpgradeItemRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, TableOfUpgradeItemRecipe::inputItem,
                        ItemStack.STREAM_CODEC, TableOfUpgradeItemRecipe::output,
                        TableOfUpgradeItemRecipe::new
                );

        @Override
        public MapCodec<TableOfUpgradeItemRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TableOfUpgradeItemRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
