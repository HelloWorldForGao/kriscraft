package io.hwfg.kriscraft.utils.classes

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.advancements.criterion.ContextAwarePredicate
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import java.util.Optional

data class CountableCondition(
    val p0 : Optional<ContextAwarePredicate>,
    val time : Int
) : SimpleCriterionTrigger.SimpleInstance{
    override fun player(): Optional<ContextAwarePredicate> = p0
    companion object {
        val codec : Codec<CountableCondition> = RecordCodecBuilder.create { p0 ->
            p0.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(CountableCondition::p0),
                Codec.INT.fieldOf("time").forGetter(CountableCondition::time)
            ).apply(p0,::CountableCondition)
        }
    }
}