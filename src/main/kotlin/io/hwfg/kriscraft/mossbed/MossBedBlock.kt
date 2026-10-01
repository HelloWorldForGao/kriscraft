package io.hwfg.kriscraft.mossbed

import io.hwfg.kriscraft.utils.healFromMoss
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BedBlock
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.phys.BlockHitResult

class MossBedBlock(
    color : DyeColor,
    settings : Properties
)  : BedBlock(color,settings){
    override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity = MossBedBlockEntity(blockPos,blockState)
}