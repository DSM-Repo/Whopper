package com.repo.whopper.domain.file;

public record ImageInfo(
        String imagePath,
        String originalName
) {
    public static ImageInfo of(String imagePath, String originalName) {
        return new ImageInfo(imagePath, originalName);
    }
}
