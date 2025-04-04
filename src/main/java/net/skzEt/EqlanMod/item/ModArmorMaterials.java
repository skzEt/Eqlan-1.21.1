package net.skzEt.EqlanMod.item;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.sound.ModSounds;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    // Glasses
    public static final Holder<ArmorMaterial> GLASSES = register("glasses", Util.make(new EnumMap<>(ArmorItem.Type.class),
            attribute -> {
        attribute.put(ArmorItem.Type.HELMET, 1);
            }), 0, 0f, 0f, () -> ModItems.SCHOOL_BOOT.get());
    // Pendant
    public static final Holder<ArmorMaterial> PENDANT = register("pendant", Util.make(new EnumMap<>(ArmorItem.Type.class),
            attribute -> {
                attribute.put(ArmorItem.Type.CHESTPLATE, 6);
            }), 0, 1f, 0f, () -> ModItems.TWITCH_DIAMOND.get());
    // Stint
    public static final Holder<ArmorMaterial> STINT = register("stint", Util.make(new EnumMap<>(ArmorItem.Type.class),
            attribute -> {
                attribute.put(ArmorItem.Type.HELMET, 8);
            }), 15, 0.5f, 0f, () -> ModItems.COIN_ALLOY.get());
    // Boxers
    public static final Holder<ArmorMaterial> BOXERS = register("boxers", Util.make(new EnumMap<>(ArmorItem.Type.class),
            attribute -> {
                attribute.put(ArmorItem.Type.LEGGINGS, 4);
            }), 15, 0.1f, 0.2f, () -> ModItems.SCHOOL_BOOT.get());

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Eqlan.MOD_ID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
