package io.hwfg.kriscraft.mossbed

import io.hwfg.kriscraft.Core
import net.minecraft.core.BlockPos
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.entity.BedBlockEntity
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class MossBedBlockEntity(
    pos: BlockPos,
    state : BlockState
) : BlockEntity(Core.MOSS_BED_ENTITY,pos,state){
    val color = DyeColor.GREEN
}