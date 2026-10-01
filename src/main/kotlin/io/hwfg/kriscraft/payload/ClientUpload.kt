package io.hwfg.kriscraft.payload

import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier

@JvmRecord
data class ClientUpload(val json: String) : CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return type
    }

    companion object {
        var id: Identifier = Identifier.fromNamespaceAndPath(
            "kriscraft",
            "upload"
        )
        var type = CustomPacketPayload.Type<ClientUpload>(id)
        var codec =
            StreamCodec.composite<RegistryFriendlyByteBuf, ClientUpload, String>(
                ByteBufCodecs.STRING_UTF8,
                ClientUpload::json
            ) { json: String -> ClientUpload(json) }
    }
}
