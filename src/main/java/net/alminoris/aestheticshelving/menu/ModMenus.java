package net.alminoris.aestheticshelving.menu;

import net.alminoris.aestheticshelving.AestheticShelving;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus
{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, AestheticShelving.MOD_ID);

    public static final RegistryObject<MenuType<ShelfMenu>> SHELF_MENU =
            MENUS.register("shelf_menu", () -> IForgeMenuType.create(ShelfMenu::new));

    public static final RegistryObject<MenuType<CeilingShelfMenu>> CEILING_SHELF_MENU =
            MENUS.register("ceiling_shelf_menu", () -> IForgeMenuType.create(CeilingShelfMenu::new));

    public static final RegistryObject<MenuType<StandingShelfMenu>> STANDING_SHELF_MENU =
            MENUS.register("standing_shelf_menu", () -> IForgeMenuType.create(StandingShelfMenu::new));

    public static final RegistryObject<MenuType<CornerShelfMenu>> CORNER_SHELF_MENU =
            MENUS.register("corner_shelf_menu", () -> IForgeMenuType.create(CornerShelfMenu::new));

    public static final RegistryObject<MenuType<LadderShelfMenu>> LADDER_SHELF_MENU =
            MENUS.register("ladder_shelf_menu", () -> IForgeMenuType.create(LadderShelfMenu::new));

    public static final RegistryObject<MenuType<TowerShelfMenu>> TOWER_SHELF_MENU =
            MENUS.register("tower_shelf_menu", () -> IForgeMenuType.create(TowerShelfMenu::new));

    public static void register(IEventBus eventBus)
    {
        MENUS.register(eventBus);
    }
}