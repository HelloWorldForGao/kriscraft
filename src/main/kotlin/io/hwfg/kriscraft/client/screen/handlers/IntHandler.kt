package io.hwfg.kriscraft.client.screen.handlers

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import io.hwfg.kriscraft.client.screen.ConfigHandler
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component

object IntHandler : ConfigHandler<Int>() {
    override fun shader(p0: Int): Component {
        return if (p0 > 0) Component.literal(p0.toString()).withStyle(ChatFormatting.GREEN)
        else if (p0 == 0) Component.literal("0")
        else Component.literal(p0.toString()).withStyle(ChatFormatting.RED)
    }

    override fun left(p0: Int): Int = p0 - 1

    override fun right(p0: Int): Int = p0 + 1
}