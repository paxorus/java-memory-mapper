package fileSystem;
import java.util.Arrays;

import bytewise.Bytewise;

/**
 * Indirect pointers from an Inode lead to an IndirectBlock.
 *
 * An IndirectBlock is pointed to either by another indirect block or
 * by the indirect pointers in an Inode (pointers 10, 11, and 12). An
 * indirect block is packed with pointers, meaning that it holds
 * BLOCK_SIZE / POINTER_SIZE pointers.
 */
public class IndirectBlock implements Nestable {
    public static final int COUNT = Disk.BLOCK_SIZE / 4;
    public int ptr[] = new int[COUNT];
    private Disk disk;
    private int blockNum;

    public IndirectBlock(Disk disk, int blockNum, boolean fresh) {
    	this.disk = disk;
    	this.blockNum = blockNum;
    	
    	if (fresh) {
    		// allocating the disk space
    		save();
    	} else {
    		// load pre-existing indirect block
    		read();
    	}
    }
    
    // copy ptr[] to buffer, save to disk
    public void save() {
    	if (disk != null) {
    		disk.write(blockNum, Bytewise.intToByte(ptr));
    	}
    }
    
    // read disk into buffer, copy to ptr[] 
    public void read() {
    	byte[] buffer = new byte[4 * COUNT];
    	if (disk != null) {
    		disk.read(blockNum, buffer);
    	}
    	ptr = Bytewise.byteToInt(buffer);
    }

    public void clear() {
        for(int i = 0; i < COUNT; i++) {
            ptr[i] = 0;
        }
    }
    
    public String toString() {
        return
            "IndirectBlock(\n  " + Arrays.toString(ptr).replace(", ", "\n  ") +
            ")";
    }
    
    public int get(int index) {
    	return ptr[index];
    }
    
    public void set(int index, int pointer) {
    	ptr[index] = pointer;
		save();
    }
}
