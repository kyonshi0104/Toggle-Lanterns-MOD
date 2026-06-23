package com.kyonshi;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

public class ModBlocks {

    private static Block registerLantern(String name) {
        ResourceKey<@NotNull Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ToggleLanterns.MOD_ID, name));
        ResourceKey<@NotNull Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ToggleLanterns.MOD_ID, name));

        Block block = Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                new LanternBlock(BlockBehaviour.Properties.of()
                        .sound(SoundType.LANTERN)
                        .strength(3.5F)
                        .noOcclusion()
                        .setId(blockKey))
        );

        // ブロックアイテムも同時に登録
        Registry.register(
                BuiltInRegistries.ITEM,
                itemKey,
                new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix())
        );

        return block;
    }

    // 鉄ランタン
    public static final Block UNLIT_LANTERN = registerLantern("unlit_lantern");
    public static final Block UNLIT_SOUL_LANTERN = registerLantern("unlit_soul_lantern");

    // 銅ランタン
    public static final Block UNLIT_COPPER_LANTERN = registerLantern("unlit_copper_lantern");
    public static final Block UNLIT_EXPOSED_COPPER_LANTERN = registerLantern("unlit_exposed_copper_lantern");
    public static final Block UNLIT_WEATHERED_COPPER_LANTERN = registerLantern("unlit_weathered_copper_lantern");
    public static final Block UNLIT_OXIDIZED_COPPER_LANTERN = registerLantern("unlit_oxidized_copper_lantern");

    // 銅ランタン
    public static final Block UNLIT_WAXED_COPPER_LANTERN = registerLantern("unlit_waxed_copper_lantern");
    public static final Block UNLIT_WAXED_EXPOSED_COPPER_LANTERN = registerLantern("unlit_waxed_exposed_copper_lantern");
    public static final Block UNLIT_WAXED_WEATHERED_COPPER_LANTERN = registerLantern("unlit_waxed_weathered_copper_lantern");
    public static final Block UNLIT_WAXED_OXIDIZED_COPPER_LANTERN = registerLantern("unlit_waxed_oxidized_copper_lantern");


    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register((itemGroup) -> {

            // 鉄ランタン
            itemGroup.addAfter(Items.LANTERN, UNLIT_LANTERN.asItem());
            itemGroup.addAfter(Items.SOUL_LANTERN, UNLIT_SOUL_LANTERN.asItem());

            // 銅ランタン
            itemGroup.addAfter(Items.COPPER_LANTERN.unaffected(), UNLIT_COPPER_LANTERN.asItem());

            itemGroup.addAfter(Items.COPPER_LANTERN.exposed(), UNLIT_EXPOSED_COPPER_LANTERN.asItem());
            itemGroup.addAfter(Items.COPPER_LANTERN.weathered(), UNLIT_WEATHERED_COPPER_LANTERN.asItem());
            itemGroup.addAfter(Items.COPPER_LANTERN.oxidized(), UNLIT_OXIDIZED_COPPER_LANTERN.asItem());

            // waxed
            itemGroup.addAfter(Items.COPPER_LANTERN.waxed(), UNLIT_WAXED_COPPER_LANTERN.asItem());
            itemGroup.addAfter(Items.COPPER_LANTERN.waxedExposed(), UNLIT_WAXED_EXPOSED_COPPER_LANTERN.asItem());
            itemGroup.addAfter(Items.COPPER_LANTERN.waxedWeathered(), UNLIT_WAXED_WEATHERED_COPPER_LANTERN.asItem());
            itemGroup.addAfter(Items.COPPER_LANTERN.waxedOxidized(), UNLIT_WAXED_OXIDIZED_COPPER_LANTERN.asItem());
        });
    }
}