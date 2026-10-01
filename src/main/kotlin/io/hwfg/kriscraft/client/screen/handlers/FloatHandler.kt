package io.hwfg.kriscraft.client.screen.handlers

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.FloatArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import io.hwfg.kriscraft.client.screen.ConfigHandler
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component

object FloatHandler : ConfigHandler<Float>() {
    override fun shader(p0: Float): Component {
        return if (p0 > 0F) Component.literal(p0.toString()).withStyle(ChatFormatting.GREEN)
        else if (p0 == 0F) Component.literal("0")
        else Component.literal(p0.toString()).withStyle(ChatFormatting.RED)
    }

    override fun left(p0: Float): Float = p0 - 1

    override fun right(p0: Float): Float = p0 + 1
}