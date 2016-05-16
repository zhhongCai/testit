package com.test.it.hadoop.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroCollector;
import org.apache.avro.mapred.AvroMapper;
import org.apache.avro.mapred.Pair;
import org.apache.avro.util.Utf8;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.Tool;

import java.io.IOException;

/**
 * Created by caizh on 16-5-16.
 */
public class AvroGenericMaxTemperature extends Configured implements Tool {
    public static final Schema SCHEMA = new Schema.Parser().parse("{\n" +
            "\"type\": \"record\"," +
            "\"name\": \"WeatherRecord\"," +
            "\"doc\": \"A weather reading.\"," +
            "\"fields\": [\n" +
            "{\"name\": \"year\", \"type\": \"int\"}," +
            "{\"name\": \"temperature\", \"type\": \"int\"}," +
            "{\"name\": \"stationId\", \"type\": \"string\"}" +
            "]" +
            "}");

    public static class MaxTemperatureMapper extends AvroMapper<Utf8, Pair<Integer, GenericRecord>> {
        private NcdcRecordParser parser = new NcdcRecordParser();
        private GenericRecord record = new GenericData.Record(SCHEMA);

        @Override
        public void map(Utf8 datum,
                        AvroCollector<Pair<Integer, GenericRecord>> collector,
                        Reporter reporter) throws IOException {

            super.map(datum, collector, reporter);
        }
    }


    @Override
    public int run(String[] strings) throws Exception {
        return 0;
    }
}
