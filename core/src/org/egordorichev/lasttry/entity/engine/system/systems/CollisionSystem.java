package org.egordorichev.lasttry.entity.engine.system.systems;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessage;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.creature.AiComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Tile;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.util.collision.Collider;
import org.egordorichev.lasttry.util.struct.Pair;
import org.egordorichev.lasttry.util.struct.Pairs;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CollisionSystem implements System {
	/**
	 * The list of collidable entities
	 */
	private HashSet<Entity> entities = new HashSet<>();

	@Override
	public void update(float delta) {
		SizeComponent worldSize = World.instance.getComponent(SizeComponent.class);
		float width = worldSize.width * Chunk.SIZE * Tile.SIZE;
		float height = worldSize.height * Chunk.SIZE * Tile.SIZE;
		Set<Pair<Entity>> pairs = Pairs.setOf(this.entities);
		for (Pair<Entity> pair : pairs) {
			// Skip comparison to self
			if (pair.same()) {
				continue;
			}
			// Retrieve first entity information
			Entity first = pair.left;
			PositionComponent pos1 = first.getComponent(PositionComponent.class);
			SizeComponent size1 = first.getComponent(SizeComponent.class);
			// Skip comparison if missing the required components
			if (pos1 == null && size1 == null) {
				continue;
			}
			pos1.x = Math.max(Math.min(pos1.x, width - Tile.SIZE - size1.width), Tile.SIZE);
			pos1.y = Math.max(Math.min(pos1.y, height - Tile.SIZE - size1.height), Tile.SIZE);
			// Retrieve second entity information
			Entity second = pair.right;
			PositionComponent pos2 = second.getComponent(PositionComponent.class);
			SizeComponent size2 = second.getComponent(SizeComponent.class);
			// Skip comparison if missing the required components
			if (pos2 == null || size2 == null) {
				continue;
			}

			if (Collider.testAABB(pos1.x, pos1.y, size1.width, size1.height, pos2.x, pos2.y, size2.width,
				size2.height)) {

				AiComponent ai1 = first.getComponent(AiComponent.class);
				AiComponent ai2 = second.getComponent(AiComponent.class);

				if (ai1 != null) {
					ai1.ai.collide(first, second);
				}

				if (ai2 != null) {
					ai2.ai.collide(second, first);
				}

				if (ai1 != null || ai2 != null) {
					return; // So we don't check not updated list
				}
			}
		}
	}


	/**
	 * Handles adding new enemies
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(SystemMessage message) {
		if (Objects.equals(message.getType(), SystemMessage.Type.ENTITIES_UPDATED)) {
			this.entities = Engine.getWithAllTypes(CollisionComponent.class);
		}
	}
}