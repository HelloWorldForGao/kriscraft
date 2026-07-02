package io.hwfg.kriscraft.sleepmoss

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.advancements.criterion.ContextAwarePredicate
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import java.util.Optional

data class SleepMossCondition(
    val p0 : Optional<ContextAwarePredicate>,
    val targetTime : Int
) : SimpleCriterionTrigger.SimpleInstance {
    override fun player(): Optional<ContextAwarePredicate> = p0
    companion object{
        val codec: Codec<SleepMossCondition> = RecordCodecBuilder.create { p0 ->
            p0.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(SleepMossCondition::p0),
                Codec.INT.fieldOf("targetTime").forGetter(SleepMossCondition::targetTime)
            ).apply(p0, ::SleepMossCondition)
        }
    }
}