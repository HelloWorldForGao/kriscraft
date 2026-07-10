package io.hwfg.kriscraft.config

import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.Kriscraft
import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.resources.sounds.MinecartSoundInstance
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.client.resources.sounds.Sound
import net.minecraft.client.resources.sounds.SoundInstance
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import org.lwjgl.glfw.GLFW
import java.nio.file.Files

class ConfigScreen : Screen(Component.translatable("kriscraft.ui")) {
    var core : MutableMap<String, JsonElement> = mutableMapOf()
    private var ptrMap : MutableMap<Int,String> = mutableMapOf()
    private var ptr : Int = 0
    private inline fun <reified T>addConfig(name : String, default : T){
        core[name] = Kriscraft.gson.toJsonTree(default)
        ptrMap[core.size - 1] = name
    }
    fun indexToString(num : Int) : String? = ptrMap[num]
    fun stringToIndex(str : String) : Int{
        var cnt = 0
        for ((i,j) in core){
            if (i == str) return cnt
            cnt++
        }
        return -1
    }
    fun centerY(genHeight : Int,objectHeight : Int) : Int = genHeight - (objectHeight / 2)
    fun up(){
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
        if (ptr == 0) ptr = core.size - 1
        else ptr--
    }
    fun down(){
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
        if (ptr == core.size - 1) ptr = 0
        else ptr++
    }
    fun left(){
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
        //configures[ptr].left()
        core[indexToString(ptr) ?: ""] = core[indexToString(ptr)]?.change(true) ?: return
    }
    fun right(){
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
        //configures[ptr].right()
        core[indexToString(ptr) ?: ""] = core[indexToString(ptr)]?.change(false) ?: return
    }

    override fun keyReleased(event: KeyEvent): Boolean {
        if (event.key == GLFW.GLFW_KEY_UP) up()
        if (event.key == GLFW.GLFW_KEY_DOWN) down()
        if (event.key == GLFW.GLFW_KEY_LEFT) left()
        if (event.key == GLFW.GLFW_KEY_RIGHT) right()
        if (event.key == GLFW.GLFW_KEY_RIGHT_SHIFT){
            Minecraft.getInstance().setScreen(null)
            onClose()
        }
        return super.keyReleased(event)
    }

    override fun extractRenderState(graphics: GuiGraphicsExtractor, mouseX: Int, mouseY: Int, a: Float) {
        super.extractRenderState(graphics, mouseX, mouseY, a)
        graphics.text(
            Minecraft.getInstance().font,
            Component.translatable("kriscraft.name"),
            50,
            50,
            0xFFFFFFFF.toInt()
        )
        graphics.text(
            Minecraft.getInstance().font,
            Component.translatable("kriscraft.description").withStyle(ChatFormatting.GREEN),
            150,
            50,
            0xFFFFFFFF.toInt()
        )
        for ((i, element) in core){
            graphics.text(
                Minecraft.getInstance().font,
                Component.translatable("kriscraft.key.$i"),
                100,
                centerY(
                    30 * (stringToIndex(i) + 1) + 90,
                    Minecraft.getInstance().font.lineHeight
                ),
                0xFFFFFFFF.toInt()
            )
            graphics.text(
                Minecraft.getInstance().font,
                element.toComponent(),
                400,
                centerY(
                    30 * (stringToIndex(i) + 1) + 90,
                    Minecraft.getInstance().font.lineHeight
                ),
                0xFFFFFFFF.toInt()
            )
        }
        graphics.blit(
            RenderPipelines.GUI_TEXTURED,
            Identifier.fromNamespaceAndPath(
                "kriscraft",
                "textures/soul.png"
            ),
            50,
            centerY(
                30 * (ptr + 1) + 90,
                16
            ),
            0F,
            0F,
            16,
            16,
            16,
            16,
            16,
            16
        )
        graphics.text(
            Minecraft.getInstance().font,
            Component.translatable("kriscraft.ui.tip").withStyle(ChatFormatting.GOLD),
            100,
            this.height - 20,
            0xFFFFFFFF.toInt()
        )
    }

    override fun onClose() {
        val str = Kriscraft.gson.toJson(core)
        Core.logger.info("Write ${str.length} char into kriscraft.json : $str")
        Files.write(
            Kriscraft.configFile,
            str.toByteArray()
        )
        super.onClose()
    }

    init {
        this.addConfig(
            "test1",
            false
        )
        this.addConfig(
            "test2",
            false
        )
        this.addConfig(
            "test3",
            1
        )
    }

    override fun init() {
        super.init()
    }
}