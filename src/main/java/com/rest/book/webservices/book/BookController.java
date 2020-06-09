package com.rest.book.webservices.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository repository;

    @GetMapping()
    public List<Book> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id){
        return repository.findById(id).get();
    }

    @PostMapping("/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping()
    public Book create(@RequestBody Book book){
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        Book bookToUpdate = repository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        repository.deleteById(id);
        return true;
    }

}
