package org.egordorichev.lasttry.world.biome;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;

public class Biome {
    public static Biome forest = new ForestBiome();
    public static Biome desert = new DesertBiome();
    public static Biome corruption = new CorruptionBiome();
    public static Biome crimson = new CrimsonBiome();
    public static Biome corruptDesert = new CorruptDesertBiome();
    public static Biome crimsonDesert = new CrimsonDesertBiome();

    /**
     * Enums used for identifying the Biome, of which a specific enemy spawns.
     */
    public enum Id{
        FOREST("Forest"), DESERT("Desert"), CORRUPTION("Corruption"), CRIMSON("Crimson"), CORRUPTDESERT("Corruptdesert"), CRIMSONDESERT("Crimsondesert");

        private String name;

        Id(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }


    /**
     * Background texture
     */
    protected Texture texture;

    /**
     * Enum representing Biome UniqueId.
     */
    protected Id id;


    /**
     * Texture alpha
     */
    private float alpha = 0;

    /**
     * Spawn info
     */
    private SpawnInfo spawnInfo;

    public Biome(Id id, SpawnInfo spawnInfo, Texture texture) {
        this.id = id;
        this.spawnInfo = spawnInfo;
        this.texture = texture;
    }

    public static void preload() {

    }

    /**
     * Animates background
     */
    public void fadeIn() {
        this.alpha = (Math.min(1, this.alpha + 0.01f));
    }

    /**
     * Animates background
     */
    public void fadeOut() {
        this.alpha = (Math.min(1, this.alpha - 0.01f));
    }

    /**
     * Shows, if animation is done
     *
     * @return animation is done
     */
    public boolean fadeInIsDone() {
        return this.alpha >= 0.99f;
    }

    /**
     * Shows, if animation is done
     *
     * @return animation is done
     */
    public boolean fadeOutIsDone() {
        return this.alpha < 0.01f;
    }

    /**
     * Returns biome name
     *
     * @return biome name
     */
    public Id getId() {
        return this.id;
    }

    /**
     * Renders backgrounds
     */
    public void renderBackground() {
        LastTry.batch.setColor(1, 1, 1, this.alpha);
        LastTry.batch.draw(this.texture, 0, 0);
        LastTry.batch.setColor(1, 1, 1, 1);
    }

    /**
     * Returns integer specifying the 'spawn rate'.
     * Spawn rate will be calculated as a '1' in 'Spawn Rate' chance of monster being spawned
     *
     * @return int representing spawn rate of the biome
     */
    public int getSpawnRate()
    {
        return spawnInfo.spawnRate;
    }

    /**
     * Returns integer specifying the maximum number of monster spawns, in a certain area close to the player
     *
     * @return int representing maximum spawn rate of a monster.
     */
    public int getSpawnMax()
    {
        return spawnInfo.spawnMax;
    }
}
