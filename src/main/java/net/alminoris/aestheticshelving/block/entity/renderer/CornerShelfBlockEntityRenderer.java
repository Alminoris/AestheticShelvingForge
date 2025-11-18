package net.alminoris.aestheticshelving.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.alminoris.aestheticshelving.block.custom.CornerShelfBlock;
import net.alminoris.aestheticshelving.block.entity.CornerShelfBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.List;

public class CornerShelfBlockEntityRenderer implements BlockEntityRenderer<CornerShelfBlockEntity>
{
    public CornerShelfBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(CornerShelfBlockEntity entity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        List<ItemStack> stacks = entity.getRenderStack();

        if (stacks.isEmpty() || entity.getLevel() == null)
            return;

        Direction facing = entity.getBlockState().getValue(CornerShelfBlock.FACING);

        poseStack.pushPose();

        poseStack.translate(0.5, 0.5, 0.5);
        switch (facing)
        {
            case NORTH -> poseStack.mulPose(Axis.YP.rotationDegrees(0f));
            case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180F));
            case WEST  -> poseStack.mulPose(Axis.YP.rotationDegrees(90F));
            case EAST  -> poseStack.mulPose(Axis.YP.rotationDegrees(-90f));
        }
        poseStack.translate(-0.5, -0.5, -0.5);

        float[] xArr = new float[] { 0.525f, 0.85f };
        float[] zArr = new float[] { 0.15f, 0.475f };
        int i = 0;
        for (ItemStack stack : stacks)
        {
            poseStack.pushPose();
            poseStack.translate(xArr[i], (stack.getItem() instanceof BlockItem) ? 0.64f : 0.5875f, zArr[i]);
            poseStack.scale(0.25f, 0.25f, 0.25f);
            if (stack.getItem() instanceof BlockItem)
            {
                poseStack.mulPose(Axis.YP.rotationDegrees(315));
                poseStack.mulPose(Axis.XP.rotationDegrees(330));
            }
            else
            {
                poseStack.mulPose(Axis.XP.rotationDegrees(270));
            }

            itemRenderer.renderStatic(
                    stack,
                    ItemDisplayContext.GUI,
                    getLightLevel(entity.getLevel(), entity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    bufferSource,
                    entity.getLevel(),
                    0
            );

            poseStack.popPose();
            i++;
        }

        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos)
    {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}