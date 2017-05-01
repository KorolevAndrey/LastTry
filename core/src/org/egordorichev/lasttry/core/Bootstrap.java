package org.egordorichev.lasttry.core;

import org.egordorichev.lasttry.entity.ai.AIs;
import org.egordorichev.lasttry.entity.enemy.Enemies;
import org.egordorichev.lasttry.item.Items;

public class Bootstrap {
	private static boolean loaded = false;

	public static boolean isLoaded() {
		return loaded;
	}

	public static void load() {
		if (loaded) {
			return;
		}

		loaded = true;

		Items.load();
		AIs.load();
		Enemies.load();
	}
}