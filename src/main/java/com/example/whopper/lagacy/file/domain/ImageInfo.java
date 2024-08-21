package com.example.whopper.lagacy.file.domain;

public record ImageInfo(
        String imagePath,
        String originalName
) {
    public static ImageInfo of(String imagePath, String originalName) {
        return new ImageInfo(imagePath, originalName);
    }
}
