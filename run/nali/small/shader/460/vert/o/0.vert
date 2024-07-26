#version 460 core

in vec3 vertex;
in vec2 texcoord;
in vec3 normal;

uniform mat4 ProjectionMatrix;
uniform mat4 ModelViewMatrix;

out vec2 fragment_texcoord;
out vec3 fragment_normal;
//out vec2 screen_coords;

void main()
{
    gl_Position = ProjectionMatrix * ModelViewMatrix * vec4(vertex, 1.0);
    fragment_texcoord = texcoord;
    fragment_normal = normal;
    //vec3 vxyz = gl_Position.xyz / gl_Position.w;
    //screen_coords = (vxyz.xy / vxyz.z) * 0.5 + 0.5;
}
