package io.hwfg.kriscraft.client

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.Kriscraft
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.Minecraft

class KriscraftClient : ClientModInitializer {

    override fun onInitializeClient() {
        Core.logger.info("Loading client")
        keyInit()
        screenInit()
        ClientTickEvents.END_CLIENT_TICK.register { p0 ->
            if (p0.screen is ConfigScreen) return@register
            if (open.consumeClick()) Minecraft.getInstance().setScreen(screen)
        }
        Core.logger.info("Playing the Deltarune Chapter ${Kriscraft.latestDeltarune}")
    }
}
