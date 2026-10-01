package io.hwfg.kriscraft.mod

import io.hwfg.kriscraft.mossbed.MossBedBlock
import io.hwfg.kriscraft.mossbed.MossBedBlockEntity
import io.hwfg.kriscraft.utils.registerBlock
import io.hwfg.kriscraft.utils.registerBlockEntity
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour

@JvmField
val mossBedBlock = registerBlock(
    "moss_bed",
    { p0 -> MossBedBlock(DyeColor.GREEN, p0) },
    BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_BED)
        .sound(SoundType.MOSS)
)
@JvmField
val mossBedEntity = registerBlockEntity(
    "moss_bed",
    { p0, p1 -> MossBedBlockEntity(p0, p1) },
    mossBedBlock
)

fun blockInit(){
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
        .register { p0 ->
            p0.insertAfter(Items.GREEN_BED,mossBedBlock.asItem())
        }
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COLORED_BLOCKS)
        .register { p0 ->
            p0.insertAfter(Items.GREEN_BED,mossBedBlock.asItem())
        }
}