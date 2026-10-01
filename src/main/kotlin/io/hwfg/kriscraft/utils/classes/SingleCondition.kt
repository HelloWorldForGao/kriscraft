package io.hwfg.kriscraft.utils.classes

import com.mojang.serialization.Codec
import net.minecraft.advancements.criterion.ContextAwarePredicate
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import java.util.Optional

data class SingleCondition(val p0 : Optional<ContextAwarePredicate>) : SimpleCriterionTrigger.SimpleInstance{
    override fun player(): Optional<ContextAwarePredicate> = p0
    companion object{
        val codec: Codec<SingleCondition> = ContextAwarePredicate.CODEC.optionalFieldOf("player")
            .xmap(::SingleCondition, SingleCondition::player)
            .codec()
    }
}