package com.kyonshi.client;

import com.kyonshi.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

@Environment(EnvType.CLIENT)
public class ToggleLanternsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Block[] translucentBlocks = {
                ModBlocks.UNLIT_LANTERN,
                ModBlocks.UNLIT_SOUL_LANTERN
        };

        for (Block block : translucentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.translucent());
        }
    }
}