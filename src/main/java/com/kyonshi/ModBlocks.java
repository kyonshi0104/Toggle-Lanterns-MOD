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
    public static final ResourceKey<@NotNull Block> UNLIT_LANTERN_KEY =
            ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ToggleLanterns.MOD_ID, "unlit_lantern"));
    public static final ResourceKey<@NotNull Item> UNLIT_LANTERN_ITEM_KEY =
            ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ToggleLanterns.MOD_ID, "unlit_lantern"));

    public static final Block UNLIT_LANTERN = Registry.register(
            BuiltInRegistries.BLOCK,
            UNLIT_LANTERN_KEY,
            new LanternBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.LANTERN)
                    .strength(3.5F)
                    .noOcclusion()
                    .setId(UNLIT_LANTERN_KEY))
    );

    public static void initialize() {
        Registry.register(
                BuiltInRegistries.ITEM,
                UNLIT_LANTERN_ITEM_KEY,
                new BlockItem(UNLIT_LANTERN, new Item.Properties().setId(UNLIT_LANTERN_ITEM_KEY).useBlockDescriptionPrefix())
        );

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(Items.LANTERN,UNLIT_LANTERN.asItem()));
    }
}