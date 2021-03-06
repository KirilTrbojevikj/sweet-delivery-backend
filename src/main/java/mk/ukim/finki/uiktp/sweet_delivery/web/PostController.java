package mk.ukim.finki.uiktp.sweet_delivery.web;


import mk.ukim.finki.uiktp.sweet_delivery.model.metamodel.Post;
import mk.ukim.finki.uiktp.sweet_delivery.model.metamodel.dto.PostDTO;
import mk.ukim.finki.uiktp.sweet_delivery.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/post")
@CrossOrigin(origins = "https://sweetdelivery.herokuapp.com/")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody PostDTO postDTO){
        return this.postService.createPost(postDTO);
    }

    @DeleteMapping("delete/{postId}")
    public void deletePost(@PathVariable Long postId){
        this.postService.deletePost(postId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/approve/{postId}")
    public void approvePost(@PathVariable Long postId) {
        this.postService.approvePost(postId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/decline/{postId}")
    public void declinePost(@PathVariable Long postId) {
        this.postService.declinePost(postId);
    }
}
