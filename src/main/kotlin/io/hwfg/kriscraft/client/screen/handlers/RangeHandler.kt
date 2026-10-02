package io.hwfg.kriscraft.client.screen.handlers

import io.hwfg.kriscraft.client.screen.ConfigHandler

abstract class RangeHandler : ConfigHandler<Float>(){
    abstract val max : Int
    abstract val min : Int
    override fun left(p0: Float): Float {
        return if (p0 > min) p0 - 1
        else p0
    }

    override fun right(p0: Float): Float {
        return if (p0 < max) p0 + 1
        else p0
    }
}