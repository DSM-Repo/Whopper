package com.example.whopper.domain.library.application.component.impl;

import com.example.whopper.domain.library.application.component.ParseDocumentIndexComponent;
import com.example.whopper.domain.library.domain.DocumentIndex;
import com.example.whopper.global.utils.DataResponseInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ParseDocumentIndexComponentImpl implements ParseDocumentIndexComponent {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DataResponseInfo<DocumentIndex> parseDocumentIndex(MultipartFile indexPart) {
        try {
            JsonNode dataNode = objectMapper.readTree(indexPart.getBytes()).get("data");
            List<DocumentIndex> documentIndexes = convertToDocumentIndex(dataNode);
            return DataResponseInfo.of(documentIndexes);
        } catch (IOException e) {
            e.printStackTrace();
            return DataResponseInfo.of(new ArrayList<>()); // 빈 리스트 반환
        }
    }

    private List<DocumentIndex> convertToDocumentIndex(JsonNode dataNode) {
        List<DocumentIndex> documentIndexes = new ArrayList<>();
        int[] pageSum = {0};

        if (dataNode.isArray()) {
            for (JsonNode requestNode : dataNode) {
                String name = requestNode.get("name").asText();
                String major = requestNode.get("major").asText();
                int studentNumber = requestNode.get("studentNumber").asInt();
                int page = requestNode.get("page").asInt();

                pageSum[0] += page;
                documentIndexes.add(new DocumentIndex(name, major, studentNumber, pageSum[0] - page));
            }
        }
        return documentIndexes;
    }
}
