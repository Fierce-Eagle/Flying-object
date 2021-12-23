package com.company.engine.object;

import com.company.engine.Engine;
import com.company.engine.renderer.Texture;
import com.company.engine.renderer.VertexArrayObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;


public class GameObject
{
    private VertexArrayObject vertexArray;
    private Texture texture;

    protected Vector3f speed;
    protected Vector3f position;
    protected Vector3f center;
    protected Vector3f rotation;
    protected Vector3f scale;

    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale)
    {
        this.position = new Vector3f(position);
        this.rotation = new Vector3f(rotation);
        this.scale = new Vector3f(scale);
        this.speed = new Vector3f();
        center = new Vector3f();
        center.x = position.x - scale.x/2;
        center.y = position.y - scale.y/2;
        center.z = position.z - scale.z/2;

        Engine.gameObjects.add(this);
    }

    public void destroy()
    {
        if(vertexArray != null)
            vertexArray.destroy();

        Engine.gameObjects.remove(this);
    }

    public GameObject setModel(VertexArrayObject vertexArray)
    {
        this.vertexArray = vertexArray;
        return this;
    }

    public GameObject setTexture(Texture texture)
    {
        this.texture = texture;
        return this;
    }

    public Matrix4f getModelMatrix()
    {
        Matrix4f modelMatrix = new Matrix4f()
                .translate(position)
                .rotate(rotation.x, new Vector3f(1, 0, 0))
                .rotate(rotation.y, new Vector3f(0, 1, 0))
                .rotate(rotation.z, new Vector3f(0, 0, 1))
                .scale(scale);
        return modelMatrix;
    }

    public void motion(float speed) {
        getPosition().x += this.speed.x;
        getPosition().y += this.speed.y;
        getPosition().z += this.speed.z;

        getCenter().x += this.speed.x;
        getCenter().y += this.speed.y;
        getCenter().z += this.speed.z;

        if (Math.random() * 2 >= 1)
            this.getRotation().x += speed;
        if (Math.random() * 2 >= 1)
            this.getRotation().y += speed;
        if (Math.random() * 2 >= 1)
            this.getRotation().z += speed;

        if(this.getPosition().x >= 1f || this.getPosition().x <= -1f)
            this.speed.x *= -1;
        if(this.getPosition().y >= 1f || this.getPosition().y <= -1f)
            this.speed.y *= -1;


    }

    public VertexArrayObject getVertexArray()
    {
        return vertexArray;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public Vector3f getRotation()
    {
        return rotation;
    }

    public Vector3f getScale()
    {
        return scale;
    }

    public void setSpeed(float x, float y, float z) {
        speed.x = x;
        speed.y = y;
        speed.z = z;
    }

    public Vector3f getCenter() {
        return  center;
    }
}
