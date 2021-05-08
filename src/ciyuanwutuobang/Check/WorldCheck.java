package ciyuanwutuobang.Check;

import ciyuanwutuobang.OreTeleport;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * @author 150149
 */
public class WorldCheck {

    public static boolean IsInWorld(Block block){
        World w = block.getWorld();
        for (String s: OreTeleport.instance.NotWorld) {
            if (w.getName().contains(s)) {
                return false;
            }
        }
        return true;
    }

}
