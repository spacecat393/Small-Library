        temp_vertex_vec4 *= w;
        temp_normal_vec4 *= w;

        vertex_vec4 += temp_vertex_vec4;
        normal_vec4 += temp_normal_vec4;
    }

    gl_Position = ProjectionMatrix * ModelViewMatrix * vertex_vec4;
    fragment_texcoord = texcoord;
    fragment_normal = normal_vec4.xyz;
    //vec3 vxyz = gl_Position.xyz / gl_Position.w;
    //screen_coords = (vxyz.xy / vxyz.z) * 0.5 + 0.5;
}
