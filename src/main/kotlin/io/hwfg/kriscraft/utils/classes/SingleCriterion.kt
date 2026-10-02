package io.hwfg.kriscraft.utils.classes

import com.mojang.serialization.Codec
import net.minecraft.advancements.triggers.SimpleCriterionTrigger
import net.minecraft.server.level.ServerPlayer

class SingleCriterion : SimpleCriterionTrigger<SingleCondition>(){
    override fun codec(): Codec<SingleCondition> = SingleCondition.codec
    fun trigger(player: ServerPlayer) = super.trigger(player) { true }
}