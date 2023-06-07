package hokhanhdu.demo.services;

import hokhanhdu.demo.entity.Product;
import hokhanhdu.demo.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository bookRepository;

    public List<Product> getAllBooks()
    {
        return bookRepository.findAll();
    }
    public Product getBookById(Long id){
        Optional<Product> optional = bookRepository.findById(id);
        return  optional.orElse(null);
    }
    public void addBook(Product book)
    {
        bookRepository.save(book);
    }
    public void updateBook(Product book){
        bookRepository.save(book);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
