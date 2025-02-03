package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.QuestionRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.QuestionResponse;
import com.davibilapps.learnsmart.services.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Question ", description = "Endpoint of Question")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class QuestionApi {

   private final QuestionService questionService;

    @PostMapping("/question")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Question", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<QuestionResponse> saveQuestion(@RequestBody QuestionRequest questionRequest) {
        return ResponseEntity.ok(questionService.save(questionRequest));
    }

    @PutMapping("/question/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Question by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable UUID trackingId, @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(questionService.update(trackingId, request));
    }

    @DeleteMapping("/question/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Question by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteQuestiontByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.questionService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/question/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Question by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<QuestionResponse> getOneQuestion(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(questionService.getOne(trackingId));
    }

    @GetMapping("/question/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Question   All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<QuestionResponse>> allQuestion() {
        return ResponseEntity.ok(questionService.listAll());
    }

    @GetMapping("/question/all/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste   Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<QuestionResponse>> listAllByCours(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(questionService.listAllByCours(trackingId));
    }

}
