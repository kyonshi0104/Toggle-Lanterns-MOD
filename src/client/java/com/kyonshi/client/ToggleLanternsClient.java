package com.kyonshi.client;

import com.kyonshi.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;

@Environment(EnvType.CLIENT)
public class ToggleLanternsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 透過させたいランタンを配列にまとめる
        Block[] translucentBlocks = {
                ModBlocks.UNLIT_LANTERN,
                ModBlocks.UNLIT_SOUL_LANTERN,
                ModBlocks.UNLIT_COPPER_LANTERN,
                ModBlocks.UNLIT_EXPOSED_COPPER_LANTERN,
                ModBlocks.UNLIT_WEATHERED_COPPER_LANTERN,
                ModBlocks.UNLIT_OXIDIZED_COPPER_LANTERN,
                ModBlocks.UNLIT_WAXED_COPPER_LANTERN,
                ModBlocks.UNLIT_WAXED_EXPOSED_COPPER_LANTERN,
                ModBlocks.UNLIT_WAXED_WEATHERED_COPPER_LANTERN,
                ModBlocks.UNLIT_WAXED_OXIDIZED_COPPER_LANTERN
        };

        for (Block block : translucentBlocks) {
            BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.TRANSLUCENT);
        }
    }
}