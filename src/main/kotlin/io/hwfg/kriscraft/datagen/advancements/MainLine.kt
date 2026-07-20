package io.hwfg.kriscraft.datagen.advancements

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.mod.eatMossCriterion
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.core.HolderLookup
import net.minecraft.data.advancements.AdvancementSubProvider
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.world.item.Items
import java.util.Optional
import java.util.function.Consumer

class MainLine : AdvancementSubProvider  {
    companion object{
        lateinit var krisRoot : AdvancementHolder
        lateinit var kris10 : AdvancementHolder
        lateinit var kris100 : AdvancementHolder
        lateinit var kris30000 : AdvancementHolder
    }

    override fun generate(
        registries: HolderLookup.Provider,
        output: Consumer<AdvancementHolder>
    ) {
        krisRoot = Advancement.Builder.advancement()
            .display(
                Items.MOSS_BLOCK,
                Component.translatable("kriscraft.adv.kris_root.title"),
                Component.translatable("kriscraft.adv.kris_root.description"),
                Identifier.fromNamespaceAndPath(
                    "kriscraft",
                    "gui/advancements/backgrounds/moss"
                ),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "default",
                eatMossCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        1
                    )
                )
            )
            .save(
                output,
                "kriscraft:root"
            )
        kris10 = Advancement.Builder.advancement()
            .parent(krisRoot)
            .display(
                Items.MOSS_BLOCK,
                Component.translatable("kriscraft.adv.kris10.title"),
                Component.translatable("kriscraft.adv.kris10.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "default",
                eatMossCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        10
                    )
                )
            )
            .save(
                output,
                "kriscraft:moss10"
            )
        kris100 = Advancement.Builder.advancement()
            .parent(kris10)
            .display(
                Items.MOSS_BLOCK,
                Component.translatable("kriscraft.adv.kris100.title"),
                Component.translatable("kriscraft.adv.kris100.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                true
            )
            .addCriterion(
                "default",
                eatMossCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        100
                    )
                )
            )
            .save(
                output,
                "kriscraft:moss100"
            )
        kris30000 = Advancement.Builder.advancement()
            .parent(kris100)
            .display(
                Items.MOSS_BLOCK,
                Component.translatable("kriscraft.adv.kris30000.title"),
                Component.translatable("kriscraft.adv.kris30000.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "default",
                eatMossCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        30000
                    )
                )
            )
            .save(
                output,
                "kriscraft:moss30000"
            )
    }
}