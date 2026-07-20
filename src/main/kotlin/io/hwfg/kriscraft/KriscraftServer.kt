package io.hwfg.kriscraft

import net.fabricmc.api.DedicatedServerModInitializer

class KriscraftServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        Core.logger.info("Loading server")
        Core.logger.info("Seems nothing for the server needs to load")
        Core.logger.info("Reviewing the Deltarune Chapter ${Kriscraft.latestDeltarune - 1}")
    }
}