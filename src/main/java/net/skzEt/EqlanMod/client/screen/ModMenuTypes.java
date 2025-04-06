package net.skzEt.EqlanMod.client.screen;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.client.screen.custom.TableOfUpgradeItemMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Eqlan.MOD_ID);

    public static final RegistryObject<MenuType<TableOfUpgradeItemMenu>> TABLE_OF_UPGRADE_ITEM_MENU =
            MENU_TYPES.register("table_of_upgrade_item_menu", () -> IForgeMenuType.create(TableOfUpgradeItemMenu::new));

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
