package edu.pkch.pkch.testlearn.customization;

import lombok.Getter;
import org.javaunit.autoparams.AutoSource;
import org.javaunit.autoparams.customization.Customization;
import org.javaunit.autoparams.customization.Customizer;
import org.javaunit.autoparams.generator.ObjectContainer;
import org.javaunit.autoparams.generator.ObjectGenerationContext;
import org.javaunit.autoparams.generator.ObjectGenerator;
import org.junit.jupiter.params.ParameterizedTest;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

class CustomizationAutoParamsTest {

    /**
     * 파라미터 대상 클래스와 Customizer 구현체는 public 접근 제어자여야한다.
     */
    @ParameterizedTest
    @AutoSource
    @Customization(PostCustomization.class)
    void postCustomization(Post post) {
        System.out.println(post);
    }

    public static class PostCustomization implements Customizer {
        @Override
        public ObjectGenerator customize(ObjectGenerator generator) {
            return (query, context) -> query.getType().equals(Post.class)
                    ? new ObjectContainer(factory(context))
                    : generator.generate(query, context);
        }

        private Post factory(ObjectGenerationContext context) {
            UUID id = (UUID) context.generate(() -> UUID.class);
            LocalDateTime createdAt = LocalDateTime.now();
            LocalDateTime modified = LocalDateTime.now();
            boolean deleted = false;

            String title = generateRandomString(ThreadLocalRandom.current().nextInt(100));
            String content = generateRandomString(ThreadLocalRandom.current().nextInt(1000));

            return new Post(id, title, content, createdAt, modified, deleted);
        }

        private String generateRandomString(int length) {
            byte[] bytes = new byte[length];
            new Random().nextBytes(bytes);

            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    /**
     * Post의 제목은 100자 이내, content는 1000자 이내
     */
    @Getter
    public static class Post {
        private UUID id;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        private boolean deleted;

        public Post(UUID id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean deleted) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
            this.deleted = deleted;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", createdAt=" + createdAt +
                    ", modifiedAt=" + modifiedAt +
                    ", deleted=" + deleted +
                    '}';
        }
    }
}
