package io.hwfg.kriscraft.mod

import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.hwfg.kriscraft.config.Config
import io.hwfg.kriscraft.utils.getData
import io.hwfg.kriscraft.utils.hurt
import io.hwfg.kriscraft.utils.toJson
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.permissions.Permissions
import net.minecraft.world.damagesource.DamageSource
import java.util.concurrent.CompletableFuture


val command: LiteralArgumentBuilder<CommandSourceStack> = Commands.literal("kriscraft")
    .requires { p0 ->
        p0.permissions().hasPermission(Permissions.COMMANDS_MODERATOR)
    }
    .executes { p0 ->
        p0.source.sendSystemMessage(
            Component.translatable("kriscraft.name")
                .withStyle(ChatFormatting.RED)
        )
        p0.source.sendSystemMessage(
            Component.translatable("kriscraft.description")
                .withStyle(ChatFormatting.GREEN)
        )
        1
    }
    .then(
        Commands.literal("data")
            .requires { it.isPlayer }
            .executes { p0 ->
                p0.source.playerOrException.getData()
                1
            }
            .then(
                Commands.literal("get")
                    .requires { it.isPlayer }
                    .executes{p0 ->
                        p0.source.playerOrException.getData()
                        1
                    }
            )
            .then(
                Commands.literal("del")
                    .requires { it.isPlayer }
                    .executes { p0 ->
                        val player = p0.source.playerOrException
                        player.sendSystemMessage(
                            Component.translatable("kriscraft.del")
                                .withStyle(ChatFormatting.RED)
                                .withStyle(ChatFormatting.BOLD),
                            false
                        )
                        1
                    }
                    .then(
                        Commands.literal("proceed")
                            .requires { it.isPlayer }
                            .executes { p0 ->
                                p0.source.playerOrException.cleanAttachments()
                                1
                            }
                    )
            )
            .then(
                Commands.literal("server_data")
                    .requires { it.isPlayer }
                    .executes { p0 ->
                        val player = p0.source.playerOrException
                        player.sendSystemMessage(
                            Component.literal(Config.subConfigs.getData().toJson()),
                            false
                        )
                        1
                    }
            )
    )
val proceed: LiteralArgumentBuilder<CommandSourceStack> = Commands.literal("proceed")
    .requires { it.isPlayer }
    .executes { p0 ->
        p0.source.playerOrException.hurt(
            snowgrave,
            1225F
        )
        if (!p0.source.playerOrException.isAlive)snowgraveDeathCriteria.trigger(p0.source.player ?: return@executes 0)
        1
    }
    .then(
        Commands.argument(
            "target",
            EntityArgument.entity()
        )
            .requires { p0 -> p0.permissions().hasPermission(Permissions.COMMANDS_MODERATOR) }
            .executes { p0 ->
                val damage = DamageSource(
                    p0.source.level.registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .getOrThrow(snowgrave),
                    p0.source.entity
                )
                val source = EntityArgument.getEntity(
                    p0,
                    "target"
                )
                source.hurtServer(
                    p0.source.level,
                    damage,
                    1225F
                )
                if (source !is ServerPlayer) return@executes 1
                if (!source.isAlive)snowgraveDeathCriteria.trigger(source)
                1
            }
    )

fun ServerPlayer.getData(){
    this.sendSystemMessage(
        Component.translatable(
            "kriscraft.dataget.moss_count",
            this.getAttached(mossCount) ?: 0
        ).withStyle(ChatFormatting.GREEN),
        false
    )
    this.sendSystemMessage(
        Component.translatable(
            "kriscraft.dataget.sleep_moss_count",
            this.getAttached(sleepMossCount) ?: 0
        ).withStyle(ChatFormatting.AQUA),
        false
    )
    this.sendSystemMessage(
        Component.translatable(
            "kriscraft.dataget.eat_moss_bread_count",
            this.getAttached(eatMossBreadCount) ?: 0
        ).withStyle(ChatFormatting.GOLD),
        false
    )
    this.sendSystemMessage(
        Component.translatable(
            "kriscraft.dataget.eat_moss_burger_count",
            this.getAttached(eatMossBurgerCount) ?: 0
        ).withStyle(ChatFormatting.RED),
        false
    )
    if(this.getAttached(negHealCount) != null && this.getAttached(negHealCount) != 0){
        this.sendSystemMessage(
            Component.translatable(
                "kriscraft.dataget.neg_heal_count",
                this.getAttached(negHealCount) ?: 0
            ).withStyle(ChatFormatting.RED),
            false
        )
    }
}
fun ServerPlayer.cleanAttachments(){
    this.removeAttached(mossCount)
    this.removeAttached(sleepMossCount)
    this.removeAttached(eatMossBreadCount)
    this.removeAttached(eatMossBurgerCount)
    this.removeAttached(negHealCount)
    this.sendSystemMessage(
        Component.translatable("kriscraft.del.finish"),
        false
    )
}
fun commandInit() = Unit