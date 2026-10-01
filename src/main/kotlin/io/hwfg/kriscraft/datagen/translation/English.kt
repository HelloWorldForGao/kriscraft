package io.hwfg.kriscraft.datagen.translation

import io.hwfg.kriscraft.mod.burgerEffect
import io.hwfg.kriscraft.mod.mossBedBlock
import io.hwfg.kriscraft.mod.mossBread
import io.hwfg.kriscraft.mod.mossBurger
import io.hwfg.kriscraft.mod.mossyEffect
import io.hwfg.kriscraft.utils.*
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class English(
    output : FabricPackOutput,
    registryLookup : CompletableFuture<HolderLookup.Provider>
) : FabricLanguageProvider(output,"en_us",registryLookup) {
    val basic: MutableMap<String, String> = mutableMapOf(
        "kriscraft.name" to "KrisCraft",
        "kriscraft.description" to "Make the moss in Minecraft more useful",
        "kriscraft.ui" to "KrisCraft : Config page",
        "kriscraft.ui.tip" to "Operate like in the Deltarune!If you has the permission,you can exit by pressing right shift to sync the config with the server",
        "kriscraft.true" to "Yes",
        "kriscraft.false" to "No",
        "kriscraft.eatmoss" to "Your HP was mossed out"
    )
    val modMenu = mutableMapOf(
        "modmenu.badge.moss" to "Moss",
        "modmenu.badge.deltarune" to "Deltarune",
        "kriscraft.modmenu.general_idea" to "General Idea(where the idea from)",
        "kriscraft.modmenu.genshin" to "Genshin Impact(also my favourite game)",
        "kriscraft.modmenu.github" to "Github",
        "kriscraft.modmenu.gitee" to "Gitee",
        "modmenu.nameTranslation.kriscraft" to "KrisCraft",
        "modmenu.descriptionTranslation.kriscraft" to "Make the moss in Minecraft more useful"
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
        "key.category.kriscraft.default" to "KrisCraft",
        "kriscraft.key.open" to "Open Config Page"
    )
    val command = mapOf(
        "kriscraft.del" to "Do you want to delete all the KrisCraft data?Delete by using /kriscraft data del proceed .",
        "kriscraft.del.finish" to "All the KrisCraft data has been deleted!"
    )
    val sync = mapOf(
        "kriscraft.no_perm" to "No permission to sync to the server.Close with ESC to avoid seeing this warn again.",
        "kriscraft.sync.error" to $$"A error occurred when syncing : %1$s",
        "kriscraft.sync.finish" to "Syncing finished!",
        "kriscraft.different" to $$"Client-server data is different,client:%1$s,server:%2$s",
        "kriscraft.same" to "Client-server data is same"
    )
    val getData = mapOf(
        "kriscraft.dataget.moss_count" to $$"You have ate %1$s moss(es).",
        "kriscraft.dataget.sleep_moss_count" to $$"You have jumped the night on the moss bed for %1$s time(s).",
        "kriscraft.dataget.eat_moss_bread_count" to $$"You have ate %1$s moss bread(s).",
        "kriscraft.dataget.eat_moss_burger_count" to $$"You have ate %1$s moss burger(s).",
        "kriscraft.dataget.neg_heal_count" to $$"You have found out the Easter-Egg of neg-heal and suffered from neg-heal for %1$s time(s).",
    )
    val advMainLine = mapOf(
        "kris_root" to listOf(
            "KrisCraft",
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
            "Sleep for one night first,then do anything else tomorrow"
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
            "You can delete this world,because you are so unlucky."
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
            "Even Kris can't eat so much moss bread",
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
        "snowgrave" to listOf(
            "Quick-frozen food",
            "Try to \"proceed\",and then what will happen?"
        )
    )
    val config = mapOf(
        "moss_heal" to listOf(
            "Moss heal",
            "How many will a moss heal"
        ),
        "product_scale" to listOf(
            "Moss products' healing scale",
            "The scale of the moss bread,2 times on moss burger(all 1 tps)"
        ),
        "can_eat_moss" to listOf(
            "Can eat moss",
            "If you can eat moss.Whether you can eat moss or not,won't effect the eating of the products"
        ),
        "proceed" to listOf(
            "Proceed",
            "Deadly"
        )
    )
    val ui = mapOf(
        "kriscraft.ui.proceed" to "Press right arrow ->"
    )
    override fun generateTranslations(
        lookup: HolderLookup.Provider,
        builder: TranslationBuilder
    ) {
        builder.addMap(
            basic,
            modMenu,
            zhiZhanZhiShang,
            damageType,
            key,
            command,
            sync,
            getData,
            ui
        )
        builder.addAdvancement(
            advMainLine,
            advSleepMoss,
            advEatMossBread,
            advSuicide
        )
        builder.addConfigs(
            config
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