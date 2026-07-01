package io.hwfg.kriscraft

import net.fabricmc.api.ModInitializer
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
            player.getItemInHand(hand).shrink(1)
            player.displayClientMessage(
                Component.translatable("kriscraft.eatmoss"),
                true
            )
            if (!level.isClientSide){
                val serverPlayer = player as ServerPlayer
                val mossCount = serverPlayer.getAttached(Core.MOSS_COUNT) ?: 0
                val newCount = mossCount + 1
                Core.EAT_MOSS_CRITERION.trigger(serverPlayer,newCount)
                serverPlayer.setAttached(Core.MOSS_COUNT,newCount)
            }
            return InteractionResult.SUCCESS
        }
        return InteractionResult.PASS
    }
    override fun onInitialize() {
        Core.LOGGER.info("Loading KrisCraft...")
        Core.init()
        val latestDeltarune = 5
        Core.LOGGER.info("Waiting for the Deltarune Chapter ${latestDeltarune + 1}")
        UseItemCallback.EVENT.register(::eatMoss)
        UseEntityCallback.EVENT.register { player, level, hand, _, _ ->
            eatMoss(player, level, hand)
        }
        Core.LOGGER.info("Done!")
    }
}
