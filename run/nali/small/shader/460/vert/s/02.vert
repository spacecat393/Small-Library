//        temp_vertex_v4 *= w;
//        temp_normal_v4 *= w;
//
//        vertex_v4 += temp_vertex_v4;
//        normal_v4 += temp_normal_v4;
//    }

    gl_Position = ProjectionMatrix * ModelViewMatrix * vertex_v4;
    fragment_normal = normals_v4.xyz;
}
