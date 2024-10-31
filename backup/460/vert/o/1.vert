#version 460 core

in vec3 vertex;
in vec3 normal;

uniform mat4 ProjectionMatrix;
uniform mat4 ModelViewMatrix;

out vec3 fragment_normal;

void main()
{
    gl_Position = ProjectionMatrix * ModelViewMatrix * vec4(vertex, 1.0);
    fragment_normal = normal;
}
