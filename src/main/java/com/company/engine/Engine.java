package com.company.engine;

import java.util.ArrayList;
import java.util.List;

import com.company.engine.object.PhysicsObject;
import org.joml.Vector3f;

import com.company.engine.input.Keyboard;
import com.company.engine.input.Mouse;
import com.company.engine.object.GameObject;
import com.company.engine.renderer.BufferLayout;
import com.company.engine.renderer.IndexBufferObject;
import com.company.engine.renderer.RenderEngine;
import com.company.engine.renderer.Shader;
import com.company.engine.renderer.Texture;
import com.company.engine.renderer.VertexArrayObject;
import com.company.engine.renderer.VertexAttribute;
import com.company.engine.renderer.VertexAttribute.ShaderDataType;
import com.company.engine.renderer.VertexBufferObject;

public class Engine
{
    public static Engine instance;
    public static final int WIDHT = 1080;
    public static final int HEIGHT = 1080;
    public static final String TITLE = "Engine 0.3";
    private EngineWindow engineWindow;
    public Shader shader;
    public Texture texture;

    public static List<GameObject> gameObjects;

    public Engine()
    {
        instance = this;
    }

    public void run()
    {
        this.init();
    }

    public void init()
    {
        this.engineWindow = new EngineWindow(WIDHT, HEIGHT, TITLE);
        this.engineWindow.create();

        RenderEngine.init();
        Engine.gameObjects = new ArrayList<>();

        this.texture = new Texture("res/textures/logo_256x256.png");
        this.shader = new Shader("res/shaders/Rectangle.vert", "res/shaders/Rectangle.frag");
        this.shader.bind();
        this.shader.setUnifromInt("u_TextureSampler", 0);

        this.update();
    }

