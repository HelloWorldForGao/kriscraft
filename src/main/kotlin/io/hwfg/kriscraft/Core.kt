package io.hwfg.kriscraft

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Core {
    val logger: Logger = LoggerFactory.getLogger("KrisCraft")

    fun init() = Unit

    operator fun <A> Set<A>.get(index: Int): A? {
        this.forEachIndexed { i, a ->
            if (i == index) return a
        }
        return null
    }
    operator fun <K,V> Map<K,V>.get(index : Int) : Map.Entry<K,V>? = this.entries[index]

}