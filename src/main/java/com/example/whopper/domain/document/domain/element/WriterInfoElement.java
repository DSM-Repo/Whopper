package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.document.domain.element.base.AbstractElement;
import com.example.whopper.domain.student.domain.StudentEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.Year;
import java.util.Collections;
import java.util.Set;

@Getter
public class WriterInfoElement extends AbstractElement {
    private final Integer generation;
    private final String email;
    private final String profileImagePath;
    private final Set<String> skillSet;
    private final String url;

    private static final int ONE_GEN_YEAR = 2015;

    @Builder
    public WriterInfoElement(String elementId, Integer generation, String email, String profileImagePath, Set<String> skillSet, String url) {
        super(elementId);
        this.generation = generation;
        this.email = email;
        this.profileImagePath = profileImagePath;
        this.skillSet = skillSet;
        this.url = url;
    }

    public static WriterInfoElement createEmptyElement(StudentEntity student) {
        return new WriterInfoElement(
                null,
                Year.now().getValue() - ONE_GEN_YEAR + 2 - student.getClassInfo().grade(),
                "",
                student.getProfileImagePath(),
                Collections.emptySet(),
                ""
        );
    }

    @Override
    public String getName() {
        return "기본 정보";
    }
}