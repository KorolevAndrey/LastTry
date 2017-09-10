package org.egordorichev.lasttry.crafting;

import com.badlogic.gdx.utils.JsonValue;

public class Recipe {
	
	/**
	 * Required crafting stations.
	 */
	private String[] stations;
	
	/**
	 * Required materials.
	 */
	private Holder[] materials;
	
	/**
	 * Result of crafting.
	 */
	private Holder result;

	private static class Holder {

		/**
		 * Item name.
		 */
		private String item;

		/**
		 * Amount required.
		 */
		private short count;

		/**
		 * Class constructor.
		 * @param item 
		 * @param count 
		 */
		Holder(String item, short count) {
			this.item = item;
			this.count = count;
		}

		public String getItem() {
			return item;
		}

		public short getCount() {
			return count;
		}
	}


	public Recipe() {

	}

	/**
	 * Loads recipe from json
	 *
	 * @param root Json root
	 * @throws Exception Exception containing parse error
	 */
	public static Recipe load(JsonValue root) throws Exception {
		Recipe recipe = new Recipe();

		if (root.has("stations")) {
			JsonValue stations = root.get("stations");
			recipe.stations = new String[stations.size];

			for (int i = 0; i < stations.size; i++) {
				recipe.stations[i] = stations.getString(i);
			}
		} else {
			recipe.stations = new String[0];
		}

		if (!root.has("materials") || !root.has("result")) {
			throw new Exception("Recipe must have materials and result fields");
		}

		JsonValue materials = root.get("materials");
		recipe.materials = new Holder[materials.size];

		for (int i = 0; i < materials.size; i++) {
			recipe.materials[i] = new Holder(materials.get(i).getString("id", "lt:dirt"), materials.get(i).getShort("count", (short) 1));
			
		}

		JsonValue result = root.get("result");

		recipe.result = new Holder(result.getString("id", "lt:dirt"), result.getShort("count", (short) 1));
		
		return recipe;
	}

	/**
	 * @return Recipe result
	 */
	public Holder getResult() {
		return this.result;
	}

	/**
	 * @return Recipe required materials
	 */
	public Holder[] getMaterials() {
		return this.materials;
	}

	/**
	 * @return Recipe required stations
	 */
	public String[] getStations() {
		return this.stations;
	}
}
