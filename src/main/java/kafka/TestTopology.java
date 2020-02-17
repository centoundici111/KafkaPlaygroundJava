package kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class TestTopology {

    public static Topology getTopology() {
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> stream = builder.stream("KafkaPlayground1",
                Consumed.with(Serdes.String(), Serdes.String()));
        stream.peek(
                (K, V) ->
                        System.out.println("key " + K + "value " + V)
        ).map((K, V) ->
                new KeyValue<>(K, V.toUpperCase())
        ).peek(
                (K, V) ->
                        System.out.println("value " + V));

        return builder.build();
    }

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put(StreamsConfig.APPLICATION_ID_CONFIG, "StreamsConsumer");
        kafkaProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
                KafkaConfigs.BOOTSTRAP_SERVERS);

        KafkaStreams kafkaStreams = new KafkaStreams(getTopology(),
                kafkaProps);

        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }

}
