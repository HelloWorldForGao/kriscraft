package io.hwfg.kriscraft.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import java.util.function.Consumer

class ModMenu : ModMenuApi{
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> = { screen }
}