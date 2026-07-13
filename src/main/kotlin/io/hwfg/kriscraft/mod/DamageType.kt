package io.hwfg.kriscraft.mod

import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey

val negDamage = ResourceKey.create(
    Registries.DAMAGE_TYPE,
    Identifier.fromNamespaceAndPath(
        "kriscraft",
        "negative"
    )
)