package io.hwfg.kriscraft.client.screen

import net.minecraft.network.chat.Component

abstract class ConfigHandler<A> {
    abstract fun shader(p0 : A) : Component
    abstract fun left(p0 : A) : A
    abstract fun right(p0 : A) : A
}