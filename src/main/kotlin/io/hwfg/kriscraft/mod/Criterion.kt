package io.hwfg.kriscraft.mod

import io.hwfg.kriscraft.Core
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.resources.Identifier

@JvmField
val eatMossCriterion = CriteriaTriggers.register(
    Identifier.fromNamespaceAndPath("kriscraft","eat_moss").toString(),
    Core.CountableCriterion()
)
@JvmField
val sleepMossCriterion = CriteriaTriggers.register(
    Identifier.fromNamespaceAndPath("kriscraft","sleep_moss").toString(),
    Core.CountableCriterion()
)
@JvmField
val eatMossBreadCriterion = CriteriaTriggers.register(
    "kriscraft:eat_moss_bread",
    Core.CountableCriterion()
)
@JvmField
val eatMossBurgerCriterion = CriteriaTriggers.register(
    "kriscraft:eat_moss_burger",
    Core.CountableCriterion()
)
@JvmField val negHealCriteria = CriteriaTriggers.register(
    "kriscraft:neg_heal",
    Core.CountableCriterion()
)

@JvmField val negHealDeathCriteria = CriteriaTriggers.register(
    "kriscraft:neg_heal_death",
    Core.SingleCriterion()
)

@JvmField val snowgraveDeathCriteria = CriteriaTriggers.register(
    "kriscraft:snowgrave_death",
    Core.SingleCriterion()
)

fun criterionInit() = Unit