package com.laolang.shop.config.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberModule extends SimpleModule {

    public NumberModule() {
        super(PackageVersion.VERSION);
        // BigDecimal 格式化
        this.addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>() {
            @Override
            public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                DecimalFormat format = new DecimalFormat("0.00");
                gen.writeString(format.format(value));
            }
        });
        // Long 格式化
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addSerializer(long.class, ToStringSerializer.instance);
    }
}

