package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;
import java.util.UUID;

public class MyProducer {
    Properties kafkaProps = new Properties();


    public MyProducer() {
        kafkaProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProps.put(ProducerConfig.CLIENT_ID_CONFIG, "cliente1");
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "all");
    }


    public void produce() {
        final KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);
        ProducerRecord<String, String> record =
                new ProducerRecord<>("test",
                        UUID.randomUUID().toString(),
                        "France");
        try {
            RecordMetadata recordMetadata = producer.send(record).get();
            System.out.println("Record sent"+  " to partition " + recordMetadata.partition()
                    + " with offset " + recordMetadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyProducer().produce();
    }
}
