package net.skzEt.EqlanMod.block.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.skzEt.EqlanMod.block.entity.custom.TableOfUpgradeItemEntity;

public class TableOfUpgradeItemRender implements BlockEntityRenderer<TableOfUpgradeItemEntity> {
    public TableOfUpgradeItemRender(BlockEntityRendererProvider.Context context) {}
    @Override
    public void render(TableOfUpgradeItemEntity entity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = entity.itemHandler.getStackInSlot(0);

        poseStack.pushPose();
        poseStack.translate(0.5f, 1.05f,0.5f);
        poseStack.scale(0.6f, 0.6f, 0.6f);
        poseStack.mulPose(Axis.YN.rotationDegrees(90));
        poseStack.mulPose(Axis.XN.rotationDegrees(90));

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED,
                getLightLevel(entity.getLevel(), entity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, entity.getLevel(),
                1);
        poseStack.popPose();
    }

    private int getLightLevel(Level pLevel, BlockPos pPos) {
        int blockLight = pLevel.getBrightness(LightLayer.BLOCK, pPos);
        int skyLight = pLevel.getBrightness(LightLayer.SKY, pPos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
