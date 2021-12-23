package com.company.engine.object;

import org.joml.Vector3f;

public class PhysicsObject {

    public static boolean collisionObject(GameObject gm, GameObject gm1) {
        if (gm.equals(gm1))
            return false;

        Vector3f pos = gm.position;
        Vector3f pos1 = gm1.position;
        Vector3f scl = gm.scale;
        Vector3f scl1 = gm1.scale;


        // Перекрытие по оси x?
        boolean collisionX = Math.abs(pos.x - pos1.x) <= (scl1.x + scl.x)/2;

        // Перекрытие по оси y?
        boolean collisionY = Math.abs(pos.y - pos1.y) <= (scl1.y + scl.y)/2;

        // Если перекрытия происходят относительно обеих осей, то мы имеем столкновение
        return collisionX && collisionY;
    }

    public static void redirectionObject(GameObject gm, GameObject gm1) {
        if (collisionObject(gm, gm1)) {
            if (Math.abs(gm.speed.x) > Math.abs(gm.speed.y)) {

                gm1.speed.y *= -1; // НАДО ПЕРЕДЕЛАТЬ ЭТУ СТРОКУ
                gm.speed.y *= -1;

            } else {
                gm1.speed.x *= -1; // НАДО ПЕРЕДЕЛАТЬ ЭТУ СТРОКУ
                gm.speed.x *= -1;
            }

        }
    }
}
