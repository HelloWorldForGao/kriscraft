package io.hwfg.kriscraft

import com.mojang.serialization.Codec
import io.hwfg.kriscraft.eatmoss.EatMossCriterion
import io.hwfg.kriscraft.mossbed.MossBedBlock
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.entity.BedBlockEntity
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Core {
    const val ID = "KrisCraft"
    val NAMESPACE = "kriscraft"
    val LOGGER: Logger = LoggerFactory.getLogger(ID)
    val MOSS_BED_BLOCK = registerBlock(
        "moss_bed",
        {p0 -> MossBedBlock(DyeColor.GREEN,p0)},
        BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_BED)
            .sound(SoundType.MOSS)
    )
    val MOSS_BED_ENTITY = registerBlockEntity(
        "moss_bed",
        { p0,p1 -> BedBlockEntity(p0, p1) },
        MOSS_BED_BLOCK
    )
    val MOSS_COUNT = AttachmentRegistry.createPersistent(
        Identifier.fromNamespaceAndPath("kriscraft","moss_count"),
        Codec.INT
    )
    val EAT_MOSS_CRITERION = CriteriaTriggers.register(
        Identifier.fromNamespaceAndPath("kriscraft","eat_moss").toString(),
        EatMossCriterion()
    )
    fun getBlockKey(name : String) = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NAMESPACE,name))
    fun getItemKey(name : String) = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NAMESPACE,name))
    fun registerBlock(name: String, blockFactory: (BlockBehaviour.Properties) -> Block, properties: BlockBehaviour.Properties): Block {
        val blockId = Identifier.fromNamespaceAndPath(NAMESPACE, name)
        val blockKey = ResourceKey.create(Registries.BLOCK, blockId)
        val finalProperties = properties.setId(blockKey)
        val block = blockFactory(finalProperties)
        val itemKey = ResourceKey.create(Registries.ITEM, blockId)
        val blockItem = BlockItem(block, Item.Properties().setId(itemKey).useBlockDescriptionPrefix())
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem)

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block)
    }
    fun <T : BlockEntity>registerBlockEntity(name : String,factory : FabricBlockEntityTypeBuilder.Factory<T>,block : Block): BlockEntityType<T> {
        val id = Identifier.fromNamespaceAndPath(NAMESPACE,name)
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,id, FabricBlockEntityTypeBuilder.create(factory,block).build())
    }
    fun init(){
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register { p0 ->
            p0.addAfter(Items.GREEN_BED,MOSS_BED_BLOCK.asItem())
        }
    }
}