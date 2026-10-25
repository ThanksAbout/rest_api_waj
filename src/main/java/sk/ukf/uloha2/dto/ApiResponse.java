package sk.ukf.uloha2.dto;

import java.time.Instant;
import java.util.List;


public record ApiResponse<T>(T data, String message, List<String> errors, Instant timestamp) {

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, List.of(), Instant.now());
    }

    public static <T> ApiResponse<T> error(String message, List<String> errors) {
        return new ApiResponse<>(null, message, errors, Instant.now());
    }
}
