package io.hwfg.kriscraft

import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.resources.Identifier

val eatMossCriterion = CriteriaTriggers.register(
    Identifier.fromNamespaceAndPath("kriscraft","eat_moss").toString(),
    Core.CountableCriterion()
)
val sleepMossCriterion = CriteriaTriggers.register(
    Identifier.fromNamespaceAndPath("kriscraft","sleep_moss").toString(),
    Core.CountableCriterion()
)
val eatMossBreadCriterion = CriteriaTriggers.register(
    "kriscraft:eat_moss_bread",
    Core.CountableCriterion()
)
val eatMossBurgerCriterion = CriteriaTriggers.register(
    "kriscraft:eat_moss_burger",
    Core.CountableCriterion()
)

fun criterionInit() = Unit