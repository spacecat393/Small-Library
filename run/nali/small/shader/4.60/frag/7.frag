#version 460 core

uniform sampler2D texture_sampler;

in vec2 fragment_texcoord;

out vec4 fragColor;

void main()
{
    vec4 blur = vec4(0.0);
    for (int i = -2; i <= 2; ++i)
    {
        for (int j = -2; j <= 2; ++j)
        {
            blur += texture(texture_sampler, fragment_texcoord + vec2(i, j) / vec2(600, 400));
        }
    }

    blur /= 25.0;//5*5
    fragColor = vec4(blur.xyz, 1);
}

/*float weight[5] = float[](0.227027, 0.1945946, 0.1216216, 0.054054, 0.016216);
void main()
{
    vec2 tex_offset = 1.0 / textureSize(texture_sampler, 0);
    vec3 result = texture(texture_sampler, fragment_texcoord).rgb * weight[0];

    for (int i = 1; i < 5; ++i)
    {
        result += texture(texture_sampler, fragment_texcoord + vec2(tex_offset.x * i, 0.0)).rgb * weight[i];
        result += texture(texture_sampler, fragment_texcoord - vec2(tex_offset.x * i, 0.0)).rgb * weight[i];
        //result += texture(texture_sampler, fragment_texcoord + vec2(0.0, tex_offset.y * i)).rgb * weight[i];
        //result += texture(texture_sampler, fragment_texcoord - vec2(0.0, tex_offset.y * i)).rgb * weight[i];
    }
    result *= 0.8;
    fragColor = vec4(result, 1.0);
}*/

/*void main()
{
    vec2 tex_offset = 1.0 / vec2(800, 600);//resolution
    vec4 result = vec4(0.0);
    float total = 0.0;

    for (int x = -4; x <= 4; ++x)
    {
        for (int y = -4; y <= 4; ++y)
        {
            vec2 offset = vec2(float(x), float(y)) * tex_offset * 0.1;//blur_radius
            result += texture(texture_sampler, fragment_texcoord + offset);
            total += 1.0;
        }
    }

    result /= total;
    result *= 0.5;
    fragColor = result;
}*/