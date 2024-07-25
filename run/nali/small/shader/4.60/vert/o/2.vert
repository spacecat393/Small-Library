#version 460 core

in vec4 vertex;

uniform vec2 Pos;
//uniform mat4 ModelViewMatrix;
//uniform mat4 ProjectionMatrix;

out vec2 fragment_texcoord;

void main()
{
    /*mat4 m_ModelViewMatrix = ModelViewMatrix;
    m_ModelViewMatrix[0][3] = 0;
    m_ModelViewMatrix[1][3] = 0;
    m_ModelViewMatrix[3][0] = 0;
    m_ModelViewMatrix[3][1] = 0;*/
    //gl_Position = /*ProjectionMatrix * m_ModelViewMatrix * */vec4(vertex.xy + vec2(ModelViewMatrix[0][3], ModelViewMatrix[1][3]), 0.0, 1.0);
    gl_Position = vec4(vertex.xy + Pos, 0.0, 1.0);
    fragment_texcoord = vertex.zw;// + vec2(ModelViewMatrix[3][0], ModelViewMatrix[3][1]);// * vec2(ModelViewMatrix[0][0], ModelViewMatrix[1][1]);
}
