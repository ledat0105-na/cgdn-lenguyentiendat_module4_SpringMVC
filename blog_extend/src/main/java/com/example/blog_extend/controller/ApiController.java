package com.example.blog_extend.controller;

import com.example.blog_extend.entity.Post;
import com.example.blog_extend.repository.CategoryRepository;
import com.example.blog_extend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final CategoryRepository categoryRepo;
    private final PostRepository postRepo;

    // Simple DTOs to avoid lazy serialization issues
    public record CategoryDto(Long id, String name) {}

    public record PostDto(Long id,
                          String title,
                          String content,
                          Long categoryId,
                          String categoryName,
                          LocalDateTime createdAt,
                          LocalDateTime updatedAt) {
        public static PostDto from(Post p) {
            return new PostDto(
                    p.getId(),
                    p.getTitle(),
                    p.getContent(),
                    p.getCategory() != null ? p.getCategory().getId() : null,
                    p.getCategory() != null ? p.getCategory().getName() : null,
                    p.getCreatedAt(),
                    p.getUpdatedAt()
            );
        }
    }

    public record PageResponse<T>(List<T> content,
                                  int page,
                                  int size,
                                  long totalElements,
                                  int totalPages,
                                  boolean first,
                                  boolean last) {
        public static <T> PageResponse<T> of(Page<T> page) {
            return new PageResponse<>(
                    page.getContent(),
                    page.getNumber(),
                    page.getSize(),
                    page.getTotalElements(),
                    page.getTotalPages(),
                    page.isFirst(),
                    page.isLast()
            );
        }
    }

    // --- Categories ---
    @GetMapping("/categories")
    public List<CategoryDto> listCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(c -> new CategoryDto(c.getId(), c.getName()))
                .toList();
    }

    // --- Posts ---
    @GetMapping("/posts")
    public PageResponse<PostDto> listPosts(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size,
                                           @RequestParam(required = false) String q,
                                           @RequestParam(name = "cat", required = false) Long categoryId,
                                           @RequestParam(defaultValue = "desc") String dir) {
        Sort sort = Sort.by("createdAt");
        sort = "asc".equalsIgnoreCase(dir) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Post> postPage;
        if (q != null && !q.isBlank()) {
            postPage = postRepo.findByTitleContainingIgnoreCase(q.trim(), pageable);
        } else if (categoryId != null) {
            postPage = postRepo.findByCategory_Id(categoryId, pageable);
        } else {
            postPage = postRepo.findAll(pageable);
        }

        Page<PostDto> dtoPage = postPage.map(PostDto::from);
        return PageResponse.of(dtoPage);
    }

    @GetMapping("/categories/{id}/posts")
    public PageResponse<PostDto> listPostsByCategory(@PathVariable("id") Long categoryId,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size,
                                                     @RequestParam(defaultValue = "desc") String dir) {
        Sort sort = Sort.by("createdAt");
        sort = "asc".equalsIgnoreCase(dir) ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PostDto> dtoPage = postRepo.findByCategory_Id(categoryId, pageable).map(PostDto::from);
        return PageResponse.of(dtoPage);
    }

    @GetMapping("/posts/{id}")
    public PostDto getPost(@PathVariable Long id) {
        Post p = postRepo.findById(id).orElseThrow();
        return PostDto.from(p);
    }
}


