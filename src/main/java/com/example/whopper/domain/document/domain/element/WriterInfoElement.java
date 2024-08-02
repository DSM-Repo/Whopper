package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.student.domain.StudentEntity;
import lombok.Builder;
import org.bson.types.ObjectId;

import java.time.Year;
import java.util.Collections;
import java.util.Set;

@Builder
public record WriterInfoElement(
        String elementId,
        Integer generation,
        String email,
        String profileImagePath,
        //String major,
        Set<String> skillSet,
        Set<String> url
) {
    public WriterInfoElement {
        if (elementId == null) {
            elementId = new ObjectId().toHexString();
        }
    }

    private static final int ONE_GEN_YEAR = 2015;

    public static WriterInfoElement createEmptyElement(StudentEntity student) {
        return new WriterInfoElement(
                null,
                Year.now().getValue() - ONE_GEN_YEAR + 2 - student.getClassInfo().grade(),
                "",
                student.getProfileImagePath(),
                //"",
                Collections.emptySet(),
                Collections.emptySet()
        );
    }
}
