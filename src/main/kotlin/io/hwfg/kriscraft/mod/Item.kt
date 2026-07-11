package io.hwfg.kriscraft.mod

import io.hwfg.kriscraft.item.MossBread
import io.hwfg.kriscraft.item.MossBurger
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Items

@JvmField
val mossBread = Registry.register(
    BuiltInRegistries.ITEM,
    ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(
            "kriscraft",
            "moss_bread"
        )
    ),
    MossBread()
)
@JvmField
val mossBurger = Registry.register(
    BuiltInRegistries.ITEM,
    ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(
            "kriscraft",
            "moss_burger"
        )
    ),
    MossBurger()
)

fun itemInit(){
    CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
        .register { p0 ->
            p0.insertAfter(Items.BREAD,mossBread,mossBurger)
        }
}