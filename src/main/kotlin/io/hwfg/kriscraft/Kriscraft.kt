package io.hwfg.kriscraft

import io.hwfg.kriscraft.mossbed.MossBedBlockEntity
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level

class Kriscraft : ModInitializer {
    fun eatMoss(player: Player,level : Level,hand : InteractionHand) : InteractionResult{
        if (player.getItemInHand(hand).item == Items.MOSS_BLOCK){
            player.heal(20.0F)
            if (!player.isInvulnerable) player.getItemInHand(hand).shrink(1)
            player.displayClientMessage(
                Component.translatable("kriscraft.eatmoss"),
                true
            )
            if (!level.isClientSide){
                val serverPlayer = player as ServerPlayer
                val mossCount = serverPlayer.getAttached(Core.mossCount) ?: 0
                val newCount = mossCount + 1
                Core.eatMossCriterion.trigger(serverPlayer,newCount)
                serverPlayer.setAttached(Core.mossCount,newCount)
            }
            return InteractionResult.SUCCESS
        }
        return InteractionResult.PASS
    }
    override fun onInitialize() {
        Core.logger.info("Loading KrisCraft...")
        Core.init()
        val latestDeltarune = 5
        Core.logger.info("Waiting for the Deltarune Chapter ${latestDeltarune + 1}")
        UseItemCallback.EVENT.register(::eatMoss)
        UseEntityCallback.EVENT.register { player, level, hand, _, _ ->
            eatMoss(player, level, hand)
        }
        EntitySleepEvents.START_SLEEPING.register { entity, pos ->
            if (entity is ServerPlayer){
                if (entity.level().getBlockEntity(pos) is MossBedBlockEntity){
                    val oldValue = entity.getAttached(Core.sleepMossCount) ?: 0
                    val newValue = oldValue + 1
                    Core.sleepMossCriterion.trigger(entity,newValue)
                    entity.setAttached(Core.sleepMossCount,newValue)
                }
            }
        }
        Core.logger.info("Done!")
    }
}
