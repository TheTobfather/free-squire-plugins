package net.unethicalite.potdrinker

import net.runelite.api.ItemID
import net.runelite.client.config.*
import net.unethicalite.potdrinker.util.BoostPotion
import net.unethicalite.potdrinker.util.Prayer

@ConfigGroup("PotDrinkerConfig")
interface PotDrinkerConfig : Config {
    companion object {
        @ConfigSection(
                name = "General Configuration",
                description = "",
                position = 10,
                keyName = "generalSection",
                closedByDefault = true
        )
        const val generalSection: String = "General Configuration"

        @ConfigSection(
                name = "Prayer Configuration",
                description = "",
                position = 50,
                keyName = "prayerSection",
                closedByDefault = true
        )
        const val prayerSection: String = "Prayer Configuration"

        @ConfigSection(
                name = "Stop Configuration",
                description = "",
                position = 150,
                keyName = "stopSection",
                closedByDefault = true
        )
        const val stopSection: String = "Stop Configuration"

        @ConfigSection(
                name = "Debug Configuration",
                description = "",
                position = 1000,
                keyName = "debugSection",
                closedByDefault = true
        )
        const val debugSection: String = "Debug Configuration"
    }

    @ConfigItem(
            keyName = "boostType",
            name = "Boost Type",
            description = "Boost type to bring",
            position = 12,
            title = "boostType",
            section = generalSection
    )
    @JvmDefault
    fun boostType(): BoostPotion {
        return BoostPotion.OVERLOAD
    }

    @ConfigItem(
            keyName = "drinkAbsUnder",
            name = "Drink Absorption Under",
            description = "Absorption dose to drink under - set to 0 to not use absorptions",
            position = 14,
            title = "drinkAbsUnder",
            section = generalSection
    )
    @JvmDefault
    fun drinkAbsUnder(): Int {
        return 100
    }

    @ConfigItem(
            keyName = "useRockCake",
            name = "Use Rock Cake / Orb",
            description = "Uses rock cake to maintain low HP",
            position = 15,
            title = "useRockCake",
            section = generalSection
    )
    @JvmDefault
    fun useRockCake(): Boolean {
        return false
    }

    @ConfigItem(
            keyName = "prayerFlick",
            name = "Prayer flick",
            description = "Prayer flicks melee",
            position = 51,
            title = "prayerFlick",
            section = prayerSection
    )
    @JvmDefault
    fun prayerFlick(): Boolean {
        return true
    }
    @ConfigItem(
            keyName = "restoretype",
            name = "Restore Type",
            description = "Restore potions to bring",
            position = 52,
            title = "restoreType",
            section = prayerSection
    )
    @JvmDefault
    fun restoretype(): Prayer {
        return Prayer.PRAYER_POTION
    }

    @ConfigItem(
            keyName = "minPrayer",
            name = "Drink at",
            description = "Points to drink at",
            position = 53,
            title = "minPrayer",
            section = prayerSection
    )
    @JvmDefault
    fun minPrayer(): Int {
        return 15
    }

    @ConfigItem(
            keyName = "debugToggle",
            name = "Toggle debug",
            description = "Toggle debug in chat",
            position = 1001,
            title = "debugToggle",
            section = debugSection
    )
    @JvmDefault
    fun debugToggle(): Boolean {
        return false
    }

}


