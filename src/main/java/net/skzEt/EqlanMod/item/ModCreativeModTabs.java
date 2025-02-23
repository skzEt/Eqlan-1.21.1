package net.skzEt.EqlanMod.item;

import jdk.jfr.Registered;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Eqlan.MOD_ID);

    public static final RegistryObject<CreativeModeTab> EQLAN_TAB = CREATIVE_MODE_TABS.register("eqlan_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TWITCH_DIAMOND.get()))
                    .title(Component.translatable("creativetab.eqlan_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // Twitch
                        output.accept(ModBlocks.TWITCH_BLOCK.get());
                        output.accept(ModBlocks.TWITCH_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_TWITCH_ORE.get());
                        output.accept(ModItems.TWITCH_DIAMOND.get());
                        output.accept(ModItems.TWITCH_HEART.get());
                        // Streamers
                        output.accept(ModItems.STINTOCOIN.get());
                        output.accept(ModItems.DUMPLING_SUN.get());
                        output.accept(ModItems.DRAKE_PENDANT.get());
                        output.accept(ModItems.MZLFF_MICROPHONE.get());
                        // Items
                        output.accept(ModItems.DUMPLING.get());
                        output.accept(ModItems.SCHOOL_BOOT.get());
                        output.accept(ModItems.COIN_ALLOY.get());
                        output.accept(ModItems.EBLAN_ALLOY.get());
                        // Custom Items
                        output.accept(ModItems.OM_NOM.get());
                        output.accept(ModItems.CACTUS_PLUSH.get());
                        output.accept(ModItems.HOLY_MANTLE.get());
                        // Music
                        output.accept(ModItems.BASELINE_BUSINESS_DISK.get());
                        // Armor
                        output.accept(ModItems.GLASSES.get());
                        output.accept(ModItems.STINT_HELMET.get());
                        output.accept(ModItems.BOXERS.get());
                        output.accept(ModItems.CROWN_OF_LIGHT.get());
                        // Food
                        output.accept(ModItems.OVERCOOKED_DUMPLING.get());
                        output.accept(ModItems.LARVA.get());
                        output.accept(ModItems.COOKED_LARVA.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}


