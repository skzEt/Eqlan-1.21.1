package net.skzEt.EqlanMod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.block.ModBlocks;
import net.skzEt.EqlanMod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }
    private static final List<ItemLike> TWITCH_SMELTABLES = List.of(
            ModBlocks.TWITCH_ORE.get(),
            ModBlocks.DEEPSLATE_TWITCH_ORE.get()
    );
    private static final List<ItemLike> STINT_SMELTABLES = List.of(
            ModItems.STINTOCOIN.get()
    );
    private static final List<ItemLike> DUMPLING_SMELTABLES = List.of(
            ModItems.DUMPLING.get()
    );
    private static final List<ItemLike> LARVA_SMELTABLES = List.of(
            ModItems.LARVA.get()
    );

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        oreSmelting(pRecipeOutput,TWITCH_SMELTABLES, RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 0.1f, 200, "twitch");
        oreBlasting(pRecipeOutput, TWITCH_SMELTABLES, RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 0.1f, 100, "twitch");

        oreSmelting(pRecipeOutput, STINT_SMELTABLES, RecipeCategory.MISC, ModItems.COIN_ALLOY.get(), 0f, 300, "stint");
        oreBlasting(pRecipeOutput,STINT_SMELTABLES, RecipeCategory.MISC,ModItems.COIN_ALLOY.get(),0f, 150, "stint");

        oreSmelting(pRecipeOutput, DUMPLING_SMELTABLES, RecipeCategory.MISC, ModItems.OVERCOOKED_DUMPLING.get(), 0f, 450, "dumpling");
        foodSmoking(pRecipeOutput, DUMPLING_SMELTABLES, RecipeCategory.MISC, ModItems.OVERCOOKED_DUMPLING.get(), 0f, 200, "dumpling");
        foodCampfireCooking(pRecipeOutput,DUMPLING_SMELTABLES, RecipeCategory.MISC, ModItems.OVERCOOKED_DUMPLING.get(), 0f, 600, "dumpling");

        oreSmelting(pRecipeOutput, LARVA_SMELTABLES, RecipeCategory.MISC, ModItems.COOKED_LARVA.get(), 0f, 100, "larva");
        foodSmoking(pRecipeOutput,LARVA_SMELTABLES, RecipeCategory.MISC, ModItems.COOKED_LARVA.get(), 0f, 50, "larva");
        foodCampfireCooking(pRecipeOutput,LARVA_SMELTABLES, RecipeCategory.MISC, ModItems.COOKED_LARVA.get(), 0f, 150, "larva");
        // Twitch Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TWITCH_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Twitch Diamond
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 9)
                .requires(ModBlocks.TWITCH_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TWITCH_BLOCK.get()), has(ModBlocks.TWITCH_BLOCK.get()))
                .save(pRecipeOutput);
        // Dumpling
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DUMPLING.get())
                .pattern(" W ")
                .pattern("WHW")
                .pattern(" W ")
                .define('W', Items.WHEAT)
                .define('H', ModItems.TWITCH_HEART.get())
                .unlockedBy(getHasName(ModItems.TWITCH_HEART.get()), has(ModItems.TWITCH_HEART.get()))
                .save(pRecipeOutput);
        // School Boot
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SCHOOL_BOOT.get())
                .pattern("KK ")
                .pattern("KB ")
                .pattern("KKC")
                .define('K', Items.DRIED_KELP)
                .define('B', Items.BLACK_DYE)
                .define('C', Items.COAL)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pRecipeOutput);
        // Twitch Heart
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TWITCH_HEART.get())
                .pattern( "TG")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Stint-o-Coin
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STINTOCOIN.get())
                .pattern(" G ")
                .pattern("GHG")
                .pattern(" G ")
                .define('G', Items.GOLD_NUGGET)
                .define('H', ModItems.TWITCH_HEART.get())
                .unlockedBy(getHasName(ModItems.TWITCH_HEART.get()), has(ModItems.TWITCH_HEART.get()))
                .save(pRecipeOutput);
        // Drake Pendant
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAKE_PENDANT.get())
                .pattern(" T ")
                .pattern("DHD")
                .pattern(" T ")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('D', Items.DIAMOND)
                .define('H', ModItems.TWITCH_HEART.get())
                .unlockedBy(getHasName(ModItems.TWITCH_HEART.get()), has(ModItems.TWITCH_HEART.get()))
                .save(pRecipeOutput);
        // Glasses
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLASSES.get())
                .pattern("C C")
                .pattern("GCG")
                .pattern(" R ")
                .define('C', Items.COAL)
                .define('G', Items.GLASS)
                .define('R', Items.RED_WOOL)
                .unlockedBy(getHasName(Items.COAL), has(ModItems.TWITCH_HEART.get()))
                .save(pRecipeOutput);
        // Dumpling Sun
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DUMPLING_SUN.get())
                .pattern("D")
                .pattern("G")
                .define('D', ModItems.DUMPLING.get())
                .define('G', ModItems.GLASSES.get())
                .unlockedBy(getHasName(ModItems.DUMPLING.get()), has(ModItems.DUMPLING.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STINT_HELMET.get())
                .pattern("CSC")
                .pattern("C C")
                .define('C', ModItems.COIN_ALLOY.get())
                .define('S', ModItems.STINTOCOIN.get())
                .unlockedBy(getHasName(ModItems.STINTOCOIN.get()), has(ModItems.STINTOCOIN.get()))
                .save(pRecipeOutput);
        // EBLAN ALLOY
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EBLAN_ALLOY.get())
                .pattern("ST")
                .pattern("DM")
                .define('S', ModItems.STINTOCOIN.get())
                .define('T', ModItems.DUMPLING_SUN.get())
                .define('D', ModItems.DRAKE_PENDANT.get())
                .define('M', ModItems.MZLFF_MICROPHONE.get())
                .unlockedBy(getHasName(ModItems.STINTOCOIN.get()), has(ModItems.STINTOCOIN.get()))
                .unlockedBy(getHasName(ModItems.DUMPLING_SUN.get()), has(ModItems.DUMPLING_SUN.get()))
                .unlockedBy(getHasName(ModItems.DRAKE_PENDANT.get()), has(ModItems.DRAKE_PENDANT.get()))
                .unlockedBy(getHasName(ModItems.MZLFF_MICROPHONE.get()), has(ModItems.MZLFF_MICROPHONE.get()))
                .save(pRecipeOutput);
        // MZLFF MICROPHONE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MZLFF_MICROPHONE.get())
                .pattern("CC ")
                .pattern("CH ")
                .pattern("  S")
                .define('C', Items.COAL)
                .define('H', ModItems.TWITCH_HEART.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_HEART.get()), has(ModItems.TWITCH_HEART.get()))
                .save(pRecipeOutput);
        // OM NOM
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OM_NOM.get())
                .pattern("LLL")
                .pattern("LER")
                .pattern("LLL")
                .define('L', Items.LIME_DYE)
                .define('E', ModItems.EBLAN_ALLOY.get())
                .define('R', Items.RED_DYE)
                .unlockedBy(getHasName(ModItems.EBLAN_ALLOY.get()), has(ModItems.EBLAN_ALLOY.get()))
                .save(pRecipeOutput);
        // HOLY MANTLE
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HOLY_MANTLE.get())
                .pattern("WTW")
                .pattern("TNT")
                .pattern(" T ")
                .define('W', Items.WITHER_SKELETON_SKULL)
                .define('T', ModBlocks.TWITCH_BLOCK.get())
                .define('N', Items.NETHER_STAR)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static void foodSmoking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smooking");
    }
    protected static void foodCampfireCooking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                              float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_campfire_cooking");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Eqlan.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
