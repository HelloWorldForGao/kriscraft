package io.hwfg.kriscraft.mod

import com.google.gson.JsonParser
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.config.configures
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.permissions.Permissions
import net.minecraft.world.damagesource.DamageSource

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
        Commands.literal("config")
            .then(
                Commands.argument(
                    "name",
                    StringArgumentType.string()
                )
                    .then(
                        Commands.argument(
                            "value",
                            StringArgumentType.string()
                        )
                            .executes { p0 ->
                                val name = p0.getArgument("name", String::class.java)
                                val value = p0.getArgument("value", String::class.java)
                                if (name !in configures.keys) return@executes 0
                                configures[name] = JsonParser.parseString(value)
                                Core.logger.info("Changed $name : $value")
                                1
                            }
                    )
            )
    )
val proceed: LiteralArgumentBuilder<CommandSourceStack> = Commands.literal("proceed")
    .executes { p0 ->
        val damage = DamageSource(
            p0.source.level.registryAccess()
                .lookupOrThrow(Registries.DAMAGE_TYPE)
                .getOrThrow(snowgrave)
        )
        p0.source.player?.hurtServer(
            p0.source.level,
            damage,
            1225F
        )
        if (p0.source.player?.isAlive == false)snowgraveDeathCriteria.trigger(p0.source.player ?: return@executes 0)
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

fun commandInit() = Unit