        temp_vertex_vec4 *= w;
        temp_normal_vec4 *= w;

        vertex_vec4 += temp_vertex_vec4;
        normal_vec4 += temp_normal_vec4;
    }

    gl_Position = ProjectionMatrix * ModelViewMatrix * vertex_vec4;
    fragment_normal = normals_vec4.xyz;
}
