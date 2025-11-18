package net.alminoris.aestheticshelving.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.alminoris.aestheticshelving.block.custom.StandingShelfBlock;
import net.alminoris.aestheticshelving.block.entity.StandingShelfBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.Dictionary;
import java.util.List;

public class StandingShelfBlockEntityRenderer implements BlockEntityRenderer<StandingShelfBlockEntity>
{
    public StandingShelfBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(StandingShelfBlockEntity entity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        Dictionary<Integer, List<ItemStack>> stacks = entity.getExtraRenderStack();

        if (stacks.isEmpty() || entity.getLevel() == null)
            return;

        Direction facing = entity.getBlockState().getValue(StandingShelfBlock.FACING);

        poseStack.pushPose();

        poseStack.translate(0.5, 0.5, 0.5);
        switch (facing)
        {
            case NORTH -> poseStack.mulPose(Vector3f.YP.rotationDegrees(0f));
            case SOUTH -> poseStack.mulPose(Vector3f.YP.rotationDegrees(180F));
            case WEST  -> poseStack.mulPose(Vector3f.YP.rotationDegrees(90F));
            case EAST  -> poseStack.mulPose(Vector3f.YP.rotationDegrees(-90f));
        }
        poseStack.translate(-0.5, -0.5, -0.5);

        float f = 0.25f;
        for (ItemStack stack : stacks.get(0))
        {
            poseStack.pushPose();
            poseStack.translate(f, (stack.getItem() instanceof BlockItem) ? 0.895f : 0.8425f, 0.65f);
            poseStack.scale(0.25f, 0.25f, 0.25f);
            if (stack.getItem() instanceof BlockItem)
            {
                poseStack.mulPose(Vector3f.YP.rotationDegrees(315));
                poseStack.mulPose(Vector3f.XP.rotationDegrees(330));
            }
            else
            {
                poseStack.mulPose(Vector3f.XP.rotationDegrees(270));
            }

            itemRenderer.renderStatic(
                    stack,
                    ItemTransforms.TransformType.GUI,
                    getLightLevel(entity.getLevel(), entity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    bufferSource,
                    0
            );

            poseStack.popPose();
            f += 1f / stacks.get(0).size();
        }

        f = 0.25f;
        for (ItemStack stack : stacks.get(1))
        {
            poseStack.pushPose();
            poseStack.translate(f, (stack.getItem() instanceof BlockItem) ? 0.395f : 0.3425f, 0.65f);
            poseStack.scale(0.25f, 0.25f, 0.25f);
            if (stack.getItem() instanceof BlockItem)
            {
                poseStack.mulPose(Vector3f.YP.rotationDegrees(315));
                poseStack.mulPose(Vector3f.XP.rotationDegrees(330));
            }
            else
            {
                poseStack.mulPose(Vector3f.XP.rotationDegrees(270));
            }

            itemRenderer.renderStatic(
                    stack,
                    ItemTransforms.TransformType.GUI,
                    getLightLevel(entity.getLevel(), entity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    bufferSource,
                    0
            );

            poseStack.popPose();
            f += 1f / stacks.get(1).size();
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