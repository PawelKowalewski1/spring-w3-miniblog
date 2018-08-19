package pl.reaktor.w3.miniblog.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.reaktor.w3.miniblog.entities.Post;
import pl.reaktor.w3.miniblog.repositories.PostRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller


public class PostController {

    private PostRepository postRepository;
@Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.GET)
        public String postAddForm (){
            System.out.println("postAddForm");
            return "post/postAdd";
        }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    public String postAddAction (@RequestParam String title,
                                 @RequestParam String content){

            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setAdded(new Date());

        Post savedPost = postRepository.save(post);


        System.out.println("post saved" +savedPost.getId());
            return "post/postAdded";
    }
   // @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @GetMapping("/posts")
    public String postDisplay (Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("postsModel", posts);
        System.out.println("postDisplay");
        return "post/postDisplay";
    }

    @RequestMapping("/posts/{id}")


        public String postPresentation(Model model,
               @PathVariable Long id) {
            model.addAttribute("postId", id);
        Optional<Post> postById = postRepository.findById(id);

        if (postById.isPresent()){
            model.addAttribute("post", postById.get());
            return "post/postPresentation";
        }



        return"post/postNotFound";

    }
@GetMapping("/search")
public String searchPosts(Model model , @RequestParam String q){

    List<Post> posts = postRepository.findAllByTitleContains(q);
    model.addAttribute("phrase", q);
    model.addAttribute("posts", posts);

    return "post/postWithQ";
}








}




