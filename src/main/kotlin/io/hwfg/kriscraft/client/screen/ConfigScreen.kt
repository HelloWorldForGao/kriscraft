package io.hwfg.kriscraft.client.screen

import io.hwfg.kriscraft.Kriscraft
import io.hwfg.kriscraft.config.Config
import io.hwfg.kriscraft.utils.upload
import io.hwfg.kriscraft.utils.getData
import io.hwfg.kriscraft.utils.toJson
import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.network.chat.Component

object ConfigScreen : DeltaruneLike(
    Component.translatable("kriscraft.name"),
    Component.translatable("kriscraft.description"),
    Config.subConfigs,
    Kriscraft.kriscraftConfigFile,
    shiftQuitEvent = { upload(Config.subConfigs.getData()) }
){
    fun extraTip() : Component{
        if (Minecraft.getInstance().connection == null || Minecraft.getInstance().isLocalServer) return Component.literal("")
        val localValue = (Config.subConfigs[ptr].value ?: "").toJson()
        val serverValue = (Config.serverConfig[ptr].value ?: "").toJson()
        if (localValue != serverValue) return Component.translatable(
            "kriscraft.different",
            localValue,
            serverValue
        ).withStyle(ChatFormatting.BLUE)
        return Component.translatable(
            "kriscraft.same"
        ).withStyle(ChatFormatting.YELLOW)
    }
    override fun extractRenderState(graphics: GuiGraphicsExtractor, mouseX: Int, mouseY: Int, a: Float) {
        super.extractRenderState(graphics, mouseX, mouseY, a)
        graphics.text(
            Minecraft.getInstance().font,
            extraTip(),
            100,
            calcY(8),
            0xFFFFFFFF.toInt(),
            false
        )
    }
}