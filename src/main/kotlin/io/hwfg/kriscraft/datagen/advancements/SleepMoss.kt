package io.hwfg.kriscraft.datagen.advancements

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.datagen.advancements.MainLine.Companion.krisRoot
import io.hwfg.kriscraft.mod.mossBedBlock
import io.hwfg.kriscraft.mod.sleepMossCriterion
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.core.HolderLookup
import net.minecraft.data.advancements.AdvancementSubProvider
import net.minecraft.network.chat.Component
import java.util.*
import java.util.function.Consumer

class SleepMoss : AdvancementSubProvider {
    companion object {
        lateinit var sleep1 : AdvancementHolder
        lateinit var sleep5 : AdvancementHolder
        lateinit var sleep31 : AdvancementHolder
        lateinit var sleep365 : AdvancementHolder
    }

    override fun generate(
        registries: HolderLookup.Provider,
        consumer: Consumer<AdvancementHolder>
    ) {
        sleep1 = Advancement.Builder.advancement()
            .parent(krisRoot)
            .display(
                mossBedBlock.asItem(),
                Component.translatable("kriscraft.advancement.sleep_moss1.title"),
                Component.translatable("kriscraft.advancement.sleep_moss1.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "default",
                sleepMossCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        1
                    )
                )
            )
            .save(
                consumer,
                "kriscraft:sleep1"
            )
        sleep5 = Advancement.Builder.advancement()
            .parent(sleep1)
            .display(
                mossBedBlock.asItem(),
                Component.translatable("kriscraft.advancement.sleep_moss5.title"),
                Component.translatable("kriscraft.advancement.sleep_moss5.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "default",
                sleepMossCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        5
                    )
                )
            )
            .save(
                consumer,
                "kriscraft:sleep5"
            )
        sleep31 = Advancement.Builder.advancement()
            .parent(sleep5)
            .display(
                mossBedBlock.asItem(),
                Component.translatable("kriscraft.advancement.sleep_moss31.title"),
                Component.translatable("kriscraft.advancement.sleep_moss31.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                true
            )
            .addCriterion(
                "default",
                sleepMossCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        31
                    )
                )
            )
            .save(
                consumer,
                "kriscraft:sleep31"
            )
        sleep365 = Advancement.Builder.advancement()
            .parent(sleep31)
            .display(
                mossBedBlock.asItem(),
                Component.translatable("kriscraft.advancement.sleep_moss365.title"),
                Component.translatable("kriscraft.advancement.sleep_moss365.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "default",
                sleepMossCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        365
                    )
                )
            )
            .save(
                consumer,
                "kriscraft:sleep365"
            )
    }
}