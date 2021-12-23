package com.company.engine.renderer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.stb.STBImage.*;
import static com.company.engine.util.MemoryManagment.*;
import static org.lwjgl.opengl.GL46C.*;

public class Texture
{
    private int id;
    private int width, height;
    private int format, internalFormat;
    private int channels;
    private ByteBuffer data;
    private String resource;

    public Texture(String resource)
    {
        this.resource = resource;

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer chan = BufferUtils.createIntBuffer(1);

        try
        {
            data = stbi_load_from_memory(resourceToByteBuffer(this.resource), w, h, chan, 0);

            this.width = w.get();
            this.height = h.get();
            this.channels = chan.get();
        }
        catch (IOException e) {e.printStackTrace();}

        if(this.channels == 4)
        {
            this.internalFormat = GL_RGBA8;
            this.format = GL_RGBA;
        }
        else if(this.channels == 3)
        {
            this.internalFormat = GL_RGB;
            this.format = GL_RGB;
        }

        this.id = glCreateTextures(GL_TEXTURE_2D);
        glTextureStorage2D(this.id, 1, this.internalFormat, this.width, this.height);

        glTextureParameteri(this.id, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTextureParameteri(this.id, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTextureParameteri(this.id, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
        glTextureParameteri(this.id, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);

        glTextureSubImage2D(this.id, 0, 0, 0, this.width, this.height, this.format, GL_UNSIGNED_BYTE, data);

        if(data != null)
            stbi_image_free(data);
    }

    public void bind()
    {
        glBindTexture(GL_TEXTURE_2D, this.id);
    }

    public void unBind()
    {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void destroy()
    {
        glDeleteTextures(this.id);
    }

    public int getId()
    {
        return id;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
    public int getChannels()
    {
        return channels;
    }

    public ByteBuffer getData()
    {
        return data;
    }

    public String getResource()
    {
        return resource;
    }

}
