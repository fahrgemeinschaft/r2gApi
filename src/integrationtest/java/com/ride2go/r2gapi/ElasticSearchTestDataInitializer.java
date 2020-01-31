package com.ride2go.r2gapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ride2go.r2gapi.legacy.elastic.ElasticConstants;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ElasticSearchTestDataInitializer {

    private static final String DATA_PATH = "/elastic-data.json";

    JestClient jestClient;
    ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    void init() throws IOException {
        createIndex();
        insertData();
    }

    private void createIndex() throws IOException {
        jestClient.execute(new DeleteIndex.Builder(ElasticConstants.TRIP_INDEX_NAME)
                .build());

        jestClient.execute(
                new CreateIndex.Builder(ElasticConstants.TRIP_INDEX_NAME)
                        .settings(ElasticConstants.mappingDefinitions())
                        .build());
    }

    private void insertData() throws IOException {
        Bulk.Builder bulk = new Bulk.Builder();

        for (Map<String, Object> data : parseData()) {
            bulk.addAction(new Index.Builder(mapper.writeValueAsString(data))
                    .index(ElasticConstants.TRIP_INDEX_NAME)
                    .type(ElasticConstants.TRIP_TYPE_NAME)
                    .id(data.get(ElasticConstants.TRIP_ID_NAME).toString())
                    .build());
        }

        jestClient.execute(bulk.build());
    }

    private List<Map<String, Object>> parseData() throws IOException {
        try (InputStream is = getClass().getResourceAsStream(DATA_PATH)) {
            return mapper.readValue(is, new TypeReference<List<Map<String, Object>>>() {
            });
        }
    }

}
