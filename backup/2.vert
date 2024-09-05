#version 120

attribute vec3 vertex;
attribute vec3 normal;
attribute /*J*/ joint;
attribute /*J*/ weight;

uniform mat4 frame[/*Bx4*/];

uniform mat4 ProjectionMatrix;
uniform mat4 ModelViewMatrix;

varying vec3 fragment_normal;

void main()
{
    mat4 skin_mat4 =//weight * frame[int(joint)] +
    /*S0*/
    /*S1*/
    /*S2*/
    /*S3*/;

    gl_Position = ProjectionMatrix * ModelViewMatrix * skin_mat4 * vec4(vertex, 1.0);
    fragment_normal = (skin_mat4 * vec4(normal, 0.0)).xyz;
}
