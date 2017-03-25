package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.state.SplashState;
import org.egordorichev.lasttry.ui.UiManager;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.Environment;
import org.egordorichev.lasttry.world.World;

import java.util.Random;

public class LastTry extends Game {
	/** Camera */
	public static OrthographicCamera camera;

	/** Public sprite batch */
	public static SpriteBatch batch;

	/** Game viewport */
	public static Viewport viewport;

	/** Random instance */
	public static final Random random = new Random();

	/** Last Try instance */
	public static LastTry instance;

	/** Static log instance */
	public static Log log;

	/** Ui manager */
	public static UiManager ui;

	/** World instance */
	public static World world;

	/** Player instance */
	public static Player player;

	/** Environment instance */
	public static Environment environment;

	/** Entity manager instance */
	public static EntityManager entityManager;

	@Override
	public void create() {
		instance = this;

		Gdx.input.setInputProcessor(Util.multiplexer);
		Gdx.graphics.setTitle(this.getRandomWindowTitle());

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(true, width, height);
		viewport = new FitViewport(width, height);

		batch = new SpriteBatch();

		ui = new UiManager();

		this.setScreen(new SplashState());
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		viewport.update(width, height);
		camera.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);

		batch.enableBlending();
		batch.begin();
		super.render();
		batch.end();
	}

	public static void log(String message) {
		log.info(message);
	}

	public static int getMouseXInWorld() {
		return (int) (player.getX() - Gdx.graphics.getWidth() / 2 + Gdx.input.getX() + 14);
	}

	public static int getMouseYInWorld() {
		return (int) (player.getY() -  Gdx.graphics.getHeight() / 2 + Gdx.input.getY() + 20);
	}

	public static void handleException(Exception exception) {
		log.error(exception.getMessage());
		exception.printStackTrace();
	}

	private String getRandomWindowTitle() {
		return new String[] { "LastTry: Dig Peon, Dig!", "LastTry: Epic Dirt", "LastTry: Hey Guys!",
				"LastTry: Sand is Overpowered", "LastTry: Part 3: The Return of the Guide", "LastTry: A Bunnies Tale",
				"LastTry: Dr. Bones and The Temple of Blood Moon", "LastTry: Slimeassic Park",
				"LastTry: The Grass is Greener on This Side",
				"LastTry: Small Blocks, Not for Children Under the Age of 5", "LastTry: Digger T' Blocks",
				"LastTry: There is No Cow Layer", "LastTry: Suspicous Looking Eyeballs", "LastTry: Purple Grass!",
				"LastTry: Noone Dug Behind!", "LastTry: Shut Up and Dig Gaiden!", "LastTry: Java for ever!"
		}[random.nextInt(17)];
	}
}