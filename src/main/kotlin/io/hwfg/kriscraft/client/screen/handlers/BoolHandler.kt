package io.hwfg.kriscraft.client.screen.handlers

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.BoolArgumentType
import io.hwfg.kriscraft.client.screen.ConfigHandler
import net.minecraft.network.chat.Component

object BoolHandler : ConfigHandler<Boolean>() {
    override fun shader(p0: Boolean): Component {
        if (p0) return Component.translatable("kriscraft.true")
        return Component.translatable("kriscraft.false")
    }

    override fun left(p0: Boolean): Boolean = !p0

    override fun right(p0: Boolean): Boolean = !p0
}