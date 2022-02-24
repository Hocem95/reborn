package map;

public class Block {
	private int line;
	private int column;
	private boolean available;

	public Block(int line, int column) {
		this.line = line;
		this.column = column;
		this.available = true;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public boolean equals(Block otherBlock) {
		if (this.getLine() == otherBlock.getLine() && this.getColumn() == otherBlock.getColumn()) {
			return true;
		}
		return false;
	}

	public Boolean isAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}
}
