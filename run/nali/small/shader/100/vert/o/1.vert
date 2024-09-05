#version 100
precision highp float;

attribute vec3 vertex;
attribute vec3 normal;

uniform mat4 ProjectionMatrix;
uniform mat4 ModelViewMatrix;

varying vec3 fragment_normal;

void main()
{
    gl_Position = ProjectionMatrix * ModelViewMatrix * vec4(vertex, 1.0);
    fragment_normal = normal;
}
