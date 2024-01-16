package madcamp.week3.service;

import madcamp.week3.model.Post;
import madcamp.week3.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> searchPostByKeyword(String searchString){
        return postRepository.searchByKeyword(searchString);
    }
}
