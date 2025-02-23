package net.skzEt.EqlanMod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.item.ModItems;
import net.skzEt.EqlanMod.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {


    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Eqlan.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {
        this.add("larva_from_dirt",
                new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DIRT).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.LARVA.get()));

        add("boxers_from_ruined_portal",
                new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.4f).build()}, ModItems.BOXERS.get()));

        add("stintocoin_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.STINTOCOIN.get()));
        add("drake_pendant_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.DRAKE_PENDANT.get()));
        add("dumpling_sun_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.DUMPLING_SUN.get()));
        add("mzlff_microphone_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.MZLFF_MICROPHONE.get()));
        add("baseline_business_disk_from_village", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village")).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.BASELINE_BUSINESS_DISK.get()));
    }
}
