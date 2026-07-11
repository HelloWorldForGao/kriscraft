package io.hwfg.kriscraft

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.advancements.criterion.ContextAwarePredicate
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

object Core {
    val logger: Logger = LoggerFactory.getLogger("KrisCraft")
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
    fun <T : BlockEntity>registerBlockEntity(name : String,factory : FabricBlockEntityTypeBuilder.Factory<T>,block : Block): BlockEntityType<T> {
        val id = Identifier.fromNamespaceAndPath("kriscraft",name)
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,id, FabricBlockEntityTypeBuilder.create(factory,block).build())
    }
    fun ServerPlayer.getAndAdd(type : AttachmentType<Int>, num : Int) : Int{
        val oldValue = this.getAttached(type) ?: 0
        val newValue = oldValue + num
        this.setAttached(type,newValue)
        return oldValue
    }
    fun ServerPlayer.addAndGet(type : AttachmentType<Int>, num : Int) : Int{
        val oldValue = this.getAttached(type) ?: 0
        val newValue = oldValue + num
        this.setAttached(type,newValue)
        return newValue
    }
    fun Player.isHanding(hand : InteractionHand,item : Item) : Boolean = this.getItemInHand(hand).item == item
    fun init() = Unit
    class CountableCriterion : SimpleCriterionTrigger<CountableCondition>() {
        override fun codec(): Codec<CountableCondition> = CountableCondition.codec
        fun trigger(player: ServerPlayer,time : Int) = super.trigger(player){p0 ->
            time >= p0.time
        }
        fun trigger(player: ServerPlayer,attachment : AttachmentType<Int>,num : Int = 1) = trigger(
            player,
            player.addAndGet(attachment,num)
        )
    }

    data class CountableCondition(
        val p0 : Optional<ContextAwarePredicate>,
        val time : Int
    ) : SimpleCriterionTrigger.SimpleInstance{
        override fun player(): Optional<ContextAwarePredicate> = p0
        companion object {
            val codec : Codec<CountableCondition> = RecordCodecBuilder.create { p0 ->
                p0.group(
                    ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(CountableCondition::p0),
                    Codec.INT.fieldOf("time").forGetter(CountableCondition::time)
                ).apply(p0,::CountableCondition)
            }
        }
    }
}