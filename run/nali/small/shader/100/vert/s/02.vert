    gl_Position = ProjectionMatrix * ModelViewMatrix * vertex_v4;
    fragment_normal = normals_v4.xyz;
}
