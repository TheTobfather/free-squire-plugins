package net.unethicalite.potdrinker.util

import net.runelite.api.ItemID
import net.runelite.api.Skill

var timeout: Int = 0
var startPlugin: Boolean = false

enum class Prayer(val type: String, vararg val ids: Int) {
    NONE("None"),
    PRAYER_POTION("Prayer potion",
            ItemID.PRAYER_POTION4,
            ItemID.PRAYER_POTION3,
            ItemID.PRAYER_POTION2,
            ItemID.PRAYER_POTION1
    ),
    SUPER_RESTORE("Super restore",
            ItemID.SUPER_RESTORE4,
            ItemID.SUPER_RESTORE3,
            ItemID.SUPER_RESTORE2,
            ItemID.SUPER_RESTORE1
    );
    override fun toString(): String {
        return type
    }
}

enum class BoostPotion(val type: String, val skill: Skill, vararg val ids: Int) {
    OVERLOAD(
            "Overload", Skill.ATTACK,
            ItemID.DIVINE_SUPER_COMBAT_POTION4,
            ItemID.DIVINE_SUPER_COMBAT_POTION3,
            ItemID.DIVINE_SUPER_COMBAT_POTION1,
            ItemID.DIVINE_SUPER_COMBAT_POTION2,

            ),
    SUPER_RANGING(
            "Super ranging",
            Skill.RANGED,
            ItemID.SUPER_RANGING_4,
            ItemID.SUPER_RANGING_3,
            ItemID.SUPER_RANGING_2,
            ItemID.SUPER_RANGING_1,
    ),
    SUPER_MAGIC_POTION("Super magic potion",
            Skill.MAGIC,
            ItemID.SUPER_MAGIC_POTION_4,
            ItemID.SUPER_MAGIC_POTION_3,
            ItemID.SUPER_MAGIC_POTION_2,
            ItemID.SUPER_MAGIC_POTION_1
    ),
    NONE("None", Skill.RANGED, -1);
    override fun toString(): String {
        return type
    }
}

enum class Spec(val type: String, val itemId: Int, val specAmt: Int, val hands: Int) {
    NONE("None", -1, 0, 0),
    BANDOS_GODSWORD("Bandos Godsword", ItemID.BANDOS_GODSWORD, 50, 2),
    GRANITE_MAUL("Granite maul", ItemID.GRANITE_MAUL, 60, 2),
    GRANITE_MAUL_OR("Granite maul OR", ItemID.GRANITE_MAUL_24225, 50, 2),
    DRAGON_WARHAMMER("Dragon Warhammer", ItemID.DRAGON_WARHAMMER, 50, 1),
    DRAGON_CLAWS("Dragon claws", ItemID.DRAGON_CLAWS, 50, 2),
    ZARYTE_CROSSBOW("Zaryte crossbow", ItemID.ZARYTE_CROSSBOW, 75, 1),
    BLOWPIPE_WIP("Blowpipe WIP", ItemID.TOXIC_BLOWPIPE, 50, 2);
    override fun toString(): String {
        return type
    }
}

