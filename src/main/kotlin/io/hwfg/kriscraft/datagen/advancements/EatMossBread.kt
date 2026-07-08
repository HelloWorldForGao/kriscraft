package io.hwfg.kriscraft.datagen.advancements

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.eatMossBreadCriterion
import io.hwfg.kriscraft.mossBread
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.core.HolderLookup
import net.minecraft.data.advancements.AdvancementSubProvider
import net.minecraft.network.chat.Component
import java.util.Optional
import java.util.function.Consumer

class EatMossBread : AdvancementSubProvider{
    companion object{
        lateinit var moss_bread1 : AdvancementHolder
        lateinit var moss_bread10 : AdvancementHolder
        lateinit var moss_bread100 : AdvancementHolder
        lateinit var moss_bread30000 : AdvancementHolder
    }
    override fun generate(
        registries: HolderLookup.Provider,
        output: Consumer<AdvancementHolder>
    ) {
        moss_bread1 = Advancement.Builder
            .advancement()
            .parent(SleepMoss.sleep1)
            .display(
                mossBread,
                Component.translatable("kriscraft.advancement.eatmossbread1.title"),
                Component.translatable("kriscraft.advancement.eatmossbread1.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "are_you_using_betteradvancement",
                eatMossBreadCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        1
                    )
                )
            )
            .save(
                output,
                "kriscraft_moss_bread1"
            )
        moss_bread10 = Advancement.Builder
            .advancement()
            .parent(moss_bread1)
            .display(
                mossBread,
                Component.translatable("kriscraft.advancement.eatmossbread10.title"),
                Component.translatable("kriscraft.advancement.eatmossbread10.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true
            )
            .addCriterion(
                "are_you_using_betteradvancement",
                eatMossBreadCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        10
                    )
                )
            )
            .save(
                output,
                "kriscraft_moss_bread10"
            )
        moss_bread100 = Advancement.Builder
            .advancement()
            .parent(moss_bread10)
            .display(
                mossBread,
                Component.translatable("kriscraft.advancement.eatmossbread100.title"),
                Component.translatable("kriscraft.advancement.eatmossbread100.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                true
            )
            .addCriterion(
                "are_you_using_betteradvancement",
                eatMossBreadCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        100
                    )
                )
            )
            .save(
                output,
                "kriscraft_moss_bread100"
            )
        moss_bread30000 = Advancement.Builder
            .advancement()
            .parent(moss_bread100)
            .display(
                mossBread,
                Component.translatable("kriscraft.advancement.eatmossbread30000.title"),
                Component.translatable("kriscraft.advancement.eatmossbread30000.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "are_you_using_betteradvancement",
                eatMossBreadCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        30000
                    )
                )
            )
            .save(
                output,
                "kriscraft_moss_bread30000"
            )
    }
}