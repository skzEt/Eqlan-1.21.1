package net.skzEt.EqlanMod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.block.ModBlocks;
import net.skzEt.EqlanMod.block.entity.custom.TableOfUpgradeItemEntity;


public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Eqlan.MOD_ID);

    public static final RegistryObject<BlockEntityType<TableOfUpgradeItemEntity>> TABLE_OF_UPGRADE_ITEM_BE =
            BLOCK_ENITY.register("table_of_upgrade_item_be",
                    () -> BlockEntityType.Builder.of(TableOfUpgradeItemEntity::new,
                            ModBlocks.TABLE_OF_UPGRADE_ITEM.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENITY.register(eventBus);
    }
}
