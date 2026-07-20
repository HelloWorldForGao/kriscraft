package io.hwfg.kriscraft.datagen.translation

import io.hwfg.kriscraft.mod.burgerEffect
import io.hwfg.kriscraft.mod.mossBedBlock
import io.hwfg.kriscraft.mod.mossBread
import io.hwfg.kriscraft.mod.mossBurger
import io.hwfg.kriscraft.mod.mossyEffect
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class English(
    output : FabricPackOutput,
    registryLookup : CompletableFuture<HolderLookup.Provider>
) : FabricLanguageProvider(output,"en_us",registryLookup) {
    val basic: MutableMap<String, String> = mutableMapOf(
        "kriscraft.name" to "Kris craft",
        "kriscraft.description" to "Make the moss in Minecraft more useful",
        "kriscraft.ui" to "Kris craft : Config page",
        "kriscraft.ui.tip" to "Operate like in the Deltarune!If you has the permission,you can exit by pressing right shift to sync the config with the server",
        "kriscraft.true" to "Yes",
        "kriscraft.false" to "No",
        "kriscraft.eatmoss" to "Your HP was mossed out"
    )
    val modMenu = mutableMapOf(
        "modmenu.badge.moss" to "Moss",
        "modmenu.badge.deltarune" to "Deltarune",
        "kriscraft.modmenu.general_idea" to "General Idea",
        "kriscraft.modmenu.genshin" to "Genshin Impact",
        "kriscraft.modmenu.github" to "Github",
        "kriscraft.modmenu.gitee" to "Gitee",
        "modmenu.nameTranslation.kriscraft" to "Kris craft",
        "modmenu.descriptionTranslation.kriscraft" to "Make the moss in Minecraft more useful"
    )
    val ui = mutableMapOf(
        "kriscraft.key.can_eat_moss" to "Can eat moss(Item)",
        "kriscraft.key.can_eat_moss_block" to "Can eat moss(Block)",
        "kriscraft.key.moss_heal" to "Moss heal number",
        "kriscraft.key.moss_product_heal_base_scale" to "Moss product heal number's base scale",
        "kriscraft.ui.tip.can_eat_moss" to "Can eat moss(Item).Warn:when you press shift,whether this is enabled or not,you won't eat moss",
        "kriscraft.ui.tip.can_eat_moss_block" to "Can eat moss(Block).Warn:when you press shift,whether this is enabled or not,you won't eat moss",
        "kriscraft.ui.tip.moss_heal" to "How much does a moss heal.Will tips you when eating moss if this value >= 20",
        "kriscraft.ui.tip.moss_product_heal_base_scale" to "The scale of the moss bread's heal number,details see the README"
    )
    val zhiZhanZhiShang = mutableMapOf(
        "kriscraft.the_sacrifice_of_stoping_wars1" to "The Leidewang will land the Earth after 3 hours",
        "kriscraft.the_sacrifice_of_stoping_wars2" to "The burger is not big and the bread is dry",
        "kriscraft.the_sacrifice_of_stoping_wars3" to "The only thing which make me remember is the burger in school",
        "kriscraft.the_sacrifice_of_stoping_wars4" to "Seems no matter how high-level you are,you can't keep yourself from the burger",
        "kriscraft.the_sacrifice_of_stoping_wars5" to "But the space is big,and who can determine the burger in school?",
        "kriscraft.the_sacrifice_of_stoping_wars" to "Eat %s moss burgers"
    )
    val damageType = mutableMapOf(
        "death.attack.negative" to $$"%1$s have found out that the moss was harmful",
        "death.attack.negative.player" to $$"%1$s have found out that the moss was harmful with the help of %2$s",
        "death.attack.snowgrave" to $$"%1$s released the snowgrave to himself",
        "death.attack.snowgrave.player" to $$"%2$s released the snowgrave to %1$s "
    )
    val key = mutableMapOf(
        "key.category.kriscraft.default" to "Kris craft",
        "kriscraft.key.open" to "Open Config Page"
    )
    val command = mapOf(
        "kriscraft.config.command_executed" to $$"%1$s have been set %2$s",
        "kriscraft.no_perm" to "No permission syncing it.Avoid seeing this by using Esc"
    )
    val advMainLine = mapOf(
        "kris_root" to listOf(
            "Kris craft",
            "Make the moss in Minecraft more useful"
        ),
        "kris10" to listOf(
            "MOSS FINDER",
            "Eat 10 moss"
        ),
        "kris100" to listOf(
            "MOSS MOST",
            "Eat 100 moss"
        ),
        "kris30000" to listOf(
            "Kris-self",
            "I will be a Minecraft player forever if I can eat 30000 moss every day."
        )
    )
    val advSleepMoss = mapOf(
        "sleep_moss1" to listOf(
            "Sleep with moss",
            "Sleep for one moss-night first,then do anything else tomorrow"
        ),
        "sleep_moss5" to listOf(
            "Monday to Friday",
            "You may not lucky enough to find a sheep"
        ),
        "sleep_moss31" to listOf(
            "Lullaby of the New Moon: Somnias a Moss",
            "One month passed,this seed may not good"
        ),
        "sleep_moss365" to listOf(
            "One year passed",
            "You can delete this world"
        )
    )
    val advEatMossBread = mapOf(
        "eat_moss_bread1" to listOf(
            "Mossy bread",
            "Eat 1 Moss bread"
        ),
        "eat_moss_bread10" to listOf(
            "More mossy bread",
            "Eat 10 Moss bread"
        ),
        "eat_moss_bread100" to listOf(
            "Bready moss",
            "Eat 100 Moss bread"
        ),
        "eat_moss_bread30000" to listOf(
            "Kris is playing your account",
            "Eat 30000 Moss bread"
        )
    )
    val advSuicide = mapOf(
        "neg_heal1" to listOf(
            "Worse than nothing",
            "Negative heal for one time"
        ),
        "neg_heal10" to listOf(
            "Taught but not change",
            "Negative heal for 10 times"
        ),
        "neg_heal_death" to listOf(
            "Deadly moss",
            "The moss was harmful"
        ),
        "snowgrave" to listOf(
            "Quick-frozen food",
            "Bore the damage from the snowgrave"
        )
    )
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
    override fun generateTranslations(
        lookup: HolderLookup.Provider,
        builder: TranslationBuilder
    ) {
        builder.addMap(
            basic,
            modMenu,
            ui,
            zhiZhanZhiShang,
            damageType,
            key,
            command
        )
        builder.addAdvancement(
            advMainLine,
            advSleepMoss,
            advEatMossBread,
            advSuicide
        )
        builder.add(
            mossBread,
            "Moss Bread"
        )
        builder.add(
            mossBedBlock,
            "Moss bed"
        )
        builder.add(
            mossBurger,
            "Moss burger"
        )
        builder.add(
            mossyEffect.value(),
            "Moss"
        )
        builder.add(
            burgerEffect.value(),
            "Burger"
        )
    }
}