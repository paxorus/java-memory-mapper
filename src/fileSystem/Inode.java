package fileSystem;
import java.util.Arrays;

/**
 * Each file in your file system is described by an <emph>index
 * node</emph> (Inode for short).
 *
 * Use Inodes to describe each file in your file system. Inodes are
 * used to find the blocks that belong to a file, and also contain
 * metadata about the file.
 */
public class Inode implements Nestable {
    public final static int SIZE = 64; // size in bytes
    public static int[] LIMITS = new int[4];
    public int flags;
    public int owner;
    public int size;
    private int ptr[] = new int[13];

    public void allocate() {
        flags = 1;
        owner = 0;
        size  = 0;
        Arrays.fill(ptr, 0);
        LIMITS[0] = 10;
        for (int i = 1; i < 4; i ++) {
        	LIMITS[i] = LIMITS[i - 1] + (int) Math.pow(IndirectBlock.COUNT, i);
        }
    }

    public String toString() {
        return
            "Inode(flags: " + flags +
            ", owner: " + owner +
            ", size: " + size +
            ", ptr: [" + Arrays.toString(ptr) + "])";
    }

    public int get(int index) {
    	return ptr[index];
    }
    
    public void set(int index, int pointer) {
    	ptr[index] = pointer;
    }
    
    public int size() {
    	return ptr.length;
    }
}
