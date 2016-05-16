package com.test.it.hadoop.avro;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;

import java.io.IOException;

/**
 * Created by caizh on 16-5-16.
 */
public class NcdcRecordParser {

    private TemperatureInfo temperatureInfo;

    public TemperatureInfo getTemperatureInfo() {
        return temperatureInfo;
    }

    public void setTemperatureInfo(TemperatureInfo temperatureInfo) {
        this.temperatureInfo = temperatureInfo;
    }

    public void parse(String line) throws IOException {
        GenericRecord record = new GenericData.Record(AvroGenericMaxTemperature.SCHEMA);
        DatumReader<GenericRecord> datumReader = new GenericDatumReader(AvroGenericMaxTemperature.SCHEMA);
        Decoder decoder = DecoderFactory.get().binaryDecoder(line.getBytes(), null);
        record = datumReader.read(record, decoder);
        temperatureInfo = new TemperatureInfo();
        temperatureInfo.setStationId((String)record.get("stationId"));
        temperatureInfo.setTemperature((Integer) record.get("temperature"));
        temperatureInfo.setYear((Integer) record.get("year"));
    }
}
