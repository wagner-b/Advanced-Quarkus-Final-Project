package tech.ada.dto;

import java.util.List;

public record CourseResponseDTO (
        Long id,
        String name,
        String description,
        List<LessonResponseDTO> lessons
) {
}
