#version 460 core

uniform vec4 model_color;

uniform vec4 Color;
uniform vec4 LightSourcePosition;

in vec3 fragment_normal;

out vec4 fragColor;

void main()
{
    if (model_color.a == 0.0 || Color.a == 0.0)
    {
        discard;
    }

    vec3 light_dir = normalize(LightSourcePosition.xyz);
    float diffuse = max(dot(fragment_normal, light_dir), 0.0);

    vec3 rgb_color = model_color.rgb;
    if (diffuse < 0.5)
    {
        rgb_color *= 0.9;
    }

    fragColor = vec4(rgb_color * Color.rgb, model_color.a * Color.a);
}
