uniform mat4 ProjectionMatrix;
uniform mat4 ModelViewMatrix;

out vec3 fragment_normal;

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
//            j = int(joint[0]);
//            w = weight[0];
//        }
//        else if (i == 1)
//        {
//            j = int(joint[1]);
//            w = weight[1];
//        }
//        else if (i == 2)
//        {
//            j = int(joint[2]);
//            w = weight[2];
//        }
//        else if (i == 3)
//        {
//            j = int(joint[3]);
//            w = weight[3];
//        }
