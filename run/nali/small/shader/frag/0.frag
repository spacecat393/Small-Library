#version 100
precision highp float;

uniform sampler2D Texture;
uniform sampler2D Map;

uniform vec4 Color;
//uniform vec4 LightSourcePosition;

uniform vec2 Light;

varying vec2 fragment_texcoord;
//varying vec3 fragment_normal;

void main()
{
	vec4 texture_color = texture2D(Texture, fragment_texcoord);

	vec4 lightmap_color;
	if (Light.x == -1.0)
	{
		lightmap_color = vec4(1.0);
	}
	else
	{
		lightmap_color = texture2D(Map, Light);
	}

//	vec3 light_dir = normalize(LightSourcePosition.xyz);
//	float diffuse = max(dot(fragment_normal, light_dir), 0.0);
//
//	vec3 rgb_color = texture_color.rgb;
//	if (diffuse < 0.5)
//	{
//		rgb_color *= 0.9;
//	}

	gl_FragColor = vec4(texture_color.rgb * Color.rgb * lightmap_color.rgb, texture_color.a * Color.a);
}
