package net.unethicalite.potdrinker

import com.google.inject.Provides
import net.runelite.api.*
import net.runelite.api.events.ConfigButtonClicked
import net.runelite.api.events.GameTick
import net.runelite.client.config.ConfigManager
import net.runelite.client.eventbus.Subscribe
import net.runelite.client.plugins.PluginDescriptor
import net.unethicalite.api.commons.Time
import net.unethicalite.api.entities.NPCs
import net.unethicalite.api.entities.Players
import net.unethicalite.api.game.Combat
import net.unethicalite.api.game.Skills
import net.unethicalite.api.game.Vars
import net.unethicalite.api.items.Bank
import net.unethicalite.api.items.Inventory
import net.unethicalite.api.plugins.LoopedPlugin
import net.unethicalite.api.utils.MessageUtils
import net.unethicalite.api.widgets.Dialog
import net.unethicalite.api.widgets.Prayers
import net.unethicalite.api.widgets.Production
import net.unethicalite.client.Static
import net.unethicalite.potdrinker.util.*
import net.unethicalite.potdrinker.util.Prayer
import org.pf4j.Extension
import java.time.Duration
import java.time.Instant
import javax.inject.Inject

@Extension
@PluginDescriptor(
    name = "Quick NMZ",
    description = "Automatic pot drinker",
    tags = ["pot"]
)
class PotDrinkerPlugin : LoopedPlugin() {

    @Inject
    lateinit var config: PotDrinkerConfig

    @Inject
    lateinit var functions: Functions

    @Inject
    lateinit var calculation: Calculation

    @Inject
    lateinit var client: Client

    @Inject
    lateinit var chinBreakHandler: ReflectBreakHandler

    var sleepLength: Long = -1

    private var startTime: Instant = Instant.now()

    private val runtime: Duration get() = Duration.between(startTime, Instant.now())

    var startPlugin: Boolean = false

    companion object : Log()

    @Provides
    fun provideConfig(configManager: ConfigManager): PotDrinkerConfig {
        return configManager.getConfig(PotDrinkerConfig::class.java)
    }


    override fun startUp() {
        log.info("${this::class.simpleName} started at $startTime")
        chinBreakHandler.registerPlugin(this)
        reset()
    }

    override fun shutDown() {
        log.info("${this::class.simpleName} stopped at ${Instant.now()} with runtime $runtime")
        chinBreakHandler.unregisterPlugin(this)
        reset()
    }

    override fun loop(): Int {
        if (chinBreakHandler.isBreakActive(this)) return 100

        if (client.isInInstancedRegion)
        {
            if (config.restoretype() != Prayer.NONE && Prayers.getPoints() <= 5 && Inventory.contains(*config.restoretype().ids))
            {
                var item: Item? = Inventory.getFirst(*config.restoretype().ids)
                if (item != null)
                {
                    item.interact("Drink")
                    return -3
                }
            }
            if (config.drinkAbsUnder() > 0 && Vars.getBit(Varbits.NMZ_ABSORPTION) <= config.drinkAbsUnder() && Inventory.contains() {it.name.contains("Absorption")})
            {
                var item: Item? = Inventory.getFirst { it.name.contains("Absorption") }
                if (item != null)
                {
                    item.interact("Drink")
                    return -3
                }
            }
            if (config.boostType() == BoostPotion.OVERLOAD && Inventory.contains() {it.name.contains("Overload")})
            {
                if (client.getVarbitValue(3955) == 0 && Combat.getCurrentHealth() >= 51)
                {
                    var item: Item? = Inventory.getFirst { it.name.contains("Overload") }
                    if (item != null)
                    {
                        item.interact("Drink")
                        return -3
                    }
                }
            }
            if (config.useRockCake())
            {
                if (Combat.getCurrentHealth() > 1 && (config.boostType() == BoostPotion.NONE || client.getVarbitValue(3955) != 0) && (config.drinkAbsUnder() == 0 || Vars.getBit(Varbits.NMZ_ABSORPTION) >= Combat.getCurrentHealth() - 1))
                {
                    var item: Item? = Inventory.getFirst { it.name.contains("rock cake") || it.name.contains("Locator orb")}
                    if (item != null)
                    {
                        item.interact("Guzzle", "Feel")
                        return -1
                    }
                }
            }
        }
        return -1
    }

    @Subscribe
    private fun onGameTick(gameTick: GameTick){
        if (chinBreakHandler.isBreakActive(this)) return

        if (client.isInInstancedRegion && config.prayerFlick())
        {
            if (Prayers.isQuickPrayerEnabled())
            {
                Prayers.toggleQuickPrayer(false)
            }
            Prayers.toggleQuickPrayer(true)
        }
        else
        {
            if (Prayers.anyActive())
                Prayers.toggleQuickPrayer(false)
        }

    }

    private fun reset() {
        sleepLength = -1
        startPlugin = false
    }

    @Subscribe
    private fun onConfigButtonPressed(configButtonClicked: ConfigButtonClicked) {
        if (!configButtonClicked.group.equals("PotDrinkerConfig", ignoreCase = true) || Static.getClient().gameState != GameState.LOGGED_IN || Players.getLocal() == null) return

    }

}