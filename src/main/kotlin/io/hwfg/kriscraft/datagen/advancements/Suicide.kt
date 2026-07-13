package io.hwfg.kriscraft.datagen.advancements

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.mod.negHealCriteria
import io.hwfg.kriscraft.mod.negHealDeathCriteria
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.core.HolderLookup
import net.minecraft.data.advancements.AdvancementSubProvider
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Items
import java.util.Optional
import java.util.function.Consumer

class Suicide : AdvancementSubProvider {
    companion object{
        lateinit var neg_heal1 : AdvancementHolder
        lateinit var neg_heal10 : AdvancementHolder
        lateinit var neg_heal_death : AdvancementHolder
    }
    override fun generate(
        registries: HolderLookup.Provider,
        output: Consumer<AdvancementHolder>
    ) {
        neg_heal1 = Advancement.Builder
            .advancement()
            .parent(MainLine.krisRoot)
            .display(
                Items.POTION,
                Component.translatable("kriscraft.advancement.neg_heal1.title"),
                Component.translatable("kriscraft.advancement.neg_heal1.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                true
            )
            .addCriterion(
                "how_can_you_see_this",
                negHealCriteria.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        1
                    )
                )
            )
            .save(output,"kriscraft:neg_heal1")
        neg_heal10 = Advancement.Builder
            .advancement()
            .parent(neg_heal1)
            .display(
                Items.POTION,
                Component.translatable("kriscraft.advancement.neg_heal10.title"),
                Component.translatable("kriscraft.advancement.neg_heal10.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                true
            )
            .addCriterion(
                "how_can_you_see_this",
                negHealCriteria.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        10
                    )
                )
            )
            .save(output,"kriscraft:neg_heal10")
        neg_heal_death = Advancement.Builder
            .advancement()
            .parent(neg_heal1)
            .display(
                Items.POTION,
                Component.translatable("kriscraft.advancement.neg_heal_death.title"),
                Component.translatable("kriscraft.advancement.neg_heal_death.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "how_can_you_see_this",
                negHealDeathCriteria.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        1
                    )
                )
            )
            .save(output,"kriscraft:neg_heal_death")
    }
}