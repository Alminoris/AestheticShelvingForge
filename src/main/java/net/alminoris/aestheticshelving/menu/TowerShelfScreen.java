package net.alminoris.aestheticshelving.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alminoris.aestheticshelving.AestheticShelving;
import net.alminoris.aestheticshelving.block.ModBlocks;
import net.alminoris.aestheticshelving.block.entity.TowerShelfBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.Objects;

import static net.alminoris.aestheticshelving.util.helper.BlockSetsHelper.WOOD_COLORS;
import static net.alminoris.aestheticshelving.util.helper.BlockSetsHelper.getWoodName;

public class TowerShelfScreen extends AbstractContainerScreen<TowerShelfMenu>
{
    private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AestheticShelving.MOD_ID,
            "textures/gui/" + getWoodName(ModBlocks.TOWER_SHELVES, Objects.requireNonNull(menu.getSlot(0).container
                    instanceof TowerShelfBlockEntity be ? be : null)) + "_tower.png");

    public TowerShelfScreen(TowerShelfMenu menu, Inventory inventory, Component title)
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
        inventoryLabelY = 80;
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY)
    {
        TowerShelfBlockEntity blockEntity = Objects.requireNonNull(menu.getSlot(0).container
                instanceof TowerShelfBlockEntity be ? be : null);

        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY,
                WOOD_COLORS.get(getWoodName(ModBlocks.TOWER_SHELVES, blockEntity)), false);
        graphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY,
                WOOD_COLORS.get(getWoodName(ModBlocks.TOWER_SHELVES, blockEntity)), false);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight+12);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
