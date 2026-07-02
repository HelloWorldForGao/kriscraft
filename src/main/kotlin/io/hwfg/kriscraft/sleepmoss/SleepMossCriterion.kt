package io.hwfg.kriscraft.sleepmoss

import com.mojang.serialization.Codec
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import net.minecraft.server.level.ServerPlayer

class SleepMossCriterion : SimpleCriterionTrigger<SleepMossCondition>() {
    override fun codec(): Codec<SleepMossCondition> = SleepMossCondition.codec
    fun trigger(player : ServerPlayer,time : Int){
        this.trigger(player){p0 ->
            time >= p0.targetTime
        }
    }
}