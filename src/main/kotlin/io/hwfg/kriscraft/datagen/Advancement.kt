package io.hwfg.kriscraft.datagen

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.eatmoss.EatMossCondition
import io.hwfg.kriscraft.sleepmoss.SleepMossCondition
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.advancements.criterion.UsingItemTrigger
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
            .addCriterion("krisroot", Core.eatMossCriterion.createCriterion(EatMossCondition(Optional.empty(), 5)))
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
            .addCriterion("kris15", Core.eatMossCriterion.createCriterion(EatMossCondition(Optional.empty(), 15)))
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
            .addCriterion("kris300", Core.eatMossCriterion.createCriterion(EatMossCondition(Optional.empty(), 300)))
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
            .addCriterion("kris30000", Core.eatMossCriterion.createCriterion(EatMossCondition(Optional.empty(), 30000)))
            .save(consumer, Identifier.fromNamespaceAndPath("kriscraft","kris30000").toString())
        val sleepMoss = Advancement.Builder.advancement()
            .parent(krisRoot)
            .display(
                Core.mossBedBlock.asItem(),
                Component.translatable("kriscraft.advancement.sleepmoss.title"),
                Component.translatable("kriscraft.advancement.sleepmoss.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true
            )
            .addCriterion("sleepmoss",Core.sleepMossCriterion.createCriterion(SleepMossCondition(Optional.empty(),1)))
            .save(consumer,Identifier.fromNamespaceAndPath("kriscraft","sleepmoss").toString())
        val sleepMoss5 = Advancement.Builder.advancement()
            .parent(sleepMoss)
            .display(
                Core.mossBedBlock.asItem(),
                Component.translatable("kriscraft.advancement.sleepmoss5.title"),
                Component.translatable("kriscraft.advancement.sleepmoss5.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true
            )
            .addCriterion("sleepmoss5",Core.sleepMossCriterion.createCriterion(SleepMossCondition(Optional.empty(),5)))
            .save(consumer,Identifier.fromNamespaceAndPath("kriscraft","sleepmoss5").toString())
        val sleepMoss31 = Advancement.Builder.advancement()
            .parent(sleepMoss5)
            .display(
                Core.mossBedBlock.asItem(),
                Component.translatable("kriscraft.advancement.sleepmoss31.title"),
                Component.translatable("kriscraft.advancement.sleepmoss31.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                true
            )
            .addCriterion("sleepmoss31",Core.sleepMossCriterion.createCriterion(SleepMossCondition(Optional.empty(),31)))
            .save(consumer,Identifier.fromNamespaceAndPath("kriscraft","sleepmoss31").toString())
        val sleepMoss365 = Advancement.Builder.advancement()
            .parent(sleepMoss31)
            .display(
                Core.mossBedBlock.asItem(),
                Component.translatable("kriscraft.advancement.sleepmoss365.title"),
                Component.translatable("kriscraft.advancement.sleepmoss365.description"),
                null,
                AdvancementType.TASK,
                true,
                true,
                true
            )
            .addCriterion("sleepmoss365",Core.sleepMossCriterion.createCriterion(SleepMossCondition(Optional.empty(),365)))
            .save(consumer,Identifier.fromNamespaceAndPath("kriscraft","sleepmoss365").toString())
    }
}