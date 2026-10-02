package io.hwfg.kriscraft.utils.classes

import com.mojang.serialization.Codec
import io.hwfg.kriscraft.utils.addAndGet
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.advancements.triggers.SimpleCriterionTrigger
import net.minecraft.server.level.ServerPlayer

class CountableCriterion : SimpleCriterionTrigger<CountableCondition>() {
    override fun codec(): Codec<CountableCondition> = CountableCondition.codec
    fun trigger(player: ServerPlayer, time : Int) = super.trigger(player){ p0 ->
        time >= p0.time
    }
    fun trigger(player: ServerPlayer, attachment : AttachmentType<Int>, num : Int = 1) = trigger(
        player,
        player.addAndGet(attachment,num)
    )
}