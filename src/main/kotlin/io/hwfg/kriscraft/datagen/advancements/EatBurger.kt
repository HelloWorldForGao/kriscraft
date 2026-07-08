package io.hwfg.kriscraft.datagen.advancements

import io.hwfg.kriscraft.Core
import io.hwfg.kriscraft.eatMossBurgerCriterion
import io.hwfg.kriscraft.mossBurger
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.core.HolderLookup
import net.minecraft.data.advancements.AdvancementSubProvider
import net.minecraft.network.chat.Component
import java.util.Optional
import java.util.function.Consumer

class EatBurger : AdvancementSubProvider {
    companion object {
        lateinit var burger1 : AdvancementHolder
        lateinit var burger10 : AdvancementHolder
        lateinit var burger100 : AdvancementHolder
        lateinit var burger1000 : AdvancementHolder
    }
    override fun generate(
        registries: HolderLookup.Provider,
        output: Consumer<AdvancementHolder>
    ) {
        burger1 = Advancement.Builder
            .advancement()
            .parent(SleepMoss.sleep1)
            .display(
                mossBurger,
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars2"),
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars",1),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "how_can_you_see_this",
                eatMossBurgerCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        1
                    )
                )
            )
            .save(
                output,
                "kriscraft:burger1"
            )
        burger10 = Advancement.Builder
            .advancement()
            .parent(burger1)
            .display(
                mossBurger,
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars3"),
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars",10),
                null,
                AdvancementType.TASK,
                true,
                true,
                false
            )
            .addCriterion(
                "how_can_you_see_this",
                eatMossBurgerCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        10
                    )
                )
            )
            .save(
                output,
                "kriscraft:burger10"
            )
        burger100 = Advancement.Builder
            .advancement()
            .parent(burger10)
            .display(
                mossBurger,
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars4"),
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars",100),
                null,
                AdvancementType.GOAL,
                true,
                true,
                true
            )
            .addCriterion(
                "how_can_you_see_this",
                eatMossBurgerCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        100
                    )
                )
            )
            .save(
                output,
                "kriscraft:burger100"
            )
        burger1000 = Advancement.Builder
            .advancement()
            .parent(burger100)
            .display(
                mossBurger,
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars5"),
                Component.translatable("kriscraft.the_sacrifice_of_stoping_wars",1000),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                true
            )
            .addCriterion(
                "how_can_you_see_this",
                eatMossBurgerCriterion.createCriterion(
                    Core.CountableCondition(
                        Optional.empty(),
                        1000
                    )
                )
            )
            .save(
                output,
                "kriscraft:burger1000"
            )
    }
}