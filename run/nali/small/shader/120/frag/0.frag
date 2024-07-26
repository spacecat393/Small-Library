#version 120

uniform sampler2D texture_sampler;
uniform sampler2D lightmap_sampler;

uniform vec4 Color;
uniform vec4 LightSourcePosition;

uniform vec2 ligcoord;

varying vec2 fragment_texcoord;
varying vec3 fragment_normal;

void main()
{
    vec4 texture_color = texture2D(texture_sampler, fragment_texcoord);

    if (texture_color.a == 0.0 || Color.a == 0.0)
    {
        discard;
    }

    vec3 lightmap_color = vec3(0);
    if (ligcoord.x == -1)
    {
        lightmap_color = vec3(1, 1, 1);
    }
    else
    {
        lightmap_color = texture2D(lightmap_sampler, vec2(ligcoord.x, ligcoord.y)).rgb;
    }

    vec3 light_dir = normalize(LightSourcePosition.xyz);
    float diffuse = max(dot(fragment_normal, light_dir), 0.0);

    vec3 rgb_color = texture_color.rgb;
    if (diffuse < 0.5)
    {
        rgb_color *= 0.9;
    }

    gl_FragColor = vec4(rgb_color * Color.rgb * lightmap_color, texture_color.a * Color.a);
}
