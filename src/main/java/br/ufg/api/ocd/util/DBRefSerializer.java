package br.ufg.api.ocd.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class DBRefSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator generator, SerializerProvider provider)
            throws JsonGenerationException, IOException {

        provider.defaultSerializeValue(value, generator);
    }
}
