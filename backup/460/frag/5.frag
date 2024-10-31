#version 460 core

//uniform sampler2D framebuffer_sampler;
uniform sampler2D texture_sampler;

uniform vec4 Color;

in vec2 fragment_texcoord;

out vec4 fragColor;

void main()
{
//    vec4 texture_color = texture(texture_sampler, fragment_texcoord);
//
//    texture_color *= Color;
//    if (texture_color.a == 0.0)
//    if (texture_color.a < 1 || Color.a < 1)
//    {
//        fragColor = vec4((texture_color.rgb + texture(framebuffer_sampler, gl_FragCoord.xy / vec2(1920, 1080)).rgb) / 2.0, texture_color.a);
////        //texture_color = vec4(1.0, 0.0, 0.0, 0.75);
////        fragColor = vec4(0, 0, 0, 0);
//////        discard;
//    }
//    else
//    {
//        fragColor = texture_color;
    fragColor = texture(texture_sampler, fragment_texcoord) * Color;
//    }
}
