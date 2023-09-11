package com.dninosores.pixelmonrepelblock;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class Utils {
    public static Vector3d getPosVec(BlockPos p) {
        return new Vector3d(p.getX(), p.getY(), p.getZ());
    }
}
