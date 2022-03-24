package objet;

import map.Block;

public abstract class Element {
	
		private Block position;
		private boolean randomMoves = true;

		public Element(Block position) {
			this.position = position;
			position.setAvailable(false);
		}

		public Block getPosition() {
			return position;
		}

		public void setPosition(Block position) {
			this.position.setAvailable(true);
			this.position = position;
			this.position.setAvailable(false);
		}

		public boolean getState() {
			return randomMoves;
		}
		
		public void setState(boolean state) {
			this.randomMoves = state;
		}
}
