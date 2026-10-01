package io.hwfg.kriscraft.client.screen.handlers.impl

import io.hwfg.kriscraft.client.screen.ConfigHandler
import io.hwfg.kriscraft.utils.proceed
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component

object ProceedHandler : ConfigHandler<Float>(){
    override fun shader(p0: Float): Component = Component
        .translatable("kriscraft.ui.proceed")
        .withStyle(ChatFormatting.BLUE)

    override fun left(p0: Float): Float = 1225F

    override fun right(p0: Float) : Float {
        proceed("insert chapter 7 side b")
        return 1225F
    }
}