#version 460 core

//uniform sampler2D framebuffer_sampler;

uniform vec4 Color;

out vec4 fragColor;

void main()
{
//    if (Color.a < 1)
//    {
//        fragColor = vec4((Color.rgb + texture(framebuffer_sampler, gl_FragCoord.xy / vec2(1920, 1080)).rgb) / 2.0, Color.a);
//    }
//    else
//    {
    fragColor = Color;
//    }
}
