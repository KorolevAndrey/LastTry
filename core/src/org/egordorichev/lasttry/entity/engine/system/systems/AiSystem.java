package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessage;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.creature.AiComponent;

import java.util.HashSet;
import java.util.Objects;

/**
 * Handles creatures AI
 */
public class AiSystem implements System {
	/**
	 * List of entities, that have AI
	 */
	private HashSet<Entity> entities = new HashSet<>();

	/**
	 * Handles input
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		for (Entity entity : this.entities) {
			AiComponent ai = entity.getComponent(AiComponent.class);
			ai.ai.update(ai.getEntity());
		}
	}

	/**
	 * Handles in-coming messages
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(SystemMessage message) {
		if (Objects.equals(message.getType(), SystemMessage.Type.ENTITIES_UPDATED)) {
			this.entities = Engine.getWithAllTypes(AiComponent.class);
		}
	}
}