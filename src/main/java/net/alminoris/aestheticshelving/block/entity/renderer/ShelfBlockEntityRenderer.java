package net.alminoris.aestheticshelving.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.alminoris.aestheticshelving.block.custom.ShelfBlock;
import net.alminoris.aestheticshelving.block.entity.ShelfBlockEntity;
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

public class ShelfBlockEntityRenderer implements BlockEntityRenderer<ShelfBlockEntity>
{
    public ShelfBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(ShelfBlockEntity entity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        List<ItemStack> stacks = entity.getRenderStack();

        if (stacks.isEmpty() || entity.getLevel() == null)
            return;

        Direction facing = entity.getBlockState().getValue(ShelfBlock.FACING);

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

        float f = 0.175f;
        for (ItemStack stack : stacks)
        {
            poseStack.pushPose();
            poseStack.translate(f, (stack.getItem() instanceof BlockItem) ? 0.64f : 0.5875f, 0.20f);
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
            f += 1f / stacks.size();
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