package io.hwfg.kriscraft

import com.google.gson.Gson
import io.hwfg.kriscraft.Core.addAndGet
import io.hwfg.kriscraft.Core.mossHeal
import io.hwfg.kriscraft.mod.command
import io.hwfg.kriscraft.mod.commandInit
import io.hwfg.kriscraft.config.configureInit
import io.hwfg.kriscraft.config.configures
import io.hwfg.kriscraft.mod.*
import io.hwfg.kriscraft.mossbed.MossBedBlockEntity
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult
import java.nio.file.Path

class Kriscraft : ModInitializer {
    companion object{
        lateinit var configRoot : Path
        lateinit var configFile : Path
        var latestDeltarune = 5
        val gson : Gson = Gson().newBuilder()
            .setPrettyPrinting()
            .create()
    }
    fun eatMoss(
        player: Player,
        level : Level,
        hand : InteractionHand
    ) : InteractionResult{
        if (player.getItemInHand(hand).item == Items.MOSS_BLOCK){
            if (!player.isCreative) player.getItemInHand(hand).shrink(1)
            if (player.isShiftKeyDown) return InteractionResult.PASS
            if (!level.isClientSide){
                val serverPlayer = player as ServerPlayer
                serverPlayer.mossHeal()
                eatMossCriterion.trigger(
                    serverPlayer,
                    serverPlayer.addAndGet(
                        mossCount,
                        1
                    )
                )
            }
            return InteractionResult.SUCCESS
        }
        return InteractionResult.PASS
    }
    fun eatBlockMoss(
        player: Player,
        level : Level,
        result: BlockHitResult
    ): InteractionResult {
        if (level.getBlockState(result.blockPos).block.asItem() == Items.MOSS_BLOCK){
            if (!player.isCreative) level.setBlock(
                result.blockPos,
                Blocks.AIR.defaultBlockState(),
                0
            )
            if (player.isShiftKeyDown) return InteractionResult.PASS
            if (!level.isClientSide){
                val serverPlayer = player as ServerPlayer
                serverPlayer.mossHeal()
                eatMossCriterion.trigger(
                    serverPlayer,
                    serverPlayer.addAndGet(
                        mossCount,
                        1
                    )
                )
            }
            return InteractionResult.SUCCESS
        }
        return InteractionResult.PASS
    }
    override fun onInitialize() {
        Core.logger.info("Loading KrisCraft...")
        configRoot = FabricLoader.getInstance().configDir
        configFile = configRoot.resolve("kriscraft.json")
        regEvent()
        init()
        Core.logger.info("Waiting for the Deltarune Chapter ${latestDeltarune + 1}")
    }
    fun init(){
        Core.init()
        attachmentInit()
        blockInit()
        criterionInit()
        effectInit()
        itemInit()
        commandInit()
        configureInit()
    }
    fun regEvent(){
        UseItemCallback.EVENT.register { player, level, hand ->
            if (configures["can_eat_moss"]?.asBoolean == true) eatMoss(player, level, hand)
            else InteractionResult.PASS
        }
        UseEntityCallback.EVENT.register { player, level, hand, _, _ ->
            if (configures["can_eat_moss"]?.asBoolean == true) eatMoss(player, level, hand)
            else InteractionResult.PASS
        }
        UseBlockCallback.EVENT.register { player, level, _, result ->
            if (configures["can_eat_moss_block"]?.asBoolean == true) eatBlockMoss(player, level, result)
            else InteractionResult.PASS
        }
        EntitySleepEvents.START_SLEEPING.register { entity, pos ->
            if (entity is ServerPlayer){
                if (entity.level().getBlockEntity(pos) is MossBedBlockEntity){
                    sleepMossCriterion.trigger(
                        entity,
                        entity.addAndGet(
                            sleepMossCount,
                            1
                        )
                    )
                }
            }
        }
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            dispatcher.register(command)
            dispatcher.register(proceed)
        }
    }
}