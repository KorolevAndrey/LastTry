package org.egordorichev.lasttry.item.block.helpers;

import org.egordorichev.lasttry.util.ByteHelper;

public class BlockHelper {
	public static PlainBlockHelper plain = new PlainBlockHelper();
	public static NullBlockHelper empty = new NullBlockHelper();
	public static MTBHelper mtb = new MTBHelper();
	public static PlantHelper plant = new PlantHelper();

	public byte getHP(byte data) {
		return ByteHelper.getSum(data, (byte) 0, (byte) 1);
	}

	public byte setHP(byte data, byte hp) {
		data = ByteHelper.setBit(data, (byte) 0, ByteHelper.bitIsSet(hp, (byte) 0));
		data = ByteHelper.setBit(data, (byte) 1, ByteHelper.bitIsSet(hp, (byte) 1));

		return data;
	}

	public boolean isActivated(byte data) {
		return ByteHelper.bitIsSet(data, (byte) 7);
	}

	public byte setActivated(byte data, boolean activated) {
		return ByteHelper.setBit(data, (byte) 7, activated);
	}
}
