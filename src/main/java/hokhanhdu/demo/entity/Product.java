package hokhanhdu.demo.entity;

import hokhanhdu.demo.Validator.annotation.ValidCategoryId;
import hokhanhdu.demo.Validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name ="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @Column(name =  "image")
    private String image;

    @Column (name = "price")
    @NotNull(message = "Price is required")
    private  Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;
}
