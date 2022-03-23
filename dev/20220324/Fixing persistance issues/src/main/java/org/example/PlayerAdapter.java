package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.scene.image.Image;

import java.io.IOException;

public class PlayerAdapter extends TypeAdapter<Player> {

    public Player read(JsonReader reader) throws IOException {
        Player newpl = new Player();
        reader.beginObject();


        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            String fieldname = null;

            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
            }

            if ("playerNumber".equals(fieldname)) {
                token = reader.peek();
                newpl.setNumber(reader.nextInt());
            }

            if ("playerName".equals(fieldname)) {
                token = reader.peek();
                newpl.setName(reader.nextString());
            }

            if ("coordinate".equals(fieldname)) {
                token = reader.peek();
                String xy = reader.nextString();
                String[] parts = xy.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                newpl.setCoordinate(x, y);
            }

        }
        reader.endObject();
        return newpl;
    }

    public void write (JsonWriter writer, Player value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.beginObject();
        writer.name("playerNumber");
        writer.value(value.getPlayerNumber());
        writer.name("playerName");
        writer.value(value.getPlayerName());
        writer.name("coordinate");
        writer.value(value.getRowCoordinate() + "," + value.getColCoordinate());


        writer.endObject();
    }
}

