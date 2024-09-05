#version 100
precision highp float;

uniform vec4 model_color;
uniform sampler2D lightmap_sampler;

uniform vec4 Color;
uniform vec4 LightSourcePosition;

uniform vec2 ligcoord;

varying vec3 fragment_normal;

void main()
{
    vec3 lightmap_color = vec3(0.0);
    if (ligcoord.x == -1.0)
    {
        lightmap_color = vec3(1.0, 1.0, 1.0);
    }
    else
    {
        lightmap_color = texture2D(lightmap_sampler, vec2(ligcoord.x, ligcoord.y)).rgb;
    }

    vec3 light_dir = normalize(LightSourcePosition.xyz);
    float diffuse = max(dot(fragment_normal, light_dir), 0.0);

    vec3 rgb_color = model_color.rgb;
    if (diffuse < 0.5)
    {
        rgb_color *= 0.9;
    }

    gl_FragColor = vec4(rgb_color * Color.rgb * lightmap_color, model_color.a * Color.a);
}
