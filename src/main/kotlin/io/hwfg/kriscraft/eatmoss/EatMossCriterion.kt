package io.hwfg.kriscraft.eatmoss

import com.mojang.serialization.Codec
import net.minecraft.advancements.CriterionTrigger
import net.minecraft.advancements.criterion.SimpleCriterionTrigger
import net.minecraft.server.PlayerAdvancements
import net.minecraft.server.level.ServerPlayer

class EatMossCriterion : SimpleCriterionTrigger<EatMossCondition>() {
    override fun codec(): Codec<EatMossCondition> = EatMossCondition.CODEC
    fun trigger(player : ServerPlayer,totalTime : Int){
        this.trigger(player){p0 ->
            p0.check(totalTime)
        }
    }
}