package org.lightning323.astral.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.lightning323.astral.Astral;

/**
 * Item tags owned by Astral Armoury (ported from Neko's ModTags).
 */
public class AstralItemTags {
    /** Dual-wield combo weapons (all sickles). */
    public static final TagKey<Item> SICKLES = TagKey.create(Registries.ITEM, Astral.resource("sickles"));
    /** Ammo accepted by the slingshot. */
    public static final TagKey<Item> SLINGSHOT_PROJECTILES =
            TagKey.create(Registries.ITEM, Astral.resource("slingshot_projectiles"));
}
