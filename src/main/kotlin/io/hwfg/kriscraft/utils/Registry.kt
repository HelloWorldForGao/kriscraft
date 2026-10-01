package io.hwfg.kriscraft.utils

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour

fun registerBlock(name: String, blockFactory: (BlockBehaviour.Properties) -> Block, properties: BlockBehaviour.Properties): Block {
    val blockId = Identifier.fromNamespaceAndPath("kriscraft", name)
    val blockKey = ResourceKey.create(Registries.BLOCK, blockId)
    val finalProperties = properties.setId(blockKey)
    val block = blockFactory(finalProperties)
    val itemKey = ResourceKey.create(Registries.ITEM, blockId)
    val blockItem = BlockItem(block, Item.Properties().setId(itemKey).useBlockDescriptionPrefix())
    Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem)

    return Registry.register(BuiltInRegistries.BLOCK, blockKey, block)
}

fun <T : BlockEntity>registerBlockEntity(name : String, factory : FabricBlockEntityTypeBuilder.Factory<T>, block : Block): BlockEntityType<T> {
    val id = Identifier.fromNamespaceAndPath("kriscraft",name)
    return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,id, FabricBlockEntityTypeBuilder.create(factory,block).build())
}