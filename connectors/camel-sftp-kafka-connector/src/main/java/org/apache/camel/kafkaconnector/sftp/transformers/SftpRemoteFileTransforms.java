package org.apache.camel.kafkaconnector.sftp.transformers;

import java.util.Map;

import org.apache.camel.component.file.remote.RemoteFile;
import org.apache.camel.kafkaconnector.utils.SchemaHelper;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SftpRemoteFileTransforms <R extends ConnectRecord<R>> implements Transformation<R> {
    public static final String FIELD_KEY_CONFIG = "key";
    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(FIELD_KEY_CONFIG, ConfigDef.Type.STRING, null, ConfigDef.Importance.MEDIUM,
                    "Transforms Remote File to String");

    private static final Logger LOG = LoggerFactory.getLogger(SftpRemoteFileTransforms.class);

    @Override
    public R apply(R r) {
        Object value = r.value();

        if (r.value() instanceof RemoteFile) {
            LOG.debug("Converting record from RemoteFile to text");
            RemoteFile message = (RemoteFile) r.value();

            LOG.debug("Received text: {}", message.getBody());

            return r.newRecord(r.topic(), r.kafkaPartition(), null, r.key(),
                    SchemaHelper.buildSchemaBuilderForType(message.getBody()), message.getBody(), r.timestamp());

        } else {
            LOG.debug("Unexpected message type: {}", r.value().getClass());

            return r;
        }
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
