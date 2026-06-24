package com.kyonshi;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

    private static Block registerLantern(String name) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(ToggleLanterns.MOD_ID, name));
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ToggleLanterns.MOD_ID, name));

        Block block = Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                new LanternBlock(BlockBehaviour.Properties.of()
                        .sound(SoundType.LANTERN)
                        .strength(3.5F)
                        .noOcclusion())
        );

        Registry.register(
                BuiltInRegistries.ITEM,
                itemKey,
                new BlockItem(block, new Item.Properties())
        );

        return block;
    }

    public static final Block UNLIT_LANTERN = registerLantern("unlit_lantern");
    public static final Block UNLIT_SOUL_LANTERN = registerLantern("unlit_soul_lantern");

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register((itemGroup) -> {
            itemGroup.addAfter(Items.LANTERN, UNLIT_LANTERN.asItem());
            itemGroup.addAfter(Items.SOUL_LANTERN, UNLIT_SOUL_LANTERN.asItem());
        });
    }
}