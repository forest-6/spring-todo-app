package com.example.todo.service;

import com.example.todo.dto.common.PagingResult;
import com.example.todo.domain.PostEntity;
import com.example.todo.dto.post.PostCreateRequest;
import com.example.todo.dto.post.PostResponse;
import com.example.todo.dto.post.PostUpdateRequest;
import com.example.todo.exception.ClientErrorException;
import com.example.todo.exception.post.PostNotFoundException;
import com.example.todo.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository repository) {
        this.postRepository = repository;
    }

    public void createPost(PostCreateRequest request) {
        postRepository.save(request);
    }

    public PagingResult<PostResponse> getPostPage(int pageSize, int pageIndex) {
        List<PostEntity> posts = postRepository.findAllPaged(pageSize, pageIndex);
        int totalCount = postRepository.getTotalCount();
        var rows = posts.stream().map(post -> PostResponse.from(post)).toList();

        return PagingResult.of(totalCount, pageSize, pageIndex, rows);
    }

    public PostResponse getPost(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));

        return PostResponse.from(post);
    }

    public void updatePost(PostUpdateRequest request, Long userId) {
        var postDetail = getPost(request.id());

        if (!postDetail.creatorId().equals(userId)) {
            throw new ClientErrorException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }

        postRepository.update(request);
    }

    public void deletePost(Long id, Long userId) {
        var postDetail = getPost(id);

        if (!postDetail.creatorId().equals(userId)) {
            throw new ClientErrorException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }

        postRepository.delete(id);
    }
}