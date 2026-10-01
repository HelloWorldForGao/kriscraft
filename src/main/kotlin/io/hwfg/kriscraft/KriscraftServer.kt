package io.hwfg.kriscraft

import net.fabricmc.api.DedicatedServerModInitializer

class KriscraftServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        Core.logger.info("Loading server")

        Core.logger.info("Playing the Deltarune Chapter ${Kriscraft.latestDeltarune}")
    }
}