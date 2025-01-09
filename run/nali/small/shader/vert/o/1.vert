#version 100
precision highp float;

attribute vec3 vertex;
//attribute vec3 normal;

uniform mat4 Project;
uniform mat4 View;
//uniform vec4 Color;
//uniform sampler2D LightTexture;

//varying vec3 fragment_normal;

void main()
{
	gl_Position = Project * View * vec4(vertex, 1.0);
//	fragment_normal = normal;
//	gl_FrontColor = LightTexture * Color;
}
