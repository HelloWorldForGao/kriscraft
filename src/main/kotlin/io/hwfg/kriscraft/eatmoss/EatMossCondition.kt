package io.hwfg.kriscraft.eatmoss

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.advancements.criterion.ContextAwarePredicate
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import java.util.*

data class EatMossCondition(
    val p0 : Optional<ContextAwarePredicate>,
    val requireTime : Int
) : SimpleCriterionTrigger.SimpleInstance {
    override fun player(): Optional<ContextAwarePredicate> = p0
    companion object{
        var CODEC : Codec<EatMossCondition> = RecordCodecBuilder.create { p0 ->
            p0.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(EatMossCondition::p0),
                Codec.INT.fieldOf("requiredTime").forGetter(EatMossCondition::requireTime)
            ).apply(p0,::EatMossCondition)
        }
    }
    fun check(totalTimes : Int) : Boolean = totalTimes >= requireTime
}