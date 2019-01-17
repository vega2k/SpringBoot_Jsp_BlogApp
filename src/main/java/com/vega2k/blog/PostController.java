package com.vega2k.blog;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vega2k.blog.domain.model.entity.Post;
import com.vega2k.blog.infrastructure.dao.PostRepository;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostRepository postRepository;
	
//	@RequestMapping("/index")
//    public String redirect() {
//        return "redirect:/post/list";
//    }
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String form(Post post) {
		return "form";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(@Valid Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
	        return "form";
	    }
		post.setRegDate(LocalDateTime.now());
		return "redirect:/post/" + postRepository.save(post).getId();
	}

	@RequestMapping("/list")
	public String list(Model model, 
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 2) Pageable pageable) {
		Page<Post> postPage = postRepository.findAll(pageable);
		model.addAttribute("postPage", postPage);
		return "list";
	}

	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable long id) {
		Post post = postRepository.getOne(id);
		model.addAttribute("post", post);
		return "post";
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable long id) {
		postRepository.deleteById(id);
		return "redirect:/post/list";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable long id) {
		Post post = postRepository.getOne(id);
		model.addAttribute("post", post);
		return "form";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(@Valid Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		return "redirect:/post/" + postRepository.save(post).getId();
	}
}
