package io.hwfg.kriscraft.config

import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.Kriscraft
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.minecraft.ChatFormatting
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import org.lwjgl.glfw.GLFW
import java.nio.file.Files

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
var configures : MutableMap<String, JsonElement> = mutableMapOf()
var ptrMap : MutableMap<Int,String> = mutableMapOf()
var shaders : MutableMap<String,(String) -> Component> = mutableMapOf()
fun <T>addConfig(
    name : String,
    default : T,
    shader : ((String) -> Component)? = null
){
    if (shader != null) shaders[name] = shader
    configures[name] = Kriscraft.gson.toJsonTree(default)
    ptrMap[configures.size - 1] = name
}
fun getCustomComponent(name : String) : Component{
    val element = configures[name]
    val factory = shaders[name]
    if (element == null) return Component.literal("404 Not Found").withStyle(ChatFormatting.RED)
    if (factory == null) return element.toComponent()
    else return factory.invoke(element.asString)
}
fun indexToString(num : Int) : String? = ptrMap[num]
fun stringToIndex(str : String) : Int{
    var cnt = 0
    for ((i, _) in configures){
        if (i == str) return cnt
        cnt++
    }
    return -1
}
fun save(){
    val str = Kriscraft.gson.toJson(configures)
    Core.logger.info("Write ${str.length} char into kriscraft.json : $str")
    Files.write(
        Kriscraft.configFile,
        str.toByteArray()
    )
}
/*val wasd = listOf(
    KeyMappingHelper.registerKeyMapping(
        KeyMapping(
            "kriscraft.key.up",
            GLFW.GLFW_KEY_UP,
            keyCategory
        )
    ),
    KeyMappingHelper.registerKeyMapping(
        KeyMapping(
            "kriscraft.key.left",
            GLFW.GLFW_KEY_LEFT,
            keyCategory
        )
    ),
    KeyMappingHelper.registerKeyMapping(
        KeyMapping(
            "kriscraft.key.down",
            GLFW.GLFW_KEY_DOWN,
            keyCategory
        )
    ),
    KeyMappingHelper.registerKeyMapping(
        KeyMapping(
            "kriscraft.key.right",
            GLFW.GLFW_KEY_RIGHT,
            keyCategory
        )
    )
)
val pause = KeyMappingHelper.registerKeyMapping(
    KeyMapping(
        "kriscraft.key.pause",
        GLFW.GLFW_KEY_RIGHT_SHIFT,
        keyCategory
    )
)*/
val screen = ConfigScreen()
fun JsonElement.toComponent() : Component{
    return if (this.isJsonPrimitive){
        if (this.asJsonPrimitive.isBoolean){
            if (this.asBoolean) Component
                .translatable("kriscraft.true")
                .withStyle(ChatFormatting.GREEN)
            else Component
                .translatable("kriscraft.false")
                .withStyle(ChatFormatting.RED)
        } else if (this.asJsonPrimitive.isNumber) Component.literal(this.asNumber.toString())
        else Component.literal(this.toString())
    }
    else Component.literal(this.toString())
}
fun JsonElement.change(isLeft : Boolean) : JsonElement{
    if (this.isJsonPrimitive){
        if (this.asJsonPrimitive.isBoolean){
            if (this.asBoolean) return toJsonElement(false)
            else return toJsonElement(true)
        }
        else if (this.asJsonPrimitive.isNumber){
            if (isLeft) return toJsonElement(this.asInt - 1)
            else return toJsonElement(this.asInt + 1)
        }
    }
    return this
}
inline fun <reified T>JsonElement.fromJsonElement() : T = Kriscraft.gson
    .fromJson(this,object : TypeToken<T>(){}.type)
fun toJsonElement(obj : Any) : JsonElement = Kriscraft.gson.toJsonTree(obj)

fun configureInit(){
    val str = Files.readString(
        Kriscraft.configFile
    )
    Core.logger.info("Read ${str.length} bytes : $str")
    val temp = Kriscraft.gson.fromJson<MutableMap<String, JsonElement>>(
        str,
        object : TypeToken<MutableMap<String, JsonElement>>() {}.type
    ) ?: return
    for ((p0,p1) in temp) configures[p0] = p1
}