package com.example.blog_extend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.example.blog_extend.entity.Category;
import com.example.blog_extend.repository.CategoryRepository;
import com.example.blog_extend.repository.PostRepository;
import com.example.blog_extend.entity.Post;

@SpringBootApplication
public class BlogExtendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogExtendApplication.class, args);
	}

    @Bean
    CommandLineRunner seedDefaultCategories(CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                categoryRepository.save(new Category(null, "General"));
                categoryRepository.save(new Category(null, "Technology"));
                categoryRepository.save(new Category(null, "Life"));
            }
        };
    }

    @Bean
    CommandLineRunner seedDemoPosts(CategoryRepository categoryRepository, PostRepository postRepository) {
        return args -> {
            if (postRepository.count() >= 30) return;

            var categories = categoryRepository.findAll();
            if (categories.isEmpty()) return;

            for (int i = 1; i <= 30; i++) {
                Post p = new Post();
                p.setTitle("Demo Post " + i);
                p.setContent("Nội dung demo cho bài viết số " + i + ". Đây là dữ liệu seed để kiểm tra phân trang.");
                p.setCategory(categories.get((i - 1) % categories.size()));
                postRepository.save(p);
            }
        };
    }
}
