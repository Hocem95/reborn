package obstacles;

import map.Block;

public abstract class Element {
	
		private Block position;
		private boolean randomMoves = true;

		public Element(Block position) {
			this.position = position;
		}

		public Block getPosition() {
			return position;
		}

		public void setPosition(Block position) {
			this.position = position;
		}

		public boolean getState() {
			return randomMoves;
		}
		
		public void setState(boolean state) {
			this.randomMoves = state;
		}
}
