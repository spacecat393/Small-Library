#version 460 core

//uniform sampler2D framebuffer_sampler;
uniform sampler2D texture_sampler;

uniform vec4 Color;
uniform vec4 LightSourcePosition;

in vec2 fragment_texcoord;
in vec3 fragment_normal;
//in vec2 screen_coords;

out vec4 fragColor;

void main()
{
    vec4 texture_color = texture(texture_sampler, fragment_texcoord);
    //vec4 frame_color = vec4(1, 1, 1, 1);

//    if (texture_color.a == 0.0 || Color.a == 0.0)
//    {
////        discard;
//        fragColor = vec4(0, 0, 0, 0);
//    }
//    else
//    {
    vec3 light_dir = normalize(LightSourcePosition.xyz);
    float diffuse = max(dot(fragment_normal, light_dir), 0.0);

    vec3 rgb_color = texture_color.rgb;
    if (diffuse < 0.5)
    {
        rgb_color *= 0.9;
    }

//        if (texture_color.a < 1 || Color.a < 1)
//        {
//            fragColor = vec4((rgb_color * Color.rgb + texture(framebuffer_sampler, gl_FragCoord.xy / vec2(1920, 1080)).rgb) / 2.0, texture_color.a * Color.a);
//        }
//        else
//        {
    fragColor = vec4(rgb_color * Color.rgb, texture_color.a * Color.a);
//        }
//    }
    /*else if (texture_color.a != 1.0 || Color.a != 1.0)
    {
        frame_color = texture(framebuffer_sampler, screen_coords);
    }*/
}
