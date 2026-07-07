package io.hwfg.kriscraft

import com.mojang.serialization.Codec
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.minecraft.resources.Identifier

val mossCount = AttachmentRegistry.createPersistent(
    Identifier.fromNamespaceAndPath("kriscraft","moss_count"),
    Codec.INT
)
val sleepMossCount = AttachmentRegistry.createPersistent(
    Identifier.fromNamespaceAndPath("kriscraft","sleep_moss_count"),
    Codec.INT
)
val eatMossBreadCount = AttachmentRegistry.createPersistent(
    Identifier.fromNamespaceAndPath("kriscraft","eat_moss_bread"),
    Codec.INT
)
val eatMossBurgerCount = AttachmentRegistry.createPersistent(
    Identifier.fromNamespaceAndPath("kriscraft","eat_moss_burger"),
    Codec.INT
)

fun attachmentInit() = Unit