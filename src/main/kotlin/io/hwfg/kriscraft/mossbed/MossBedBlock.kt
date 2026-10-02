package io.hwfg.kriscraft.mossbed

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.InsideBlockEffectApplier
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BedBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class MossBedBlock(
    color : DyeColor,
    settings : Properties
)  : BedBlock(color,settings){
    //override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity = MossBedBlockEntity(blockPos,blockState)
}