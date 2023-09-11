package com.dninosores.pixelmonrepelblock;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class Utils {
    public static Vector3d getPosVec(BlockPos p) {
        return new Vector3d(p.getX(), p.getY(), p.getZ());
    }

    public static boolean inCube(double radius, Vector3d a, Vector3d b) {
        return Math.abs(a.x - b.x) <= radius && Math.abs(a.y - b.y) <= radius && Math.abs(a.z - b.z) <= radius;
    }
}
