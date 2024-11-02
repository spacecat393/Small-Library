#version 100
precision highp float;

attribute vec3 vertex;
attribute vec2 texcoord;
attribute vec3 normal;

uniform mat4 ProjectionMatrix;
uniform mat4 ModelViewMatrix;

varying vec2 fragment_texcoord;
varying vec3 fragment_normal;

void main()
{
	gl_Position = ProjectionMatrix * ModelViewMatrix * vec4(vertex, 1.0);
	fragment_texcoord = texcoord;
	fragment_normal = normal;
}
