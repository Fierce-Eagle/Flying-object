#version 430 core

layout (location = 0) in vec3 attrib_Position;
layout (location = 1) in vec2 attrib_TextureCoord;

out vec3 position;
out vec2 textureCoord;

uniform mat4 u_ModelMatrix;

void main()
{
	position       = attrib_Position;
	textureCoord   = attrib_TextureCoord;

	gl_Position    = u_ModelMatrix * vec4(attrib_Position, 1.0f);
}