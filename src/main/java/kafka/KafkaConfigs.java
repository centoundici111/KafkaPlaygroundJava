package kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

interface KafkaConfigs {
     String BOOTSTRAP_SERVERS = "localhost:9092";
    String KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    String KEY_DESERIALIZER = StringDeserializer.class.getName();
    String VALUE_SERIALIZER = StringSerializer.class.getName();
    String VALUE_DESERIALIZER = StringDeserializer.class.getName();
    String OFFSET_RESET_EARLIER="earliest";
}
