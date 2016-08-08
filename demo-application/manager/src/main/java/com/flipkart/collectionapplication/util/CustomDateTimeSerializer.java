package com.flipkart.collectionapplication.util;

/**
 * Created by nitish.agarwal on 30/11/15.
 */
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class CustomDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private static DateTimeFormatter formatter =
            DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen,
                          SerializerProvider arg2)
        throws IOException, JsonProcessingException {

        gen.writeString(formatter.print(value));
    }
}

