package net.alminoris.aestheticshelving.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.alminoris.aestheticshelving.block.custom.TowerShelfBlock;
import net.alminoris.aestheticshelving.block.entity.TowerShelfBlockEntity;
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
import java.util.Map;
import java.util.TreeMap;

public class TowerShelfBlockEntityRenderer implements BlockEntityRenderer<TowerShelfBlockEntity>
{
    public TowerShelfBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(TowerShelfBlockEntity entity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        TreeMap<Integer, List<ItemStack>> oldStacks = entity.getRenderTreeMapStack();

        if (oldStacks.isEmpty() || entity.getLevel() == null)
            return;

        Direction facing = entity.getBlockState().getValue(TowerShelfBlock.FACING);

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


        float[] xArr = new float[] { 0.5f, 0.765f, 0.5f, 0.245f };
        float[] yArr = new float[] { 0.275f, 0.58f, 0.8875f };
        float[] zArr = new float[] { 0.245f, 0.5f, 0.765f, 0.5f };
        float[] yDegrees = new float[] { 0f, 90f, 180f, 270f };

        Map<Integer, List<ItemStack>> stacks = oldStacks.descendingMap();

        for (int i = 0; i < 3; i++)
        {
            List<ItemStack> layerStacks = stacks.get(i);
            if (layerStacks == null) continue;

            int j = 0;
            for (ItemStack stack : layerStacks)
            {
                if (j >= xArr.length) break;

                poseStack.pushPose();
                poseStack.translate(xArr[j], (stack.getItem() instanceof BlockItem) ? yArr[i] : (yArr[i] - 0.0525f), zArr[j]);
                poseStack.scale(0.2f, 0.2f, 0.2f);

                poseStack.mulPose(Axis.YN.rotationDegrees(yDegrees[j]));

                if (stack.getItem() instanceof BlockItem)
                {
                    poseStack.mulPose(Axis.YP.rotationDegrees(135));
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