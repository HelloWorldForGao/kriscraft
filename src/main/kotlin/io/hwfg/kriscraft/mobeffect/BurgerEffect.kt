package io.hwfg.kriscraft.mobeffect

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
            livingEntity.displayClientMessage(
                Component.translatable("kriscraft.zhizhanzhishang")
                    .withStyle(ChatFormatting.RED)
                    .withStyle(ChatFormatting.BOLD),
                true
            )
            livingEntity.heal(0.3F)
        }
        return super.applyEffectTick(serverLevel, livingEntity, i)
    }
}