package ciyuanwutuobang;

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * @author 150149
 */
public class Replace {
    private static int X;
    private static int Y;

    static {
        X = OreTeleport.instance.X;
        Y = OreTeleport.instance.Y;
    }

    public static void ReplaceOre(Block block) {
        if ("COAL_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
            block.setType(Material.getMaterial("COAL_ORE"));
            OreTeleport.debug("替换煤成功" );
        }
        if ("DIAMOND_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
            block.setType(Material.getMaterial("DIAMOND_ORE"));
            OreTeleport.debug("替换钻石成功" );
        }
        if ("EMERALD_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
            block.setType(Material.getMaterial("EMERALD_ORE"));
            OreTeleport.debug("替换绿宝石成功" );
        }
        if ("GOLD_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
            block.setType(Material.getMaterial("GOLD_ORE"));
            OreTeleport.debug("替换金成功" );
        }
        if ("IRON_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
            block.setType(Material.getMaterial("IRON_ORE"));
            OreTeleport.debug("替换铁成功" );
        }
        if ("REDSTONE_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
            block.setType(Material.getMaterial("REDSTONE_ORE"));
            OreTeleport.debug("替换红石成功" );
        }
                                    /*
                                    for (ItemStack s:ores) {
                                        Collection<ItemStack> i = block.getLocation().add(X,0,Y).getBlock().getDrops();
                                        if (i.contains(s)) {
                                            //block.setType(Material.getMaterial(s.getTypeId()));
                                            //block.getLocation().add(X,0,Y).getBlock().getMetadata();

                                            block.setType((block.getLocation().add(X,0,Y).getBlock().getType()));
                                            OreTeleport.debug("替换" + block.getType().toString() +"成功" );
                                        }
                                    }
                                    */
    }

    public static void replaceStone(Block block) {
        OreTeleport.debug("对面的方块为:" + block.getLocation().getBlock().getType().toString());
        block.setType(Material.getMaterial("STONE"));
        OreTeleport.debug("替换石头成功" );
    }
    
}
