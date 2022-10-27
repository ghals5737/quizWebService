package com.example.quiz.global.error.handler;

import com.example.quiz.global.error.exception.CustomException;
import com.example.quiz.global.error.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = { CustomException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e, HttpServletRequest request) throws IOException {
        final ContentCachingRequestWrapper cachingRequestWrapper=(ContentCachingRequestWrapper) request;
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        StringBuilder sb=new StringBuilder();
        sb.append("===============================API REQUEST ERROR===============================\n")
                .append("Request Time: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss"))).append("\n")
                .append("Method: ").append(request.getMethod()).append("\n")
                .append("URL: ").append(request.getRequestURL()).append("\n")
                .append("URI: ").append(request.getRequestURI()).append("\n")
                .append("QueryString: ").append(request.getQueryString()==null?"":request.getQueryString()).append("\n")
                .append("Request Body: ").append(objectMapper.readTree(cachingRequestWrapper.getContentAsByteArray())).append("\n");

        log.error("handleCustomException Detail : {}", sb.toString());

        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
