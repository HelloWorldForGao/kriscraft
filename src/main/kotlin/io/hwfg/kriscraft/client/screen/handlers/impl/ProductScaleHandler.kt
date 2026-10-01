package io.hwfg.kriscraft.client.screen.handlers.impl

import io.hwfg.kriscraft.client.screen.handlers.RangeHandler
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component

object ProductScaleHandler : RangeHandler() {
    override fun shader(p0: Float): Component{
        return if (p0 > 0) Component.literal(p0.toString()).withStyle(ChatFormatting.GREEN)
        else if (p0 == 0F) Component.literal(p0.toString())
        else Component.literal(p0.toString()).withStyle(ChatFormatting.RED)
    }
    override val max: Int = 3
    override val min: Int = -3
}