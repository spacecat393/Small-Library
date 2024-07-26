//        temp_vertex_v4 *= w;
//        temp_normal_v4 *= w;
//
//        vertex_v4 += temp_vertex_v4;
//        normal_v4 += temp_normal_v4;
//    }

    gl_Position = ProjectionMatrix * ModelViewMatrix * vertex_v4;
    fragment_texcoord = texcoord;
    fragment_normal = normal_v4.xyz;
    //vec3 vxyz = gl_Position.xyz / gl_Position.w;
    //screen_coords = (vxyz.xy / vxyz.z) * 0.5 + 0.5;
}
