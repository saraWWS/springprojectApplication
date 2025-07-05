package com.contactsaver.demo.controller;

import com.contactsaver.demo.entity.Contact;
import com.contactsaver.demo.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/add")
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @GetMapping("/get")
    public List<Contact> getContacts() {
        return contactService.getContacts();
    }

    @PutMapping("/update/{id}")
    public void updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        contactService.updateContact(id, contact);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}
