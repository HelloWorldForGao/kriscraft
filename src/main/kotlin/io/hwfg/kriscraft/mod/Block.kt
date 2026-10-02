package io.hwfg.kriscraft.mod

import io.hwfg.kriscraft.mossbed.MossBedBlock
import io.hwfg.kriscraft.utils.registerBlock
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
    BlockBehaviour.Properties.ofFullCopy(Blocks.BED.green)
        .sound(SoundType.MOSS)
)

fun blockInit(){
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
        .register { p0 ->
            p0.insertAfter(Items.BED.green,mossBedBlock.asItem())
        }
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COLORED_BLOCKS)
        .register { p0 ->
            p0.insertAfter(Items.BED.green,mossBedBlock.asItem())
        }
}