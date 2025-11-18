package net.alminoris.aestheticshelving;

import com.mojang.logging.LogUtils;
import net.alminoris.aestheticshelving.block.ModBlocks;
import net.alminoris.aestheticshelving.block.entity.ModBlockEntities;
import net.alminoris.aestheticshelving.block.entity.renderer.*;
import net.alminoris.aestheticshelving.item.ModItemGroups;
import net.alminoris.aestheticshelving.item.ModItems;
import net.alminoris.aestheticshelving.menu.*;
import net.alminoris.aestheticshelving.util.helper.BlockSetsHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AestheticShelving.MOD_ID)
public class AestheticShelving
{
    public static final String MOD_ID = "aestheticshelving";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AestheticShelving(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents entries)
    {
        if (entries.getTab() == ModItemGroups.ASHELF_TAB)
        {
            for(String name : BlockSetsHelper.WOODS)
            {
                entries.accept(ModBlocks.SHELVES.get(name).get());
                entries.accept(ModBlocks.STANDING_SHELVES.get(name).get());
                entries.accept(ModBlocks.CEILING_SHELVES.get(name).get());
                entries.accept(ModBlocks.CORNER_SHELVES.get(name).get());
                entries.accept(ModBlocks.LADDER_SHELVES.get(name).get());
                entries.accept(ModBlocks.TOWER_SHELVES.get(name).get());
            }

            if (ModList.get().isLoaded("arborealnature"))
            {
                for(String name : BlockSetsHelper.EXTRA_WOODS_AN)
                {
                    entries.accept(ModBlocks.SHELVES.get(name).get());
                    entries.accept(ModBlocks.STANDING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CEILING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CORNER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.LADDER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.TOWER_SHELVES.get(name).get());
                }
            }

            if (ModList.get().isLoaded("wildfields"))
            {
                for(String name : BlockSetsHelper.EXTRA_WOODS_WF)
                {
                    entries.accept(ModBlocks.SHELVES.get(name).get());
                    entries.accept(ModBlocks.STANDING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CEILING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CORNER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.LADDER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.TOWER_SHELVES.get(name).get());
                }
            }

            if (ModList.get().isLoaded("whisperleaftrees"))
            {
                for(String name : BlockSetsHelper.WT_WOOD_NAMES)
                {
                    entries.accept(ModBlocks.SHELVES.get(name).get());
                    entries.accept(ModBlocks.STANDING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CEILING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CORNER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.LADDER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.TOWER_SHELVES.get(name).get());
                }
            }

            if (ModList.get().isLoaded("silverwoodtrees"))
            {
                for(String name : BlockSetsHelper.ST_WOOD_NAMES)
                {
                    entries.accept(ModBlocks.SHELVES.get(name).get());
                    entries.accept(ModBlocks.STANDING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CEILING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CORNER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.LADDER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.TOWER_SHELVES.get(name).get());
                }
            }

            if (ModList.get().isLoaded("missingtrees"))
            {
                for(String name : BlockSetsHelper.MT_WOOD_NAMES)
                {
                    entries.accept(ModBlocks.SHELVES.get(name).get());
                    entries.accept(ModBlocks.STANDING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CEILING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CORNER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.LADDER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.TOWER_SHELVES.get(name).get());
                }
            }

            if (ModList.get().isLoaded("natures_spirit"))
            {
                for(String name : BlockSetsHelper.NSS_WOOD_NAMES)
                {
                    entries.accept(ModBlocks.SHELVES.get(name).get());
                    entries.accept(ModBlocks.STANDING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CEILING_SHELVES.get(name).get());
                    entries.accept(ModBlocks.CORNER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.LADDER_SHELVES.get(name).get());
                    entries.accept(ModBlocks.TOWER_SHELVES.get(name).get());
                }
            }
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenus.SHELF_MENU.get(), ShelfScreen::new);
            MenuScreens.register(ModMenus.STANDING_SHELF_MENU.get(), StandingShelfScreen::new);
            MenuScreens.register(ModMenus.CORNER_SHELF_MENU.get(), CornerShelfScreen::new);
            MenuScreens.register(ModMenus.CEILING_SHELF_MENU.get(), CeilingShelfScreen::new);
            MenuScreens.register(ModMenus.TOWER_SHELF_MENU.get(), TowerShelfScreen::new);
            MenuScreens.register(ModMenus.LADDER_SHELF_MENU.get(), LadderShelfScreen::new);

            BlockEntityRenderers.register(ModBlockEntities.SHELF_BLOCK_ENTITY.get(), ShelfBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.STANDING_SHELF_BLOCK_ENTITY.get(), StandingShelfBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.CORNER_SHELF_BLOCK_ENTITY.get(), CornerShelfBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.CEILING_SHELF_BLOCK_ENTITY.get(), CeilingShelfBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.TOWER_SHELF_BLOCK_ENTITY.get(), TowerShelfBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.LADDER_SHELF_BLOCK_ENTITY.get(), LadderShelfBlockEntityRenderer::new);
        }
    }
}