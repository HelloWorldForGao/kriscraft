package io.hwfg.kriscraft.client

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.minecraft.client.KeyMapping
import net.minecraft.resources.Identifier
import org.lwjgl.glfw.GLFW

val keyCategory = KeyMapping.Category.register(
    Identifier.fromNamespaceAndPath("kriscraft","default")
)
val open = KeyMappingHelper.registerKeyMapping(
    KeyMapping(
        "kriscraft.key.open",
        GLFW.GLFW_KEY_0,
        keyCategory
    )
)
fun keyInit() = Unit
