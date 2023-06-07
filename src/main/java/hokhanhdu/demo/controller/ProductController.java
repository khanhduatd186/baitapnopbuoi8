package hokhanhdu.demo.controller;

import hokhanhdu.demo.entity.Product;
import hokhanhdu.demo.services.ProductService;
import hokhanhdu.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class ProductController {

    @Autowired
    private ProductService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listBook(Model model) {
        List<Product> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("title", "Book List");
        return "book/list";
    }
    @GetMapping("/add")
        public String addBookForm(Model model){
        model.addAttribute("book", new Product());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
        }
      @PostMapping("/add")
      public String addBook(@Valid  @ModelAttribute("book") Product book, BindingResult result , Model model){
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
        }


    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model){
        Product editBook = bookService.getBookById(id);
        if(editBook != null){
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else {
            return "not-found";
        }
    }
        @PostMapping("/edit")
        public String editBook(@Valid @ModelAttribute("book") Product updateBook, BindingResult result, Model model){
            if(result.hasErrors()){
                model.addAttribute("categories",categoryService.getAllCategories());
                return "book/edit";
            }
        bookService.getAllBooks().stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> {bookService.updateBook(updateBook);});
            return "redirect:/books";
        }

        @GetMapping("/delete/{id}")
        public String deleteBook(@PathVariable("id") Long id){
            bookService.deleteBook(id);
            return "redirect:/books";
        }

}
