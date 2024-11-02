	gl_Position = ProjectionMatrix * ModelViewMatrix * vertex_v4;
	fragment_texcoord = texcoord;
	fragment_normal = normal_v4.xyz;
}
