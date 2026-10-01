package io.hwfg.kriscraft.utils

import io.hwfg.kriscraft.config.Config
import io.hwfg.kriscraft.mod.negDamage
import io.hwfg.kriscraft.mod.negHealCount
import io.hwfg.kriscraft.mod.negHealCriteria
import io.hwfg.kriscraft.mod.timer
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.ChatFormatting
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item

fun ServerPlayer.addAttached(type: AttachmentType<Int>,num: Int){
    val oldValue = this.getAttached(type) ?: 0
    val newValue = oldValue + num
    this.setAttached(type,newValue)
}
fun ServerPlayer.getAndAdd(type : AttachmentType<Int>, num : Int) : Int{
    val oldValue = this.getAttached(type) ?: 0
    val newValue = oldValue + num
    this.setAttached(type,newValue)
    return oldValue
}

fun ServerPlayer.addAndGet(type : AttachmentType<Int>, num : Int) : Int{
    val oldValue = this.getAttached(type) ?: 0
    val newValue = oldValue + num
    this.setAttached(type,newValue)
    return newValue
}

fun ServerPlayer.healFromMoss(scale : Float = 1F,ignoreTimer : Boolean = false){
    val mossHeal = Config.forceGet<Float>("moss_heal")
    val num = mossHeal * scale
    this.improvedHeal(num,ignoreTimer)
}

fun ServerPlayer.improvedHeal(num : Float,ignoreTimer : Boolean = false){
    if (this.getAttached(timer) != 10 && !ignoreTimer) return
    if (num >= 20) this.sendSystemMessage(
        Component.translatable("kriscraft.eatmoss")
            .withStyle(ChatFormatting.GREEN),
        false
    )
    if (num >= 0) this.heal(num)
    else this.hurt(negDamage,-num)
}

fun ServerPlayer.hurt(id : ResourceKey<DamageType>,num : Float){
    val source = DamageSource(
        this.level()
            .registryAccess()
            .lookupOrThrow(Registries.DAMAGE_TYPE)
            .getOrThrow(id)
    )
    this.hurtServer(
        this.level(),
        source,
        num
    )
}

fun Player.isHanding(hand : InteractionHand, item : Item) : Boolean = this.getItemInHand(hand).item == item
fun ServerPlayer.negHealTrigger(isProduct : Boolean = false){
    var mossHeal = Config.forceGet<Float>("moss_heal")
    val scale = Config.forceGet<Float>("product_scale")
    if (isProduct) mossHeal *= scale
    if (mossHeal < 0) {
        negHealCriteria.trigger(
            this,
            this.addAndGet(
                negHealCount,
                1
            )
        )
    }
}
