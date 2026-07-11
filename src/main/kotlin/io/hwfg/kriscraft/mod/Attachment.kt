package io.hwfg.kriscraft.mod

import com.mojang.serialization.Codec
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.minecraft.resources.Identifier

@JvmField
val mossCount = AttachmentRegistry.createPersistent(
    Identifier.fromNamespaceAndPath("kriscraft","moss_count"),
    Codec.INT
)
@JvmField
val sleepMossCount = AttachmentRegistry.createPersistent(
    Identifier.fromNamespaceAndPath("kriscraft","sleep_moss_count"),
    Codec.INT
)
@JvmField
val eatMossBreadCount = AttachmentRegistry.createPersistent(
    Identifier.fromNamespaceAndPath("kriscraft","eat_moss_bread"),
    Codec.INT
)
@JvmField
val eatMossBurgerCount = AttachmentRegistry.createPersistent(
    Identifier.fromNamespaceAndPath("kriscraft","eat_moss_burger"),
    Codec.INT
)

fun attachmentInit() = Unit