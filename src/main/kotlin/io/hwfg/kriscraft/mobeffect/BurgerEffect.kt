package io.hwfg.kriscraft.mobeffect

import io.hwfg.kriscraft.Core.mossHeal
import io.hwfg.kriscraft.config.configures
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity

class BurgerEffect : MobEffect(MobEffectCategory.BENEFICIAL,0x00FF00) {
    override fun shouldApplyEffectTickThisTick(i: Int, j: Int): Boolean = true
    override fun applyEffectTick(
        serverLevel: ServerLevel,
        livingEntity: LivingEntity,
        i: Int
    ): Boolean {
        if (livingEntity is ServerPlayer){
            livingEntity.sendOverlayMessage(
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars1")
                    .withStyle(ChatFormatting.RED)
                    .withStyle(ChatFormatting.BOLD)
            )
            val scale = configures["moss_product_heal_base_scale"]?.asInt ?: 0
            livingEntity.mossHeal(scale * 0.2F)
        }
        return super.applyEffectTick(serverLevel, livingEntity, i)
    }
}