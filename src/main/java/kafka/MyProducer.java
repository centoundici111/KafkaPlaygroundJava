package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {
    Properties kafkaProps = new Properties();


    public MyProducer() {
        kafkaProps.put("bootstrap.servers","localhost:9092");
        kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("acks", "all");
    }


    public void produce(){
        final KafkaProducer<String, String> producer =new KafkaProducer<>(kafkaProps);
        ProducerRecord<String, String> record =
                new ProducerRecord<>("test", "Precision Products",
                        "France");
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyProducer().produce();
    }
}
