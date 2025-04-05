package net.skzEt.EqlanMod.entity.render;

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
import net.skzEt.EqlanMod.entity.custom.TestBlockEnity;

public class TestBlockEntityRender implements BlockEntityRenderer<TestBlockEnity> {
    public TestBlockEntityRender(BlockEntityRendererProvider.Context context) {}
    @Override
    public void render(TestBlockEnity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.inventory.getStackInSlot(0);

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 1.15f,0.5f);
        pPoseStack.scale(0.5f, 0.5f, 0.5f);
        pPoseStack.mulPose(Axis.XN.rotationDegrees(pBlockEntity.getRenderingRotation()));
        pPoseStack.mulPose(Axis.YN.rotationDegrees(pBlockEntity.getRenderingRotation()));
        pPoseStack.mulPose(Axis.ZN.rotationDegrees(pBlockEntity.getRenderingRotation()));

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED,
                getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, pBlockEntity.getLevel(),
                1);
        pPoseStack.popPose();
    }

    private int getLightLevel(Level pLevel, BlockPos pPos) {
        int blockLight = pLevel.getBrightness(LightLayer.BLOCK, pPos);
        int skyLight = pLevel.getBrightness(LightLayer.SKY, pPos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
