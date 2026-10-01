package io.hwfg.kriscraft.payload

import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier

@JvmRecord
data class Proceed(val lastWord : String) : CustomPacketPayload{
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = type
    companion object{
        val id = Identifier.fromNamespaceAndPath(
            "kriscraft",
            "proceed"
        )
        val type = CustomPacketPayload.Type<Proceed>(id)
        val codec =
            StreamCodec.composite<RegistryFriendlyByteBuf, Proceed, String>(
            ByteBufCodecs.STRING_UTF8,
            Proceed::lastWord
        ){ Proceed(it) }
    }
}