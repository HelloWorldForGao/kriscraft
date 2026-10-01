package io.hwfg.kriscraft.mobeffect

import io.hwfg.kriscraft.config.Config
import io.hwfg.kriscraft.utils.healFromMoss
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity

class MossyEffect : MobEffect(MobEffectCategory.BENEFICIAL,0x00FF00) {
    override fun shouldApplyEffectTickThisTick(i: Int, j: Int): Boolean = true
    override fun applyEffectTick(
        serverLevel: ServerLevel,
        livingEntity: LivingEntity,
        i: Int
    ): Boolean {
        if (livingEntity is ServerPlayer) {
            val scale = Config.forceGet<Float>("product_scale")
            livingEntity.healFromMoss(scale * 0.1F)
        }
        return super.applyEffectTick(serverLevel, livingEntity, i)
    }
}