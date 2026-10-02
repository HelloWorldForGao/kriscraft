package io.hwfg.kriscraft.config

import com.google.gson.annotations.SerializedName
import io.hwfg.kriscraft.client.screen.ConfigHandler

open class SubConfig<A>(
    @SerializedName("trans") val translationKey : String?,
    val name : String,
    @SerializedName("help_trans")val helpTranslationKey : String?,
    val default : A,
    val handler : ConfigHandler<A>
) {
    open var value : A = default
    fun left() {
        value = handler.left(value)
    }
    fun right(){
        value = handler.right(value)
    }
    fun toSubConfigData() : SubConfigData<A> = SubConfigData(name,value)
    fun fromSubConfigData(data : SubConfigData<A>){
        if (data.name == name) value = data.value
    }
    fun applySubConfigData(data : SubConfigData<*>){
        @Suppress("UNCHECKED_CAST")
        value = data.value as A
    }
    open class SimpleSubConfig<A>(
        name : String,
        default : A,
        handler: ConfigHandler<A>
    ) : SubConfig<A>(
        "kriscraft.conf.${name}",
        name,
        "kriscraft.conf.${name}.help",
        default,
        handler
    )
}