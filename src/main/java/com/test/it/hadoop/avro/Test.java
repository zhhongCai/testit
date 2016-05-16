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
import org.apache.avro.util.Utf8;

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
        dataFileWriter.append(datum);
        dataFileWriter.close();
        System.out.println(file.getAbsoluteFile());

        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader);
        Schema readSchema = dataFileReader.getSchema();
        System.out.println(readSchema.toString(true));
        while(dataFileReader.hasNext()) {
            GenericRecord record = dataFileReader.next();
            System.out.println(record.toString());
        }
    }

    @org.junit.Test
    public void genData() throws IOException {
        GenericRecord datum = new GenericData.Record(AvroGenericMaxTemperature.SCHEMA);
        datum.put("year", 1988);
        datum.put("temperature", 1988);
        datum.put("stationId", "rxxxxt");

        File file = new File("temperature.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(AvroGenericMaxTemperature.SCHEMA);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(AvroGenericMaxTemperature.SCHEMA, file);
        dataFileWriter.append(datum);
        dataFileWriter.close();
        System.out.println(file.getAbsoluteFile());
    }

    @org.junit.Test
    public void testNcdcRecordParser() throws IOException {
        GenericRecord datum = new GenericData.Record(AvroGenericMaxTemperature.SCHEMA);
        datum.put("year", 1988);
        datum.put("temperature", 1988);
        datum.put("stationId", "rxxxxt");

        NcdcRecordParser parser = new NcdcRecordParser();
        parser.parse(new Utf8().toString());
        System.out.println(parser.getTemperatureInfo().toString());

    }


}
