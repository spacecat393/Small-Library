uniform mat4 ProjectionMatrix;
uniform mat4 ModelViewMatrix;

out vec2 fragment_texcoord;
out vec3 fragment_normal;
//out vec2 screen_coords;

//invariant gl_Position;

void main()
{
//    vec4 vertex_v4 = vec4(0);
//    vec4 normal_v4 = vec4(0);
//
//    for (int i = 0; i < 4; ++i)
//    {
//        vec4 temp_vertex_v4 = vec4(vertex, 1.0);
//        vec4 temp_normal_v4 = vec4(normal, 0.0);
//
//        int j = 0;
//        float w = 0.0;
//
//        if (i == 0)
//        {
//            j = joint[0];
//            w = weight[0];
//        }
//        else if (i == 1)
//        {
//            j = joint[1];
//            w = weight[1];
//        }
//        else if (i == 2)
//        {
//            j = joint[2];
//            w = weight[2];
//        }
//        else if (i == 3)
//        {
//            j = joint[3];
//            w = weight[3];
//        }
