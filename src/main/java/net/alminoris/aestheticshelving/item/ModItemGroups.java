package net.alminoris.aestheticshelving.item;

import net.alminoris.aestheticshelving.AestheticShelving;
import net.alminoris.aestheticshelving.block.ModBlocks;
import net.alminoris.aestheticshelving.util.helper.BlockSetsHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AestheticShelving.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemGroups
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AestheticShelving.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ASHELF_TAB = CREATIVE_MODE_TABS.register("ashelftab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(ModBlocks.SHELVES.get("oak").get().asItem()::getDefaultInstance)
            .title(Component.translatable("itemgroup.ashelftab"))
            .displayItems((parameters, entries) ->
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
            }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}