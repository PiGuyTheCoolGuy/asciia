package app.game.world;

import java.util.List;

/**
 * This is a remplate for a real <code>MissionPackage</code>. It has all
 * attributes of <code>MissionPackage</code>, but all
 * attributes are public and there are no methods. It is temporary and only for
 * loading from JSON.
 */
public class RawMissionPackage {

    public String name;
    public String description;

    public List<RawWorldObject> worldObjects;
}
