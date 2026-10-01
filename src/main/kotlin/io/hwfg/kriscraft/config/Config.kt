package io.hwfg.kriscraft.config

import com.google.gson.reflect.TypeToken
import io.hwfg.kriscraft.Kriscraft
import io.hwfg.kriscraft.client.screen.handlers.BoolHandler
import io.hwfg.kriscraft.client.screen.handlers.impl.MossHealHandler
import io.hwfg.kriscraft.client.screen.handlers.impl.ProceedHandler
import io.hwfg.kriscraft.client.screen.handlers.impl.ProductScaleHandler
import io.hwfg.kriscraft.utils.applyData
import io.hwfg.kriscraft.utils.safeRead
import io.hwfg.kriscraft.utils.safeWrite

object Config {
    var subConfigs : MutableList<SubConfig<*>> = mutableListOf()
    var serverConfig = mutableListOf<SubConfig<*>>()
    fun get(name : String) : Any?{
        for (i in subConfigs){
            if (name == i.name) return i.value
        }
        return null
    }
    @Suppress("UNCHECKED_CAST")
    fun <A>forceGet(name : String) : A = get(name) as A
    fun save(){
        val subConfigData : MutableList<SubConfigData<*>> = mutableListOf()
        subConfigs.forEach { p0 ->
            subConfigData.addLast(p0.toSubConfigData())
        }
        Kriscraft.kriscraftConfigFile.safeWrite(subConfigData)
    }
    fun MutableList<SubConfig<*>>.init(){
        addLast(SubConfig.SimpleSubConfig(
            "moss_heal",
            20F,
            MossHealHandler
        ))
        addLast(SubConfig.SimpleSubConfig(
            "product_scale",
            1F,
            ProductScaleHandler
        ))
        addLast(SubConfig.SimpleSubConfig(
            "can_eat_moss",
            true,
            BoolHandler
        ))
        addLast(SubConfig.SimpleSubConfig(
            "proceed",
            1225F,
            ProceedHandler
        ))
    }
    fun read(){
        val res = Kriscraft.kriscraftConfigFile.safeRead(object : TypeToken<MutableList<SubConfigData<*>>>(){})
        subConfigs.init()
        if (res == null) return
        subConfigs.applyData(res)
        serverConfig.init()
    }
    fun init(){
        read()
    }
}