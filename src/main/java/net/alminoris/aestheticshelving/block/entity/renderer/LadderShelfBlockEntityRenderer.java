package net.alminoris.aestheticshelving.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.alminoris.aestheticshelving.block.custom.LadderShelfBlock;
import net.alminoris.aestheticshelving.block.entity.LadderShelfBlockEntity;
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

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LadderShelfBlockEntityRenderer implements BlockEntityRenderer<LadderShelfBlockEntity>
{
    public LadderShelfBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(LadderShelfBlockEntity entity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        TreeMap<Integer, List<ItemStack>> oldStacks = entity.getRenderTreeMapStack();

        if (oldStacks.isEmpty() || entity.getLevel() == null)
            return;

        Direction facing = entity.getBlockState().getValue(LadderShelfBlock.FACING);

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

        float[][] xArr = new float[][]
                {
                        new float[] { 0.375f, 0.625f },
                        new float[] { 0.3f, 0.5f, 0.7f },
                        new float[] { 0.35f, 0.35f, 0.65f, 0.65f },
                        new float[] { 0.3f, 0.3f, 0.5f, 0.7f, 0.7f}
                };
        float[][] zArr = new float[][]
                {
                        new float[] { 0.15f, 0.15f },
                        new float[] { 0.25f, 0.25f, 0.25f },
                        new float[] { 0.5f, 0.25f, 0.25f, 0.5f },
                        new float[] { 0.5f, 0.25f, 0.375f, 0.25f, 0.5f}
                };

        Map<Integer, List<ItemStack>> stacks = oldStacks.descendingMap();

        float[] yArr = new float[] { 1.6575f, 1.225f, 0.7825f, 0.3525f };

        for (int i = 0; i < stacks.size(); i++)
        {
            int j = 0;
            for (ItemStack stack : stacks.get(i))
            {
                poseStack.pushPose();
                poseStack.translate(xArr[i][j], !(stack.getItem() instanceof BlockItem) ? (yArr[i] - 0.0525f) : yArr[i], zArr[i][j]);
                poseStack.scale(0.2f, 0.2f, 0.2f);
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
                j++;
            }
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