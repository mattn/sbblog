package io.github.mattn.sbblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.mattn.sbblog.domain.EntriesRepository;
import io.github.mattn.sbblog.domain.Entry;
import io.github.mattn.sbblog.form.EntryForm;

@Controller
@EnableAutoConfiguration
public class EntriesController {
	@Autowired
	private EntriesRepository entriesRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(
			@PageableDefault(value = 3, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
			Model model) {
		Page<Entry> entries = entriesRepository.findAll(pageable);
        model.addAttribute("entries", entries);
        model.addAttribute("ph", pageable);
		return "entries";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable int id, Model model) {
		model.addAttribute("entry", entriesRepository.findOne(id));
		return "entry";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editShow(@PathVariable int id, @ModelAttribute("form") EntryForm entryForm, Model model) {
		Entry entry = entriesRepository.findOne(id);
		entryForm.setTitle(entry.getTitle());
		entryForm.setBody(entry.getBody());	
		model.addAttribute("entry", entry);
		model.addAttribute("entryForm", entryForm);
		return "edit";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String editUpdate(@PathVariable int id, @Validated EntryForm entryForm, BindingResult result, Model model) {
		Entry entry = entriesRepository.findOne(id);
		if (!result.hasErrors()) {
			entry.setTitle(entryForm.getTitle());
			entry.setBody(entryForm.getBody());
			entriesRepository.save(entry);
			return "redirect:/";
		}
		model.addAttribute("entryForm", entryForm);
		model.addAttribute("message", "入力エラーがあります:" + result.getFieldErrors().get(0).getDefaultMessage());
		return "edit";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(@Validated EntryForm entryForm, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			entriesRepository.save(new Entry(entryForm.getTitle(), entryForm.getBody()));
			return "redirect:/";
		}
		model.addAttribute("entries", entriesRepository.findAll());
		model.addAttribute("message", "入力エラーがあります:" + result.getFieldErrors().get(0).getDefaultMessage());
		return "edit";
	}
}
