package process;

import config.Config;
import map.Map;


public class GameBuilder {

	public static Map buildMap() {
		return new Map(Config.nbLines, Config.nbColumns);
	}

	public static ElementManager buildInitMobile(Map map) {
		ElementManager manager = new ElementManager(map);
		return manager;
	}
}
