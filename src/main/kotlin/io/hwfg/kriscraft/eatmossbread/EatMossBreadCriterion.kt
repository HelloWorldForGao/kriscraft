package io.hwfg.kriscraft.eatmossbread

import com.mojang.serialization.Codec
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import net.minecraft.server.level.ServerPlayer

class EatMossBreadCriterion : SimpleCriterionTrigger<EatMossBreadCondition>() {
    override fun codec(): Codec<EatMossBreadCondition> = EatMossBreadCondition.CODEC
    fun trigger(player : ServerPlayer,totalTime : Int){
        this.trigger(player){p0 ->
            p0.check(totalTime)
        }
    }
}