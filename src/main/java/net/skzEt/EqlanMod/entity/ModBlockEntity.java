package net.skzEt.EqlanMod.entity;

import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.EqlanMod.Eqlan;
import net.skzEt.EqlanMod.block.ModBlocks;
import net.skzEt.EqlanMod.entity.custom.TestBlockEnity;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Eqlan.MOD_ID);

    public static final RegistryObject<BlockEntityType<TestBlockEnity>> TEST_BLOCK =
            BLOCK_ENITY.register("test", () -> BlockEntityType.Builder.of(TestBlockEnity::new,
                    ModBlocks.TEST_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENITY.register(eventBus);
    }
}
