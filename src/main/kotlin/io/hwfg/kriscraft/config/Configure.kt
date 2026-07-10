package io.hwfg.kriscraft.config

import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.mojang.blaze3d.platform.InputConstants
import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.Kriscraft
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.minecraft.ChatFormatting
import net.minecraft.client.KeyMapping
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
fun configureInit(){
    val str = Files.readString(
        Kriscraft.configFile
    )
    Core.logger.info("Read ${str.length} bytes : $str")
    val temp = Kriscraft.gson.fromJson<MutableMap<String, JsonElement>>(str, object : TypeToken<MutableMap<String, JsonElement>>(){}.type)
    for ((p0,p1) in temp) screen.core[p0] = p1
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
fun toJsonElement(obj : Any) : JsonElement = Kriscraft.gson.toJsonTree(obj)