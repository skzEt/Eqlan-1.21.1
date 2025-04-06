package net.skzEt.EqlanMod.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.skzEt.EqlanMod.block.entity.ModBlockEntity;
import net.skzEt.EqlanMod.client.screen.custom.TableOfUpgradeItemMenu;
import net.skzEt.EqlanMod.item.ModItems;
import net.skzEt.EqlanMod.recipe.ModRecipes;
import net.skzEt.EqlanMod.recipe.TableOfUpgradeItemRecipe;
import net.skzEt.EqlanMod.recipe.TableOfUpgradeItemRecipeInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class TableOfUpgradeItemEntity extends BlockEntity implements MenuProvider {
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;


    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    public TableOfUpgradeItemEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntity.TABLE_OF_UPGRADE_ITEM_BE.get(), pPos, pBlockState);

        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> TableOfUpgradeItemEntity.this.progress;
                    case 1 -> TableOfUpgradeItemEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0: TableOfUpgradeItemEntity.this.progress = i1;
                    case 1: TableOfUpgradeItemEntity.this.maxProgress = i1;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public void clearContents() {
        itemHandler.setStackInSlot(0,ItemStack.EMPTY);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.eqlanmod.table_of_upgrade_item_block");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new TableOfUpgradeItemMenu(i, inventory, this, this.data);
    }

    public final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 100;
    }

    private void craftItem() {
        Optional<RecipeHolder<TableOfUpgradeItemRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INPUT_SLOT, 1, false);
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<TableOfUpgradeItemRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertItemIntoSlot(output) && canInsertAmountItemIntoSlot(output.getCount());
    }

    private Optional<RecipeHolder<TableOfUpgradeItemRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.TABLE_OF_UPGRADE_ITEM_TYPE.get(), new TableOfUpgradeItemRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)), level);
    }

    private boolean canInsertAmountItemIntoSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    private boolean canInsertItemIntoSlot(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("upgrade.progress", progress);
        pTag.putInt("upgrade.maxProgress", maxProgress);
    }
    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("upgrade.progress");
        maxProgress = pTag.getInt("upgrade.maxProgress");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return super.getUpdateTag(pRegistries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return super.getUpdatePacket();
    }
}
