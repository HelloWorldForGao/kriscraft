package io.hwfg.kriscraft.mod

import io.hwfg.kriscraft.utils.classes.CountableCriterion
import io.hwfg.kriscraft.utils.classes.SingleCriterion
import net.minecraft.advancements.triggers.CriterionTrigger
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier


@JvmField
val eatMossCriterion = register(
    Identifier.fromNamespaceAndPath("kriscraft","eat_moss").toString(),
    CountableCriterion()
)
@JvmField
val sleepMossCriterion = register(
    Identifier.fromNamespaceAndPath("kriscraft","sleep_moss").toString(),
    CountableCriterion()
)
@JvmField
val eatMossBreadCriterion = register(
    "kriscraft:eat_moss_bread",
    CountableCriterion()
)
@JvmField
val eatMossBurgerCriterion = register(
    "kriscraft:eat_moss_burger",
    CountableCriterion()
)
@JvmField val negHealCriteria = register(
    "kriscraft:neg_heal",
    CountableCriterion()
)

@JvmField val snowgraveDeathCriteria = register(
    "kriscraft:snowgrave_death",
    SingleCriterion()
)

private fun <T : CriterionTrigger<*>>register(name: String, criterion: T): T{
    return Registry.register(
        BuiltInRegistries.TRIGGER_TYPES,
        name,
        criterion
    )
}

fun criterionInit() = Unit