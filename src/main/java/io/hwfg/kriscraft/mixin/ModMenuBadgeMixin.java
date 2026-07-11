package io.hwfg.kriscraft.mixin;

import com.terraformersmc.modmenu.util.mod.Mod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Mod.Badge.class)
public enum ModMenuBadgeMixin {
    KRIS_CRAFT_MOSS(
            "modmenu.badge.moss",
            0xFF00FF00,
            0xFF006700,
            "moss"
    ),KRIS_CRAFT_DELTARUNE(
            "modmenu.badge.deltarune",
            0xFF5E5492,
            0xFF000000,
            "deltarune"
    );

    @Shadow
    ModMenuBadgeMixin(String translationKey, int outlineColor, int fillColor, String key) {
    }
}
