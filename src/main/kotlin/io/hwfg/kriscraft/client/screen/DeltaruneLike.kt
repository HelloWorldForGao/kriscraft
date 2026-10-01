package io.hwfg.kriscraft.client.screen

import com.google.gson.reflect.TypeToken
import com.mojang.authlib.minecraft.client.MinecraftClient
import io.hwfg.kriscraft.client.screen.handlers.IntHandler
import io.hwfg.kriscraft.config.SubConfig
import io.hwfg.kriscraft.config.SubConfigData
import io.hwfg.kriscraft.utils.applyData
import io.hwfg.kriscraft.utils.getData
import io.hwfg.kriscraft.utils.safeRead
import io.hwfg.kriscraft.utils.safeWrite
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.player.LocalPlayer
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.player.Player
import org.lwjgl.glfw.GLFW
import java.nio.file.Files
import java.nio.file.Path

open class DeltaruneLike(
    title : Component,
    val subtitle : Component,
    var configList : MutableList<SubConfig<*>> = mutableListOf(),
    var savePos : Path? = null,
    val excQuitEvent : (LocalPlayer) -> Unit = {},
    val shiftQuitEvent : (LocalPlayer) -> Unit = {},
    val textDistance : Int = 30
) : Screen(title) {
    companion object{
        val testList : MutableList<SubConfig<*>> = mutableListOf(
            SubConfig(
                "hwfg.test1",
                "test1",
                "hwfg.test1",
                114514,
                IntHandler
            ),
            SubConfig(
                "hwfg.test2",
                "test2",
                "hwfg.test2",
                1919810,
                IntHandler
            ),
            SubConfig(
                "hwfg.test3",
                "test3",
                "hwfg.test3",
                33550336,
                IntHandler
            ),
            SubConfig(
                "hwfg.test4",
                "test4",
                "hwfg.test4",
                114514,
                IntHandler
            ),
            SubConfig(
                "hwfg.test5",
                "test5",
                "hwfg.test5",
                1919810,
                IntHandler
            ),
            SubConfig(
                "hwfg.test6",
                "test6",
                "hwfg.test6",
                33550336,
                IntHandler
            )
        )
        val testScreen = DeltaruneLike(
            Component.literal("test"),
            Component.literal("sub_test"),
            testList
        )
    }
    open val SOUL = Identifier.fromNamespaceAndPath(
        "kriscraft",
        "textures/soul.png"
    )
    open var ptr = 0
    //open var configList : MutableList<SubConfig<*>> = mutableListOf()
    fun centerY(genHeight : Int,objectHeight : Int) : Int = genHeight - (objectHeight / 2)
    fun calcY(index : Int) = 100 + (textDistance * index)
    fun up(){
        if (ptr == 0) ptr = configList.size - 1
        else ptr--
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
    }
    fun down(){
        if (ptr == configList.size - 1) ptr = 0
        else ptr++
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
    }
    fun left() {
        configList[ptr].left()
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
    }
    fun right() {
        configList[ptr].right()
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
    }
    override fun extractRenderState(graphics: GuiGraphicsExtractor, mouseX: Int, mouseY: Int, a: Float) {
        val page = Math.ceilDiv(ptr + 1,6)
        val start = (page - 1) * 6
        val stop = page * 6 - 1
        for ((i,j) in configList.withIndex()){
            if (i !in start..stop) continue
            val pos = i % 6
            //j.shadow(calcY(pos),graphics)
            val res = j.handler.shader(j.value)
            graphics.text(
                Minecraft.getInstance().font,
                res,
                600,
                calcY(pos),
                0xFFFFFFFF.toInt(),
                false
            )
            if (j.translationKey != null) graphics.text(
                Minecraft.getInstance().font,
                Component.translatable(j.translationKey),
                100,
                calcY(pos),
                0xFFFFFFFF.toInt(),
                false
            )
            else graphics.text(
                Minecraft.getInstance().font,
                Component.literal(j.name),
                100,
                calcY(pos),
                0xFFFFFFFF.toInt(),
                false
            )
        }
        val soulPos = ptr % 6
        graphics.blit(
            RenderPipelines.GUI_TEXTURED,
            SOUL,
            50,
            centerY(calcY(soulPos),16),
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
            title,
            100,
            50,
            0xFFFFFFFF.toInt(),
            false
        )
        graphics.text(
            Minecraft.getInstance().font,
            subtitle,
            400,
            50,
            0xFFFFFFFF.toInt(),
            false
        )
        graphics.text(
            Minecraft.getInstance().font,
            Component.translatable(configList[ptr].helpTranslationKey ?: ""),
            100,
            calcY(7),
            0xFFFFFFFF.toInt(),
            false
        )
    }
    fun save(){
        if (savePos == null) return
        (savePos as Path).safeWrite(configList.getData())
    }
    fun read(){
        if (savePos == null) return
        val dataList = (savePos as Path).safeRead(object : TypeToken<MutableList<SubConfigData<*>>>(){}) ?: return
        configList.applyData(dataList)
    }
    fun escape(){
        save()
        Minecraft.getInstance().setScreen(null)
    }

    override fun keyReleased(event: KeyEvent): Boolean {
        // 把ESC、右Shift放到最顶部！优先处理退出
        if (event.isEscape) {
            val player = Minecraft.getInstance().player
            if(player != null) {
                excQuitEvent(player)
            }
            escape()
            return true // 关闭屏幕后直接return，不再执行后面任何逻辑
        }

        if (event.key == GLFW.GLFW_KEY_RIGHT_SHIFT){
            val player = Minecraft.getInstance().player
            if(player != null) {
                excQuitEvent(player)
                shiftQuitEvent(player)
            }
            escape()
            return true
        }

        // 只有不按ESC/Shift才执行光标移动
        if (event.isUp) up()
        if (event.isDown) down()
        if (event.isLeft) left()
        if (event.isRight) right()

        return true
    }

    override fun shouldCloseOnEsc(): Boolean = false
    override fun init() {
        read()
    }
}