package com.ecommerce.project.Service;

import com.ecommerce.project.Exceptions.ResourceNotFoundException;
import com.ecommerce.project.Model.Category;
import com.ecommerce.project.Model.Product;
import com.ecommerce.project.Payload.ProductDTO;
import com.ecommerce.project.Payload.ProductResponse;
import com.ecommerce.project.Repositories.CategoryRepository;
import com.ecommerce.project.Repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO,Product.class);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category" , "categoryId" , categoryId));

        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct , ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product , ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContents(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchbyCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category" , "categoryId" , categoryId));

        List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);

        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product , ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContents(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');

        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product , ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContents(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO , Product.class);

        //Getting Existing Product From database
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() ->new ResourceNotFoundException("Product" , "productId" , productId));

        //Update Product Information
        productFromDB.setProductName(product.getProductName());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setQuantity(product.getQuantity());
        productFromDB.setDiscount(product.getDiscount());
        productFromDB.setPrice(product.getPrice());
        //double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
        //productFromDB.setSpecialPrice(specialPrice);
        productFromDB.setSpecialPrice(product.getSpecialPrice());

        //Saving to Database
        Product savedProduct = productRepository.save(productFromDB);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        List<Product> products = productRepository.findAll();
        Product product = products.stream().filter(c -> Objects.equals(c.getProductid(), productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product" , "productId" , productId));

        productRepository.delete(product);
        return  modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        // Get Product from the Database
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product" , "productId" , productId));

        // Upload the Image to the server . Here in a /image file directory
        // Get the filename of the Uploaded Image
        String path = "images/";
        String filename = uploadImage(path , image);


        // Updating the new file name to the product
        productFromDB.setImage(filename);

        // save product
        Product updatedProduct = productRepository.save(productFromDB);

        // return the updated mapped DTO
        return  modelMapper.map(productFromDB, ProductDTO.class);
    }

    private String uploadImage(String path, MultipartFile file) throws IOException {
        // File names of the current/ original File
        String originalFilename = file.getOriginalFilename();

        // Generate a Unique File Name
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;

        // Check if Path exists or not. if NOT Create
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }

        // Upload to the server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Returning File name
        return fileName;
    }
}