    public void update()
    {

        float speed = 0.01f;
        float[] vertices =
                {
//                     координаты           координаты текстуры

                        0, 0, 0,               0.25f, 0.25f,
                        1, 0, 0,               0.25f, 0.5f,
                        1, 0, 1,               0.5f, 0.25f,
                        0, 0, 1,               0.5f, 0.5f,
                        0, 1, 0,               0.25f, 0.25f,
                        1, 1, 0,               0.25f, 0.5f,
                        1, 1, 1,               0.5f, 0.25f,
                        0, 1, 1,               0.5f, 0.5f
                };

        int [] indices = {0, 1, 3, 1, 2, 3, // сторона кубика состоит из 2 треугольников
                          0, 5, 1, 0, 4, 5, // с помощью этих треугольников
                          3, 4, 0, 3, 7, 4, // можно создавать различные фигуры
                          1, 6, 2, 1, 5, 6, // Почему треугольники?
                          2, 7, 3, 2, 6, 7, // ТЕОРЕМА: три точки в пространстве, не лежащие на одной прямой,
                          4, 6, 5, 4, 7, 6};// образуют плоскость и при том только одну

        //создание модели квадрата
        VertexArrayObject vertexArray = new VertexArrayObject();
        VertexBufferObject vertexBuffer = new VertexBufferObject(vertices);
        vertexBuffer.setLayout(new BufferLayout
                (
                        new VertexAttribute("attrib_Position", ShaderDataType.t_float3),
                        new VertexAttribute("attrib_TextureCoord", ShaderDataType.t_float2)
                ));
        vertexArray.putBuffer(vertexBuffer);
        IndexBufferObject indexBuffer = new IndexBufferObject(indices);
        vertexArray.putBuffer(indexBuffer);



        //создание обьектов
        GameObject gameObject = new GameObject( // при создании данная хрень автоматом пихается в список
                new Vector3f(-0.5f,0.3f,0), // начальная позиция
                new Vector3f(0,0,0), // повороты
                new Vector3f(0.07f,0.07f,0.07f)); // размеры
        gameObject.setModel(vertexArray); // моделька (сюда можно пихать фигуру)
        gameObject.setTexture(texture); // если будет не впадлу сделаю несколько текстур
        gameObject.setSpeed(speed, speed/5, 0);

        GameObject gameObject2 = new GameObject(new Vector3f(-0.1f,0.3f,0), new Vector3f(0,0,0), new Vector3f(0.04f,0.04f, 0.04f));
        gameObject2.setModel(vertexArray);
        gameObject2.setTexture(texture);
        gameObject2.setSpeed(speed/2, speed/2, 0);

        GameObject gameObject3 = new GameObject(new Vector3f(0.5f,0.5f,0), new Vector3f(0,0,0), new Vector3f(0.05f,0.05f, 0.05f));
        gameObject3.setModel(vertexArray);
        gameObject3.setTexture(texture);
        gameObject3.setSpeed(speed/3, speed/2, 0);

        GameObject gameObject4 = new GameObject(new Vector3f(0.9f,0.3f,0), new Vector3f(0,0,0), new Vector3f(0.035f,0.035f, 0.035f));
        gameObject4.setModel(vertexArray);
        gameObject4.setTexture(texture);
        gameObject4.setSpeed(speed/3, -speed/5, 0);

        GameObject gameObject5 = new GameObject(new Vector3f(0.65f,0.7f,0), new Vector3f(0,0,0), new Vector3f(0.0245f,0.0245f, 0.0245f));
        gameObject5.setModel(vertexArray);
        gameObject5.setTexture(texture);
        gameObject5.setSpeed(-speed/3, speed, 0);

        GameObject gameObject6 = new GameObject(new Vector3f(0.5f,-0.7f,0), new Vector3f(0,0,0), new Vector3f(0.0315f,0.0315f, 0.0315f));
        gameObject6.setModel(vertexArray);
        gameObject6.setTexture(texture);
        gameObject6.setSpeed(speed/6, -speed/2, 0);

        GameObject gameObject7 = new GameObject(new Vector3f(0.8f,0.7f,0), new Vector3f(0,0,0), new Vector3f(0.055f,0.055f, 0.055f));
        gameObject7.setModel(vertexArray);
        gameObject7.setTexture(texture);
        gameObject7.setSpeed(speed/7, speed/5, 0);

        GameObject gameObject8 = new GameObject(new Vector3f(0.8f,-0.4f,0), new Vector3f(0,0,0), new Vector3f(0.035f,0.035f, 0.035f));
        gameObject8.setModel(vertexArray);
        gameObject8.setTexture(texture);
        gameObject8.setSpeed(-speed/4, speed, 0);

        GameObject gameObject9 = new GameObject(new Vector3f(-0.4f,0.42f,0), new Vector3f(0,0,0), new Vector3f(0.045f,0.045f, 0.045f));
        gameObject9.setModel(vertexArray);
        gameObject9.setTexture(texture);
        gameObject9.setSpeed(speed/2, -speed, 0);

        GameObject gameObject10 = new GameObject(new Vector3f(0.1f,-0.6f,0), new Vector3f(0,0,0), new Vector3f(0.04f,0.04f, 0.04f));
        gameObject10.setModel(vertexArray);
        gameObject10.setTexture(texture);
        gameObject10.setSpeed(speed/3, -speed/6, 0);

        while(!this.engineWindow.isCloseRequested())
        {
            Keyboard.handleKeyboardInput();
            Mouse.handleMouseInput();

            //рендеринг сцены.
            RenderEngine.begin(shader);
            {
                //рендеринг каждого обьекта добавленного в лист gameObjects.
                for(GameObject gm : gameObjects)
                {
                    for(GameObject gm1 : gameObjects)
                    {
                        PhysicsObject.redirectionObject(gm, gm1);
                    }
                    gm.motion(speed);
                    RenderEngine.renderGameObject(gm, shader);
                }
            }
            RenderEngine.end(shader);

            this.engineWindow.swapBuffers();
        }

        RenderEngine.destory();
        this.engineWindow.destroy();
    }

    public static Engine get()
    {
        return instance;
    }

    public EngineWindow getEngineWindow()
    {
        return this.engineWindow;
    }
}
