package net.skzEt.EqlanMod.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.EqlanMod.Eqlan;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Eqlan.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Eqlan.MOD_ID);

    public static final RegistryObject<RecipeSerializer<TableOfUpgradeItemRecipe>> TABLE_OF_UPGRADE_ITEM_SERIALIZER =
            SERIALIZERS.register("table_of_upgrade_item", TableOfUpgradeItemRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<TableOfUpgradeItemRecipe>> TABLE_OF_UPGRADE_ITEM_TYPE =
            RECIPE_TYPES.register("table_of_upgrade_item", () -> new RecipeType<TableOfUpgradeItemRecipe>() {
                @Override
                public String toString() {
                    return "table_of_upgrade_item";
                }
            });
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
