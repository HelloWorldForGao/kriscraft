package io.hwfg.kriscraft.eatmossbread

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.advancements.criterion.ContextAwarePredicate
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import java.util.Optional

data class EatMossBreadCondition(
    val p0 : Optional<ContextAwarePredicate>,
    val requireTime : Int
) : SimpleCriterionTrigger.SimpleInstance {
    override fun player(): Optional<ContextAwarePredicate> = p0
    companion object{
        var CODEC : Codec<EatMossBreadCondition> = RecordCodecBuilder.create { p0 ->
            p0.group(
                ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(EatMossBreadCondition::p0),
                Codec.INT.fieldOf("requiredTime").forGetter(EatMossBreadCondition::requireTime)
            ).apply(p0,::EatMossBreadCondition)
        }
    }
    fun check(totalTimes : Int) : Boolean = totalTimes >= requireTime
}