package io.hwfg.kriscraft.config

import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.input.KeyEvent
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.sounds.SoundEvents
import org.lwjgl.glfw.GLFW

class ConfigScreen : Screen(Component.translatable("kriscraft.ui")) {
    private var ptr : Int = 0
    private var isMoved = false
    fun centerY(genHeight : Int,objectHeight : Int) : Int = genHeight - (objectHeight / 2)
    fun move(){
        Minecraft.getInstance().soundManager.play(
            SimpleSoundInstance.forUI(
                SoundEvents.UI_BUTTON_CLICK.value(),
                1F,
                1F
            )
        )
        isMoved = true
    }
    fun up(){
        move()
        if (ptr == 0) ptr = configures.size - 1
        else ptr--
    }
    fun down(){
        move()
        if (ptr == configures.size - 1) ptr = 0
        else ptr++
    }
    fun left(){
        move()
        //configures[ptr].left()
        configures[indexToString(ptr) ?: ""] = configures[indexToString(ptr)]?.change(true) ?: return
    }
    fun right(){
        move()
        //configures[ptr].right()
        configures[indexToString(ptr) ?: ""] = configures[indexToString(ptr)]?.change(false) ?: return
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
        for ((i, _) in configures){
            val y = centerY(
                30 * (stringToIndex(i) + 1) + 90,
                Minecraft.getInstance().font.lineHeight
            )
            graphics.text(
                Minecraft.getInstance().font,
                Component.translatable("kriscraft.key.$i"),
                100,
                y,
                0xFFFFFFFF.toInt()
            )
            graphics.text(
                Minecraft.getInstance().font,
                getCustomComponent(i),
                400,
                y,
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
        if (!isMoved) graphics.text(
            Minecraft.getInstance().font,
            Component.translatable("kriscraft.ui.tip").withStyle(ChatFormatting.GOLD),
            100,
            this.height - 20,
            0xFFFFFFFF.toInt()
        )
        else graphics.text(
            Minecraft.getInstance().font,
            Component.translatable("kriscraft.ui.tip.${indexToString(ptr)}").withStyle(ChatFormatting.GOLD),
            100,
            this.height - 20,
            0xFFFFFFFF.toInt()
        )
    }
    override fun onClose() {
        save()
        super.onClose()
    }

    init {
        addConfig(
            "can_eat_moss",
            true
        )
        addConfig(
            "can_eat_moss_block",
            true
        )
        addConfig(
            "moss_heal",
            10
        ){p0 ->
            val num = p0.toInt()
            if (num < 0) Component.literal(p0).withStyle(ChatFormatting.RED)
            else if (num in 0..19) Component.literal(p0)
            else Component.literal(p0).withStyle(ChatFormatting.GREEN)
        }
    }
}