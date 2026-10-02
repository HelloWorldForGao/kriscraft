package io.hwfg.kriscraft

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.hwfg.kriscraft.config.Config
import io.hwfg.kriscraft.config.SubConfigData
import io.hwfg.kriscraft.mod.*
import io.hwfg.kriscraft.payload.ClientUpload
import io.hwfg.kriscraft.payload.Proceed
import io.hwfg.kriscraft.payload.ServerDownload
import io.hwfg.kriscraft.utils.*
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.permissions.Permissions
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
        lateinit var kriscraftConfigDir : Path
        lateinit var kriscraftConfigFile : Path
        lateinit var testConfigFile : Path
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
        if (player.isShiftKeyDown) return InteractionResult.PASS
        if (player.getItemInHand(hand).item == Items.MOSS_BLOCK){
            if (!player.isCreative) player.getItemInHand(hand).shrink(1)
            //if (player.isShiftKeyDown) return InteractionResult.PASS
            if (!level.isClientSide){
                val serverPlayer = player as ServerPlayer
                serverPlayer.healFromMoss(ignoreTimer = true)
                serverPlayer.negHealTrigger(false)
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
        if (player.isShiftKeyDown) return InteractionResult.PASS
        if (level.getBlockState(result.blockPos).block.asItem() == Items.MOSS_BLOCK){
            if (!player.isCreative) level.setBlock(
                result.blockPos,
                Blocks.AIR.defaultBlockState(),
                0
            )
            //if (player.isShiftKeyDown) return InteractionResult.PASS
            if (!level.isClientSide){
                val serverPlayer = player as ServerPlayer
                serverPlayer.healFromMoss(ignoreTimer = true)
                serverPlayer.negHealTrigger(false)
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
        kriscraftConfigDir = configRoot.resolve("kriscraft")
        testConfigFile = kriscraftConfigDir.resolve("test.json")
        kriscraftConfigFile = kriscraftConfigDir.resolve("config.json")
        configFile = configRoot.resolve("kriscraft.json")
        PayloadTypeRegistry.serverboundPlay().register(
            ClientUpload.type,
            ClientUpload.codec
        )
        PayloadTypeRegistry.clientboundPlay().register(
            ServerDownload.type,
            ServerDownload.codec
        )
        PayloadTypeRegistry.serverboundPlay().register(
            Proceed.type,
            Proceed.codec
        )
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
        Config.init()
    }
    fun regEvent(){
        UseItemCallback.EVENT.register { player, level, hand ->
            if (Config.forceGet("can_eat_moss")) eatMoss(player, level, hand)
            else InteractionResult.PASS
        }
        UseEntityCallback.EVENT.register { player, level, hand, _, _ ->
            if (Config.forceGet("can_eat_moss")) eatMoss(player, level, hand)
            else InteractionResult.PASS
        }
        UseBlockCallback.EVENT.register { player, level, _, result ->
            if (Config.forceGet("can_eat_moss")) eatBlockMoss(player, level, result)
            else InteractionResult.PASS
        }
        EntitySleepEvents.START_SLEEPING.register { entity, pos ->
            if (entity is ServerPlayer){
                if (entity.level().getBlockState(pos).`is`(mossBedBlock)){
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
        ServerPlayNetworking.registerGlobalReceiver(
            ClientUpload.type
        ){p0,p1 ->
            val entity = p1.player()
            if (entity.isRemoved) return@registerGlobalReceiver
            if (!entity.permissions().hasPermission(Permissions.COMMANDS_MODERATOR)){
                entity.sendSystemMessage(
                    Component.translatable("kriscraft.no_perm").withStyle(ChatFormatting.RED)
                )
                return@registerGlobalReceiver
            }
            try {
                val json = p0.json
                val data = gson.fromJson(
                    json,
                    object : TypeToken<MutableList<SubConfigData<*>>>() {}
                )
                Config.subConfigs.applyData(data)
            }
            catch (e : Exception){
                entity.sendSystemMessage(
                    Component.translatable("kriscraft.sync.error",e.stackTraceToString())
                )
            }
            finally {
                entity.sendSystemMessage(
                    Component.translatable("kriscraft.sync.finish")
                )
                p1.server().playerList.players.forEach { player ->
                    download(
                        Config.subConfigs,
                        player
                    )
                }
            }
        }
        ClientPlayNetworking.registerGlobalReceiver(
            ServerDownload.type
        ){ p0, _ ->
            val message = p0.json
            val res = gson.fromJson(
                message,
                object : TypeToken<List<SubConfigData<*>>>(){}
            )
            Config.serverConfig.applyData(res)
        }
        ServerPlayNetworking.registerGlobalReceiver(
            Proceed.type
        ){ _, p1 ->
            p1.player().hurt(
                snowgrave,
                1225F
            )
        }
        ServerPlayerEvents.JOIN.register { p0 ->
            download(Config.subConfigs,p0)
        }
    }
}