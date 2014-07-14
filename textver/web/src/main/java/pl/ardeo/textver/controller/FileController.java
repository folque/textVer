package pl.ardeo.textver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.ardeo.textver.form.CommentForm;
import pl.ardeo.textver.form.FileForm;
import pl.ardeo.textver.model.Comment;
import pl.ardeo.textver.model.Document;
import pl.ardeo.textver.model.Version;
import pl.ardeo.textver.model.auth.Users;
import pl.ardeo.textver.services.CommentService;
import pl.ardeo.textver.services.FileService;
import pl.ardeo.textver.services.dao.DocumentDAO;
import pl.ardeo.textver.services.dao.UserDAO;
import pl.ardeo.textver.services.dao.VersionDAO;

@Controller
public class FileController {
	
	@Autowired
	DocumentDAO documentDAO;
	
	@Autowired
	VersionDAO versionDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	CommentService commentService;
	
	
	@RequestMapping(value={"/", "/fileList"}, method=RequestMethod.GET)
	public String fileList(Model model){
		List<Document> documents = documentDAO.findAll();
		List<FileForm> forms = fileService.getFileFormsForDocuments(documents);
		if(documents.isEmpty()){
			model.addAttribute("error", "true");
		}
		model.addAttribute("forms", forms);
		return "fileList";
	}
	
	@RequestMapping(value="/createFile", method=RequestMethod.GET)
	public String createfile(Model model){
		FileForm fileForm = new FileForm();
		model.addAttribute("fileForm", fileForm);
		return "createFile";
	}
	
	@RequestMapping(value="/createFile", method=RequestMethod.POST)
	public String submitFile(Model model, @ModelAttribute FileForm fileForm){
		fileService.insert(fileForm);
		return "redirect:/";
	}
	
	@RequestMapping(value="viewFile/{id}/{ver:.*}", method=RequestMethod.GET)
	public String viewFile(Model model, @PathVariable("id") Long id, @PathVariable("ver") String ver){
		FileForm fileForm = fileService.populate(new FileForm(), id, ver);
		Users user = documentDAO.findVersionByName(documentDAO.findById(id), ver).getUser_id();
		List<Version> versions = versionDAO.findAllforDocument(id);
		List<Comment> comments = commentService.getCommentsForVersion(documentDAO.findById(id), ver);
		model.addAttribute("user", user);
		model.addAttribute("comments", comments);
		model.addAttribute("commentForm", new CommentForm());
		model.addAttribute("fileForm", fileForm);
		model.addAttribute("versions", versions);
		return "viewFile";
	}
	
	@RequestMapping(value="viewFile/{id}/{ver:.*}", method=RequestMethod.POST)
	public String saveFile(Model model, @ModelAttribute FileForm fileForm, @ModelAttribute CommentForm commentForm, 
			@PathVariable("id") Long id, @PathVariable("ver") String ver, @RequestParam String action ){
		if(action.equals("Save")){
		FileForm old = fileService.populate(new FileForm(), id, ver);
		if(!old.equals(fileForm)){
			fileService.update(old, fileForm, id);
		}
		}
		else if(action.equals("Comment")){
			commentService.insert(commentForm, documentDAO.findById(id), ver);
		}
		return "redirect:/";
	}
}
