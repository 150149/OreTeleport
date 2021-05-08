package ciyuanwutuobang.Check;

import ciyuanwutuobang.OreTeleport;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * @author 150149
 */
public class CheckAirAndLiquid {
    
    public static boolean HasAirAndLiquid(Block block) {
        if (!"AIR".equals(block.getLocation().add(1,0,0).getBlock().getType().toString()) && !block.getLocation().add(1,0,0).getBlock().isLiquid()) {
            if (!"AIR".equals(block.getLocation().add(-1,0,0).getBlock().getType().toString()) && !block.getLocation().add(-1,0,0).getBlock().isLiquid()) {
                if (!"AIR".equals(block.getLocation().add(0,1,0).getBlock().getType().toString())&& !block.getLocation().add(0,1,0).getBlock().isLiquid()) {
                    if (!"AIR".equals(block.getLocation().add(0,-1,0).getBlock().getType().toString())&& !block.getLocation().add(0,-1,0).getBlock().isLiquid()) {
                        if (!"AIR".equals(block.getLocation().add(0,0,1).getBlock().getType().toString())&& !block.getLocation().add(0,0,1).getBlock().isLiquid()) {
                            if (!"AIR".equals(block.getLocation().add(0,0,-1).getBlock().getType().toString())&& !block.getLocation().add(0,0,-1).getBlock().isLiquid()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    
}
