package net.alminoris.aestheticshelving.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alminoris.aestheticshelving.AestheticShelving;
import net.alminoris.aestheticshelving.block.ModBlocks;
import net.alminoris.aestheticshelving.block.entity.StandingShelfBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.Objects;

import static net.alminoris.aestheticshelving.util.helper.BlockSetsHelper.WOOD_COLORS;
import static net.alminoris.aestheticshelving.util.helper.BlockSetsHelper.getWoodName;

public class StandingShelfScreen extends AbstractContainerScreen<StandingShelfMenu>
{
    private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AestheticShelving.MOD_ID,
            "textures/gui/" + getWoodName(ModBlocks.STANDING_SHELVES, Objects.requireNonNull(menu.getSlot(0).container
                    instanceof StandingShelfBlockEntity be ? be : null)) + "_standing.png");

    public StandingShelfScreen(StandingShelfMenu menu, Inventory inventory, Component title)
    {
        super(menu, inventory, title);
    }

    @Override
    protected void init()
    {
        super.init();
        titleLabelY = 10;
        titleLabelX = 10;
        inventoryLabelX = 10;
        inventoryLabelY = 68;
    }

    @Override
    protected void renderLabels(PoseStack graphics, int mouseX, int mouseY)
    {
        StandingShelfBlockEntity blockEntity = Objects.requireNonNull(menu.getSlot(0).container
                instanceof StandingShelfBlockEntity be ? be : null);

        drawString(graphics, this.font, this.title, this.titleLabelX, this.titleLabelY,
                WOOD_COLORS.get(getWoodName(ModBlocks.STANDING_SHELVES, blockEntity)));
        drawString(graphics, this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY,
                WOOD_COLORS.get(getWoodName(ModBlocks.STANDING_SHELVES, blockEntity)));
    }

    @Override
    protected void renderBg(PoseStack graphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        blit(graphics, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
