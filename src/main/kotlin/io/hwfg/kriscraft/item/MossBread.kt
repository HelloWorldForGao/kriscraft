package io.hwfg.kriscraft.item

import io.hwfg.kriscraft.utils.negHealTrigger
import io.hwfg.kriscraft.mod.eatMossBreadCount
import io.hwfg.kriscraft.mod.eatMossBreadCriterion
import io.hwfg.kriscraft.mod.mossyEffect
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

class MossBread : Item(
    Properties()
        .food(
            FoodProperties
                .Builder()
                .nutrition(8)
                .saturationModifier(6F)
                .alwaysEdible()
                .build(),
            Consumables
                .defaultFood()
                .onConsume(
                    ApplyStatusEffectsConsumeEffect(
                        MobEffectInstance(
                            mossyEffect,
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
                        "moss_bread"
                    )
                )
        )
){
    override fun finishUsingItem(
        p0: ItemStack,
        p1: Level,
        p2: LivingEntity
    ): ItemStack {
        if (p2 !is ServerPlayer)return super.finishUsingItem(p0, p1, p2)
        eatMossBreadCriterion.trigger(
            p2,
            eatMossBreadCount
        )
        p2.negHealTrigger(true)
        return super.finishUsingItem(p0, p1, p2)
    }
}