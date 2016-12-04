package bytewise;

public class Bytewise {
	
	// All conversions assume Big-Endian order.
		
	public static int[] byteToInt(byte[] bytes) {
		int[] ints = new int[bytes.length / 4];
		for (int i = 0; i < ints.length; i ++) {
			ints[i] = (bytes[4 * i + 3] & 0xFF) | ((bytes[4* i + 2] & 0xFF) << 8) |
	                  ((bytes[4 * i + 1] & 0xFF) << 16) | ((bytes[4 * i] & 0xFF) << 24);
		}
		return ints;
	}
	
	public static byte[] intToByte(int[] ints) {
		byte[] bytes = new byte[ints.length * 4];
		for (int i = 0; i < ints.length; i ++) {
			bytes[4 * i] = (byte) (ints[i] >>> 24);
			bytes[4 * i + 1] = (byte) (ints[i] >>> 16);
			bytes[4 * i + 2] = (byte) (ints[i] >>> 8);
			bytes[4 * i + 3] = (byte) ints[i];
		}
		return bytes;
	}
}
