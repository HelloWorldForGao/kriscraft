package io.hwfg.kriscraft.mod

import io.hwfg.kriscraft.mobeffect.BurgerEffect
import io.hwfg.kriscraft.mobeffect.MossyEffect
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.effect.MobEffect

@JvmField
val mossyEffect : Holder<MobEffect> = Registry.registerForHolder(
    BuiltInRegistries.MOB_EFFECT,
    ResourceKey.create(
        Registries.MOB_EFFECT,
        Identifier.fromNamespaceAndPath(
            "kriscraft",
            "mossy_effect"
        )
    ),
    MossyEffect()
)
@JvmField
val burgerEffect : Holder<MobEffect> = Registry.registerForHolder(
    BuiltInRegistries.MOB_EFFECT,
    ResourceKey.create(
        Registries.MOB_EFFECT,
        Identifier.fromNamespaceAndPath("kriscraft","burger_effect")
    ),
    BurgerEffect()
)

fun effectInit() = Unit