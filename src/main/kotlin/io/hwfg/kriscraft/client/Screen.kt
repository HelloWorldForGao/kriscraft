package io.hwfg.kriscraft.client

import com.google.gson.JsonElement
import io.hwfg.kriscraft.config.configures
import io.hwfg.kriscraft.config.defaultChange
import io.hwfg.kriscraft.config.indexToString
import io.hwfg.kriscraft.config.toJsonElement
import net.minecraft.network.chat.Component

var shaders : LinkedHashMap<String,(String) -> Component> = LinkedHashMap()
var leftActions = LinkedHashMap<String,(JsonElement) -> JsonElement>()
var rightActions = LinkedHashMap<String,(JsonElement) -> JsonElement>()
val screen = ConfigScreen()
fun <T>addConfig(
    name : String,
    default : T,
    shader : ((String) -> Component)? = null,
    left : ((JsonElement) -> JsonElement)? = null,
    right : ((JsonElement) -> JsonElement)? = null
){
    if (default == null) return
    if (shader != null) shaders[name] = shader
    if (left != null) leftActions[name] = left
    if (right != null) rightActions[name] = right
    if (configures[name] == null) configures[name] = default.toJsonElement()
}
fun left(name : String) : Boolean{
    val factory = leftActions[name] ?: return false
    configures[name] = factory(configures[name] ?: return false)
    return true
}
fun right(name : String) : Boolean{
    val factory = rightActions[name] ?: return false
    configures[name] = factory(configures[name] ?: return false)
    return true
}
fun changeLeft(ptr : Int){
    if (!left(ptr)) configures[indexToString(ptr) ?: return] = configures[indexToString(ptr) ?: return]?.defaultChange(true) ?: return
    //else configures[indexToString(ptr) ?: return] = leftActions[ptr]?.value(configures[indexToString(ptr) ?: return] ?: return) ?: return
}
fun changeRight(ptr : Int){
    if (!right(ptr)) configures[indexToString(ptr) ?: return] = configures[indexToString(ptr) ?: return]?.defaultChange(false) ?: return
    //else configures[indexToString(ptr) ?: return] = rightActions[ptr]?.value(configures[indexToString(ptr) ?: return] ?: return) ?: return
}
fun left(i : Int): Boolean = left(indexToString(i) ?: return false)
fun right(i : Int): Boolean = right(indexToString(i) ?: return false)
fun screenInit() = Unit
