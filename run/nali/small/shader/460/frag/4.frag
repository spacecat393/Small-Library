#version 460 core

uniform vec4 model_color;
//uniform sampler2D framebuffer_sampler;
uniform sampler2D lightmap_sampler;

uniform vec4 Color;
uniform vec4 LightSourcePosition;

uniform vec2 ligcoord;

in vec3 fragment_normal;

out vec4 fragColor;

void main()
{
    //    if (model_color.a == 0.0 || Color.a == 0.0)
    //    {
    ////        discard;
    //        fragColor = vec4(0, 0, 0, 0);
    //    }
    //    else
    //    {
    vec3 lightmap_color = vec3(0);
    if (ligcoord.x == -1)
    {
        lightmap_color = vec3(1, 1, 1);
    }
    else
    {
        lightmap_color = texture(lightmap_sampler, vec2(ligcoord.x, ligcoord.y)).rgb;
    }

    vec3 light_dir = normalize(LightSourcePosition.xyz);
    float diffuse = max(dot(fragment_normal, light_dir), 0.0);

    vec3 rgb_color = model_color.rgb;
    if (diffuse < 0.5)
    {
        rgb_color *= 0.9;
    }

//    if (model_color.a < 1 || Color.a < 1)
//    {
//        fragColor = vec4((rgb_color * Color.rgb * lightmap_color + texture(framebuffer_sampler, gl_FragCoord.xy / vec2(1920, 1080)).rgb) / 2.0, model_color.a * Color.a);
//    }
//    else
//    {
    fragColor = vec4(rgb_color * Color.rgb * lightmap_color, model_color.a * Color.a);
//    }
    //    }
}
