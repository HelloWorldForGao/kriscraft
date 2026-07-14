package io.hwfg.kriscraft.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import io.hwfg.kriscraft.client.screen

class ModMenu : ModMenuApi{
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> = { screen }
}