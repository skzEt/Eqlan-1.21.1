package net.skzEt.EqlanMod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.skzEt.EqlanMod.block.ModBlocks;
import net.skzEt.EqlanMod.block.entity.render.TableOfUpgradeItemRender;
import net.skzEt.EqlanMod.client.screen.ModMenuTypes;
import net.skzEt.EqlanMod.client.screen.custom.TableOfUpgradeItemScreen;
import net.skzEt.EqlanMod.effect.ModEffect;
import net.skzEt.EqlanMod.block.entity.ModBlockEntity;
import net.skzEt.EqlanMod.event.EventHandlers;
import net.skzEt.EqlanMod.item.ModCreativeModTabs;
import net.skzEt.EqlanMod.item.ModItems;
import net.skzEt.EqlanMod.loot.ModLootModifiers;
import net.skzEt.EqlanMod.particle.HolyMantleParticles;
import net.skzEt.EqlanMod.particle.ModParticles;
import net.skzEt.EqlanMod.recipe.ModRecipes;
import net.skzEt.EqlanMod.sound.ModSounds;
import org.slf4j.Logger;

@Mod(Eqlan.MOD_ID)
public class Eqlan
{
    public static final String MOD_ID = "eqlanmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Eqlan()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntity.register(modEventBus);
        ModEffect.register(modEventBus);

        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModParticles.register(modEventBus);
        ModSounds.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new EventHandlers());

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.TABLE_OF_UPGRADE_ITEM_MENU.get(), TableOfUpgradeItemScreen::new);
        }
        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.HOLY_MANTLE_PARTICLE.get(), HolyMantleParticles.Provider::new);
        }
        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntity.TABLE_OF_UPGRADE_ITEM_BE.get(), TableOfUpgradeItemRender::new);
        }
    }
}
