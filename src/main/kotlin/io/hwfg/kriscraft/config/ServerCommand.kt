package io.hwfg.kriscraft.config

import com.google.gson.JsonParser
import com.mojang.brigadier.arguments.StringArgumentType
import net.minecraft.ChatFormatting
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraft.server.permissions.Permissions

val command = Commands.literal("kriscraft")
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
                                1
                            }
                    )
            )
    )