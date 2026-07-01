package io.hwfg.kriscraft.datagen

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.eatmoss.EatMossCondition
import io.hwfg.kriscraft.eatmoss.EatMossCriterion
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.world.item.Items
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class Advancement(
    val output : FabricDataOutput,
    val registryLookup : CompletableFuture<HolderLookup.Provider>
) : FabricAdvancementProvider(output,registryLookup) {
    override fun generateAdvancement(
        registryLookup: HolderLookup.Provider,
        consumer: Consumer<AdvancementHolder>
    ) {
        val krisRoot = Advancement.Builder.advancement()
            .display(
                Items.MOSS_BLOCK,
                Component.translatable("kriscraft.advancement.krisroot.title"),
                Component.translatable("kriscraft.advancement.krisroot.description"),
                Identifier.fromNamespaceAndPath("kriscraft","gui/advancements/backgrounds/moss"),
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion("krisroot", Core.EAT_MOSS_CRITERION.createCriterion(EatMossCondition(Optional.empty(), 5)))
            .save(consumer, Identifier.fromNamespaceAndPath("kriscraft","krisroot").toString())
        val kris15 = Advancement.Builder.advancement()
            .parent(krisRoot)
            .display(
                Items.MOSS_BLOCK,
                Component.translatable("kriscraft.advancement.kris15.title"),
                Component.translatable("kriscraft.advancement.kris15.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion("kris15", Core.EAT_MOSS_CRITERION.createCriterion(EatMossCondition(Optional.empty(), 15)))
            .save(consumer, Identifier.fromNamespaceAndPath("kriscraft","kris15").toString())
        val kris300 = Advancement.Builder.advancement()
            .parent(kris15)
            .display(
                Items.MOSS_BLOCK,
                Component.translatable("kriscraft.advancement.kris300.title"),
                Component.translatable("kriscraft.advancement.kris300.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                true
            )
            .addCriterion("kris300", Core.EAT_MOSS_CRITERION.createCriterion(EatMossCondition(Optional.empty(), 300)))
            .save(consumer, Identifier.fromNamespaceAndPath("kriscraft","kris300").toString())
        val kris30000 = Advancement.Builder.advancement()
            .parent(kris300)
            .display(
                Items.MOSS_BLOCK,
                Component.translatable("kriscraft.advancement.kris30000.title"),
                Component.translatable("kriscraft.advancement.kris30000.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion("kris30000", Core.EAT_MOSS_CRITERION.createCriterion(EatMossCondition(Optional.empty(), 30000)))
            .save(consumer, Identifier.fromNamespaceAndPath("kriscraft","kris30000").toString())
    }
}