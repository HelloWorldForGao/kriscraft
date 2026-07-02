package io.hwfg.kriscraft.item

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.mobeffect.MossyEffect
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.component.Consumable
import net.minecraft.world.item.component.Consumables
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect

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
                            Core.burgurEffect,
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
)