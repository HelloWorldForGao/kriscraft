package io.hwfg.kriscraft.client

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.Kriscraft
import io.hwfg.kriscraft.client.screen.ConfigScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.Minecraft

class KriscraftClient : ClientModInitializer {

    override fun onInitializeClient() {
        Core.logger.info("Loading client")
        keyInit()
        ClientTickEvents.END_CLIENT_TICK.register { p0 ->
            if (p0.gui.screen() is ConfigScreen) return@register
            //if (openTest.consumeClick()) Minecraft.getInstance().setScreen(screen)
            if (open.consumeClick()) Minecraft.getInstance().setScreenAndShow(ConfigScreen)
        }
        Core.logger.info("Playing the Deltarune Chapter ${Kriscraft.latestDeltarune}")
    }
}
