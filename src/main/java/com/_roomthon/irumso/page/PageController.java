package com._roomthon.irumso.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // templates/login.html
    }

    // 루트 경로 요청 시 메인 페이지로 리디렉션
    @GetMapping("/")
    public String redirectToMain() {
        return "redirect:/board/main";
    }

    // 메인 페이지
    @GetMapping("/board/main")
    public String mainPage(Model model) {
        // 로그인 여부 및 사용자 이름은 실제 환경에서 SecurityContext에서 가져와야 함
        boolean isAuthenticated = false; // 임시 설정
        String username = "사용자";

        model.addAttribute("authenticated", isAuthenticated);
        model.addAttribute("username", username);
        return "board/main"; // templates/board/main.html
    }

    // 게시글 목록 페이지
    @GetMapping("/board/posts")
    public String postsPage(Model model) {
        // 게시글 데이터를 서비스에서 가져와야 함 (현재는 템플릿 테스트용으로 비움)
        // model.addAttribute("posts", postService.getAllPosts());
        return "board/board-posts"; // templates/board/board-posts.html
    }

    // 게시글 작성 페이지
    @GetMapping("/board/posts/new")
    public String newPostPage() {
        return "board/board-post-new"; // templates/board/board-post-new.html
    }

    // 게시글 상세보기 페이지
    @GetMapping("/board/posts/{postId}")
    public String postDetailPage(@PathVariable Long postId, Model model) {
        // 특정 게시글 데이터를 서비스에서 가져와야 함
        // model.addAttribute("post", postService.getPostById(postId));
        return "board/board-post-detail"; // templates/board/board-post-detail.html
    }

    // 게시글 수정 페이지
    @GetMapping("/board/posts/edit/{postId}")
    public String editPostPage(@PathVariable Long postId, Model model) {
        // 수정할 게시글 데이터를 서비스에서 가져와야 함
        // model.addAttribute("post", postService.getPostById(postId));
        return "board/board-post-edit"; // templates/board/board-post-edit.html
    }

    // 댓글 관리 페이지
    @GetMapping("/board/comments")
    public String commentsPage(Model model) {
        // 댓글 데이터를 서비스에서 가져와야 함
        // model.addAttribute("comments", commentService.getAllComments());
        return "board/board-comments"; // templates/board/board-comments.html
    }

    // 카테고리 관리 페이지
    @GetMapping("/board/categories")
    public String categoriesPage(Model model) {
        // 카테고리 데이터를 서비스에서 가져와야 함
        // model.addAttribute("categories", categoryService.getAllCategories());
        return "board/board-categories"; // templates/board/board-categories.html
    }
}
