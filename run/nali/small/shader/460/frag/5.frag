#version 460 core

uniform sampler2D texture_sampler;

uniform vec4 Color;

in vec2 fragment_texcoord;

out vec4 fragColor;

void main()
{
    vec4 texture_color = texture(texture_sampler, fragment_texcoord);

    texture_color *= Color;
    if (texture_color.a == 0.0)
    {
        //texture_color = vec4(1.0, 0.0, 0.0, 0.75);
        discard;
    }

    fragColor = texture_color;
}
