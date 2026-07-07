package io.hwfg.kriscraft.datagen

import io.hwfg.kriscraft.datagen.advancements.MainLine
import io.hwfg.kriscraft.datagen.advancements.SleepMoss
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.data.advancements.AdvancementProvider

class Datagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()
        pack.addProvider(::Recipe)
        pack.addProvider{p0,p1 ->
            AdvancementProvider(
                p0,
                p1,
                listOf(
                    MainLine(),
                    SleepMoss()
                )
            )
        }
    }
}