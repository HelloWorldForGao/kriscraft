package io.hwfg.kriscraft.client

import io.hwfg.kriscraft.config.ConfigScreen
import io.hwfg.kriscraft.config.open
import io.hwfg.kriscraft.config.screen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.Minecraft

class KriscraftClient : ClientModInitializer {

    override fun onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register { p0 ->
            if (p0.screen is ConfigScreen) return@register
            if (open.consumeClick()) Minecraft.getInstance().setScreen(screen)
        }
    }
}
