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

class Chinese(
    output : FabricPackOutput,
    registryLookup : CompletableFuture<HolderLookup.Provider>
) : FabricLanguageProvider(output,"zh_cn",registryLookup) {
    val basic: MutableMap<String, String> = mutableMapOf(
        "kriscraft.name" to "Kris工艺",
        "kriscraft.description" to "使MC的苔藓更有用",
        "kriscraft.ui" to "Kris工艺：配置页面",
        "kriscraft.ui.tip" to "像在三角符文里一样操作，当你有权限修改服务器配置时按右shift退出可同步至服务器",
        "kriscraft.true" to "是",
        "kriscraft.false" to "否",
        "kriscraft.eatmoss" to "你的生命被苔高到了极藓"
    )
    val modMenu = mutableMapOf(
        "modmenu.badge.moss" to "苔藓",
        "modmenu.badge.deltarune" to "三角符文",
        "kriscraft.modmenu.general_idea" to "源灵感",
        "kriscraft.modmenu.genshin" to "源神",
        "kriscraft.modmenu.github" to "Github",
        "kriscraft.modmenu.gitee" to "Gitee",
        "modmenu.nameTranslation.kriscraft" to "Kris工艺",
        "modmenu.descriptionTranslation.kriscraft" to "使MC的苔藓更有用"
    )
    val ui = mutableMapOf(
        "kriscraft.key.can_eat_moss" to "可以吃苔藓（物品）",
        "kriscraft.key.can_eat_moss_block" to "可以吃苔藓（方块）",
        "kriscraft.key.moss_heal" to "苔藓回复量",
        "kriscraft.key.moss_product_heal_base_scale" to "苔藓制品基础回复倍率",
        "kriscraft.ui.tip.can_eat_moss" to "是否可以吃苔藓（物品）。注意：当按shift时无论此项是否开启都不会吃苔藓",
        "kriscraft.ui.tip.can_eat_moss_block" to "是否可以吃苔藓（方块）。注意：当按shift时无论此项是否开启都不会吃苔藓",
        "kriscraft.ui.tip.moss_heal" to "吃一个苔藓回复多少。大于等于20时吃苔藓有特殊提示",
        "kriscraft.ui.tip.moss_product_heal_base_scale" to "苔藓面包的回复倍率，详情见README"
    )
    val zhiZhanZhiShang = mutableMapOf(
        "kriscraft.the_sacrifice_of_stoping_wars1" to "雷德王还有3个小时降临地球",
        "kriscraft.the_sacrifice_of_stoping_wars2" to "汉堡不大，面包很干",
        "kriscraft.the_sacrifice_of_stoping_wars3" to "唯独让我牵挂难以放下执念的，便是学校的汉堡了",
        "kriscraft.the_sacrifice_of_stoping_wars4" to "看来无论何等高级的生命都无法抵御汉堡的诱惑",
        "kriscraft.the_sacrifice_of_stoping_wars5" to "可宇宙苍茫，谁又能主宰学校的汉堡呢?",
        "kriscraft.the_sacrifice_of_stoping_wars" to "吃 %s 个苔藓汉堡"
    )
    val damageType = mutableMapOf(
        "death.attack.negative" to $$"%1$s 发现苔藓是剧毒的",
        "death.attack.negative.player" to $$"%1$s 在 %2$s 的帮助下发现苔藓是剧毒的"
    )
    val key = mutableMapOf(
        "key.category.kriscraft.default" to "Kris工艺",
        "kriscraft.key.open" to "打开配置页面"
    )
    val command = mapOf(
        "kriscraft.config.command_executed" to $$"%1$s 被设为了 %2$s",
        "kriscraft.no_perm" to "权限不足无法同步至服务器。使用Esc退出以避免再次看到此提示"
    )
    val advMainLine = mapOf(
        "kris_root" to listOf(
            "Kris工艺",
            "使MC的苔藓更有用"
        ),
        "kris10" to listOf(
            "探藓家",
            "吃10个苔藓"
        ),
        "kris100" to listOf(
            "资深探藓家",
            "吃100个苔藓"
        ),
        "kris30000" to listOf(
            "Kris本尊",
            "日啖苔藓三万块，不辞长做MC人"
        )
    )
    val advSleepMoss = mapOf(
        "sleep_moss1" to listOf(
            "与藓同眠",
            "藓睡一觉，别的明天再说"
        ),
        "sleep_moss5" to listOf(
            "周一到周五",
            "可能是你运气不好还没找到羊"
        ),
        "sleep_moss31" to listOf(
            "新月的摇篮曲：伴藓同眠",
            "一个月过去了，看来这种子不好"
        ),
        "sleep_moss365" to listOf(
            "一周年了",
            "此处建议删档重开"
        )
    )
    val advEatMossBread = mapOf(
        "eat_moss_bread1" to listOf(
            "新藓的面包",
            "吃一块苔藓面包"
        ),
        "eat_moss_bread10" to listOf(
            "更多新藓的面包",
            "吃10块苔藓面包"
        ),
        "eat_moss_bread100" to listOf(
            "面包苔藓",
            "吃100块苔藓面包"
        ),
        "eat_moss_bread30000" to listOf(
            "Kris顶号了",
            "吃30000块苔藓面包"
        )
    )
    val advSuicide = mapOf(
        "neg_heal1" to listOf(
            "不如没有",
            "被反向治疗一次"
        ),
        "neg_heal10" to listOf(
            "屡教不改",
            "被反向治疗10次"
        ),
        "neg_heal_death" to listOf(
            "吃好喝好一路走好",
            "被苔藓的反向治疗治死"
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
                val key = "kriscraft.advancement.$i"
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
            key
        )
        builder.addAdvancement(
            advMainLine,
            advSleepMoss,
            advEatMossBread,
            advSuicide
        )
        builder.add(
            mossBread,
            "苔藓面包"
        )
        builder.add(
            mossBedBlock,
            "苔藓床"
        )
        builder.add(
            mossBurger,
            "苔藓汉堡"
        )
        builder.add(
            mossyEffect.value(),
            "苔藓"
        )
        builder.add(
            burgerEffect.value(),
            "汉堡"
        )
    }
}