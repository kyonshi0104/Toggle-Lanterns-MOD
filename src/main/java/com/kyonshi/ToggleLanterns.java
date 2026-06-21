package com.kyonshi;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToggleLanterns implements ModInitializer {
	public static final String MOD_ID = "toggle-lanterns";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModBlocks.initialize();

        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = level.getBlockState(pos);
            if (state.is(Blocks.LANTERN)) {
                if (!level.isClientSide()) {
                    BlockState newState = ModBlocks.UNLIT_LANTERN.defaultBlockState()
                            .setValue(LanternBlock.HANGING, state.getValue(LanternBlock.HANGING))
                            .setValue(LanternBlock.WATERLOGGED, state.getValue(LanternBlock.WATERLOGGED));

                    level.setBlockAndUpdate(pos, newState);
                    level.playSound(null, pos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }

            if (state.is(ModBlocks.UNLIT_LANTERN)) {
                if (!level.isClientSide()) {
                    BlockState newState = Blocks.LANTERN.defaultBlockState()
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

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
