package io.hwfg.kriscraft.item

import io.hwfg.kriscraft.Core.tryToTrigger
import io.hwfg.kriscraft.mod.burgerEffect
import io.hwfg.kriscraft.mod.eatMossBreadCount
import io.hwfg.kriscraft.mod.eatMossBreadCriterion
import io.hwfg.kriscraft.mod.eatMossBurgerCount
import io.hwfg.kriscraft.mod.eatMossBurgerCriterion
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.Consumables
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect
import net.minecraft.world.level.Level

class MossBurger : Item(
    Properties()
        .food(
            FoodProperties
                .Builder()
                .nutrition(12)
                .saturationModifier(12F)
                .alwaysEdible()
                .build(),
            Consumables
                .defaultFood()
                .onConsume(
                    ApplyStatusEffectsConsumeEffect(
                        MobEffectInstance(
                            burgerEffect,
                            120,
                            1
                        ),
                        1.0F
                    )
                )
                .build()
        )
        .setId(
            ResourceKey
                .create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(
                        "kriscraft",
                        "moss_burger"
                    )
                )
        )
){
    override fun finishUsingItem(itemStack: ItemStack, level: Level, entity: LivingEntity): ItemStack {
        val res = super.finishUsingItem(itemStack, level, entity)
        if (entity !is ServerPlayer) return res
        eatMossBurgerCriterion.trigger(
            entity,
            eatMossBurgerCount
        )
        eatMossBreadCriterion.trigger(
            entity,
            eatMossBreadCount,
            2
        )
        entity.tryToTrigger()
        return res
    }
}