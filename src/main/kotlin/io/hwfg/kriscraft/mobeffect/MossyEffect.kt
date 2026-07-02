package io.hwfg.kriscraft.mobeffect

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.Core.addAndGet
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player

class MossyEffect : MobEffect(MobEffectCategory.BENEFICIAL,0x00FF00) {
    override fun shouldApplyEffectTickThisTick(i: Int, j: Int): Boolean = true
    override fun applyEffectTick(
        serverLevel: ServerLevel,
        livingEntity: LivingEntity,
        i: Int
    ): Boolean {
        if (livingEntity is Player) livingEntity.heal(0.1F)
        return super.applyEffectTick(serverLevel, livingEntity, i)
    }
}