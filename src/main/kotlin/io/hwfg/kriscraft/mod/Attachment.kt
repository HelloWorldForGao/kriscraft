package io.hwfg.kriscraft.mod

import com.mojang.serialization.Codec
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.resources.Identifier

@JvmField val mossCount = createAttachment(
    "kriscraft",
    "moss_count",
    Codec.INT
)

@JvmField val sleepMossCount = createAttachment(
    "kriscraft",
    "sleep_moss",
    Codec.INT
)

@JvmField val eatMossBreadCount = createAttachment(
    "kriscraft",
    "eat_moss_bread",
    Codec.INT
)

@JvmField val eatMossBurgerCount = createAttachment(
    "kriscraft",
    "eat_moss_burger",
    Codec.INT
)

@JvmField val negHealCount = createAttachment(
    "kriscraft",
    "neg_heal",
    Codec.INT
)

fun <A : Any>createAttachment(
    namespace : String,
    name : String,
    type : Codec<A>
) : AttachmentType<A> = AttachmentRegistry.create(
    Identifier.fromNamespaceAndPath(namespace,name)
){p0 ->
    p0.persistent(type)
    p0.copyOnDeath()
}

fun attachmentInit() = Unit

