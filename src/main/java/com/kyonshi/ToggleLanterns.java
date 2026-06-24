package com.kyonshi;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ToggleLanterns implements ModInitializer {
    public static final String MOD_ID = "toggle-lanterns";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final Map<Block, Block> TO_UNLIT = new HashMap<>();
    private static final Map<Block, Block> TO_LIT = new HashMap<>();

    static {
        registerPair(Blocks.LANTERN, ModBlocks.UNLIT_LANTERN);
        registerPair(Blocks.SOUL_LANTERN, ModBlocks.UNLIT_SOUL_LANTERN);
    }

    private static void registerPair(Block lit, Block unlit) {
        TO_UNLIT.put(lit, unlit);
        TO_LIT.put(unlit, lit);
    }

    @Override
    public void onInitialize() {
        ModBlocks.initialize();

        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = level.getBlockState(pos);
            Block currentBlock = state.getBlock();

            if (TO_UNLIT.containsKey(currentBlock)) {
                if (!level.isClientSide()) {
                    Block targetUnlitBlock = TO_UNLIT.get(currentBlock);
                    BlockState newState = targetUnlitBlock.defaultBlockState()
                            .setValue(LanternBlock.HANGING, state.getValue(LanternBlock.HANGING))
                            .setValue(LanternBlock.WATERLOGGED, state.getValue(LanternBlock.WATERLOGGED));

                    level.setBlockAndUpdate(pos, newState);
                    level.playSound(null, pos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }

            if (TO_LIT.containsKey(currentBlock)) {
                if (!level.isClientSide()) {
                    Block targetLitBlock = TO_LIT.get(currentBlock);
                    BlockState newState = targetLitBlock.defaultBlockState()
                            .setValue(LanternBlock.HANGING, state.getValue(LanternBlock.HANGING))
                            .setValue(LanternBlock.WATERLOGGED, state.getValue(LanternBlock.WATERLOGGED));

                    level.setBlockAndUpdate(pos, newState);
                    level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        });
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}