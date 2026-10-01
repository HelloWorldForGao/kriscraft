package io.hwfg.kriscraft.utils

import io.hwfg.kriscraft.Kriscraft
import io.hwfg.kriscraft.config.SubConfig
import io.hwfg.kriscraft.config.SubConfigData
import io.hwfg.kriscraft.payload.ClientUpload
import io.hwfg.kriscraft.payload.Proceed
import io.hwfg.kriscraft.payload.ServerDownload
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.Minecraft
import net.minecraft.server.level.ServerPlayer

fun List<SubConfig<*>>.applyData(data : List<SubConfigData<*>>){
    val dataMap = data.associateBy { it.name }
    for (config in this) {
        val matchedData = dataMap[config.name]
        if(matchedData != null) {
            config.applySubConfigData(matchedData)
        }
    }
}
fun List<SubConfig<*>>.getData() : List<SubConfigData<*>>{
    val dataList = mutableListOf<SubConfigData<*>>()
    for (config in this) {
        dataList.addLast(config.toSubConfigData())
    }
    return dataList
}
fun TranslationBuilder.addMap(vararg p0 : Map<String, String>){
    for (k in p0){
        for ((i, j) in k) {
            this.add(i, j)
        }
    }
}
fun TranslationBuilder.addAdvancement(vararg p0 : Map<String, List<String>>){
    for (k in p0){
        for ((i, j) in k) {
            val key = "kriscraft.adv.$i"
            this.add("$key.title", j[0])
            this.add("$key.description", j[1])
        }
    }
}
fun TranslationBuilder.addConfigs(vararg  p0 : Map<String, List<String>>){
    for (i in p0){
        for ((key, value) in i){
            this.add("kriscraft.conf.$key", value[0])
            this.add("kriscraft.conf.$key.help", value[1])
        }
    }
}
fun Any.toJson(): String = Kriscraft.gson.toJson(this)
fun upload(buf : Any){
    val str = buf.toJson()
    ClientPlayNetworking.send(ClientUpload(str))
}
fun download(buf : Any, target : ServerPlayer){
    val str = buf.toJson()
    ServerPlayNetworking.send(target, ServerDownload(str))
}
fun proceed(lastWord : String){
    if (Minecraft.getInstance().player == null) return
    ClientPlayNetworking.send(Proceed(lastWord))
}