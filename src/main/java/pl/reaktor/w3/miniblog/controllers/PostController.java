package pl.reaktor.w3.miniblog.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.reaktor.w3.miniblog.entities.Comment;
import pl.reaktor.w3.miniblog.entities.Post;
import pl.reaktor.w3.miniblog.repositories.CommentRepository;
import pl.reaktor.w3.miniblog.repositories.PostRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller


public class PostController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
@Autowired
    public PostController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository=commentRepository;
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
//            List<Comment> comments = commentRepository.findAll();
//            List<Comment> goodComments = new ArrayList<>();
//            for (Comment comment:comments)
//                if(comment.getPost().getId().equals(id)){
//                    goodComments.add(comment);
//            }
//
//            model.addAttribute("comments", goodComments);


            List<Comment> goodComments = commentRepository.findAll().stream()
                    .filter(c -> c.getPost().getId().equals(id))
                    .collect(Collectors.toList());

            return "post/postPresentation";
        }
        return"post/postNotFound";

    }
@GetMapping("/search")
public String searchPosts(Model model , @RequestParam String q,
@RequestParam String searchBy){

    List<Post> posts ;

    if ("title".equals(searchBy)){
        posts=postRepository.findAllByTitleContains(q);
    } else if ("titleOrContent".equals(searchBy)){
        posts = postRepository.findAllByTitleContainsOrContentContains(q,q);
    }else if ("titleAndContent".equals(searchBy))
        posts=postRepository.findAllByTitleContainsAndContentContainsOrderByTitleDesc(q,q);
    else {
        posts = new ArrayList<>();
    }


    model.addAttribute("phrase", q);
    model.addAttribute("posts", posts);

    return "post/postWithQ";
}


@PostMapping("/post/{postId}/comment/add")
    public String addCommentAction(@RequestParam String commentBody,
                                   @PathVariable Long postId,
                                    @RequestParam (required = false) Long postHiddenId){
    Optional<Post> postOptional = postRepository.findById(postId);

    if(!postOptional.isPresent()){
        return "redirect:/posts";
    }

    Comment comment = new Comment();
    comment.setCommentBody(commentBody);
    comment.setAdded(new Date());
    comment.setPost(postOptional.get());


    commentRepository.save(comment);

    return "redirect:/posts/"+postId;
}





}




