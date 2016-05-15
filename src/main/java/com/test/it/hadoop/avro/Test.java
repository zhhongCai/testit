package com.test.it.hadoop.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by caizh on 16-5-15.
 */
public class Test {

    @org.junit.Test
    public void test() throws IOException {
        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(getClass().getResourceAsStream("/avro/StringPair.avsc"));
        GenericRecord datum = new GenericData.Record(schema);
        datum.put("left", "L");
        datum.put("right", "right");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(datum, encoder);
        encoder.flush();
        out.close();

        File file = new File("data.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(schema, file);
        for(int i = 0; i < 10; i++) {
            dataFileWriter.append(datum);
        }
        dataFileWriter.close();
        System.out.println(file.getAbsoluteFile());

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
        Schema readSchema = dataFileReader.getSchema();
        System.out.println(readSchema.toString(true));
        while(dataFileReader.hasNext()) {
            GenericRecord record = dataFileReader.next();
            System.out.println(record.toString());
        }
    }


}
