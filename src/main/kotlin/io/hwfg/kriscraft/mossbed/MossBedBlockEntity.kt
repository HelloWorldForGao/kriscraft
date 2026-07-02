package io.hwfg.kriscraft.mossbed

import io.hwfg.kriscraft.Core
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class MossBedBlockEntity(
    pos: BlockPos,
    state : BlockState
) : BlockEntity(Core.mossBedEntity,pos,state)