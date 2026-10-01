package io.hwfg.kriscraft.mod

import io.hwfg.kriscraft.utils.classes.CountableCriterion
import io.hwfg.kriscraft.utils.classes.SingleCriterion
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.resources.Identifier

@JvmField
val eatMossCriterion = CriteriaTriggers.register(
    Identifier.fromNamespaceAndPath("kriscraft","eat_moss").toString(),
    CountableCriterion()
)
@JvmField
val sleepMossCriterion = CriteriaTriggers.register(
    Identifier.fromNamespaceAndPath("kriscraft","sleep_moss").toString(),
    CountableCriterion()
)
@JvmField
val eatMossBreadCriterion = CriteriaTriggers.register(
    "kriscraft:eat_moss_bread",
    CountableCriterion()
)
@JvmField
val eatMossBurgerCriterion = CriteriaTriggers.register(
    "kriscraft:eat_moss_burger",
    CountableCriterion()
)
@JvmField val negHealCriteria = CriteriaTriggers.register(
    "kriscraft:neg_heal",
    CountableCriterion()
)

@JvmField val snowgraveDeathCriteria = CriteriaTriggers.register(
    "kriscraft:snowgrave_death",
    SingleCriterion()
)

fun criterionInit() = Unit