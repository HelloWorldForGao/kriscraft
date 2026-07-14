package io.hwfg.kriscraft.config

import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.Core.get
import io.hwfg.kriscraft.Kriscraft
import io.hwfg.kriscraft.client.leftActions
import io.hwfg.kriscraft.client.rightActions
import io.hwfg.kriscraft.client.shaders
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import java.nio.file.Files

var configures : LinkedHashMap<String, JsonElement> = LinkedHashMap()
fun getCustomComponent(name : String) : Component{
    val element = configures[name]
    val factory = shaders[name]
    if (element == null) return Component.literal("404 Not Found").withStyle(ChatFormatting.RED)
    if (factory == null) return element.toComponent()
    else return factory.invoke(element.asString)
}
fun indexToString(num : Int) : String? = configures[num]?.key
fun stringToIndex(str : String) : Int{
    configures.entries.forEachIndexed { index, entry ->
        if (entry.key == str) return index
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
fun JsonElement.defaultChange(isLeft : Boolean) : JsonElement{
    if (this.isJsonPrimitive){
        if (this.asJsonPrimitive.isBoolean){
            if (this.asBoolean) return jsonElement(false)
            else return jsonElement(true)
        }
        else if (this.asJsonPrimitive.isNumber){
            if (isLeft) return jsonElement(this.asInt - 1)
            else return jsonElement(this.asInt + 1)
        }
    }
    return this
}

inline fun <reified T>JsonElement.fromJsonElement() : T = Kriscraft.gson
    .fromJson(this,object : TypeToken<T>(){}.type)
fun jsonElement(obj : Any) : JsonElement = Kriscraft.gson.toJsonTree(obj)
fun Any.toJsonElement() : JsonElement = jsonElement(this)

fun configureInit(){
    val str = Files.readString(
        Kriscraft.configFile
    )
    Core.logger.info("Read ${str.length} bytes : $str")
    val temp = Kriscraft.gson.fromJson<LinkedHashMap<String, JsonElement>>(
        str,
        object : TypeToken<LinkedHashMap<String, JsonElement>>() {}.type
    ) ?: return
    for ((p0,p1) in temp) configures[p0] = p1
}