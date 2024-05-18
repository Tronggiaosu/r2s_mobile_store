package com.example.r2s_mobile_store.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.r2s_mobile_store.dto.PaginationDTO;
import com.example.r2s_mobile_store.dto.ProductDTO;
import com.example.r2s_mobile_store.dto.ProductUpdateDTO;
import com.example.r2s_mobile_store.dto.ThumbnailDTO;
import com.example.r2s_mobile_store.dto.VariantProductDTO;
import com.example.r2s_mobile_store.entity.Category;
import com.example.r2s_mobile_store.entity.Product;
import com.example.r2s_mobile_store.entity.Thumbnail;
import com.example.r2s_mobile_store.entity.VariantProduct;
import com.example.r2s_mobile_store.exception.CategoryNotFoundException;
import com.example.r2s_mobile_store.exception.NotFoundException;
import com.example.r2s_mobile_store.exception.ProductNotFoundException;
import com.example.r2s_mobile_store.mapper.ProductMapper;
import com.example.r2s_mobile_store.repository.CategoryRepository;
import com.example.r2s_mobile_store.repository.ProductRepository;
import com.example.r2s_mobile_store.repository.ThumbnailRepository;
import com.example.r2s_mobile_store.repository.VariantProductRepository;
import com.example.r2s_mobile_store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VariantProductRepository variantProductRepository;
    private final ThumbnailRepository thumbnailRepository;

    public ProductServiceImpl(ProductMapper productMapper,
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            VariantProductRepository variantProductRepository,
            ThumbnailRepository thumbnailRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.variantProductRepository = variantProductRepository;
        this.thumbnailRepository = thumbnailRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PaginationDTO findAllProductPaginate(int no, int limit) throws ProductNotFoundException {

    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> findAllByCategoryId(Long id) throws CategoryNotFoundException {
        return productRepository.findAllByCategoryId(id).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO findById(long id) throws ProductNotFoundException {
        return productMapper.toDTO(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Sản phẩm này không tồn tại!")));
    }

    @Transactional
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategory())
                .orElseThrow(() -> new NotFoundException(productDTO.getCategory()));
        Product product = productMapper.toEntity(productDTO);
        product.setCategory(category);
        product = productRepository.save(product);

        List<VariantProduct> listVariantProducts = new ArrayList<>();
        for (VariantProduct variantProduct : product.getVariantProducts()) {
            variantProduct.setProduct(product);
            listVariantProducts.add(variantProductRepository.save(variantProduct));
        }
        product.setVariantProducts(listVariantProducts);

        List<Thumbnail> listThumbnail = new ArrayList<>();
        for (Thumbnail thumbnail : product.getThumbnails()) {
            thumbnail.setProduct(product);
            listThumbnail.add(thumbnailRepository.save(thumbnail));
        }
        product.setThumbnails(listThumbnail);

        return productMapper.toDTO(product);
    }

    @Transactional
    @Override
    public ProductUpdateDTO update(long id, ProductUpdateDTO productUpdateDTO) throws ProductNotFoundException {
        Product existedProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        // cập nhật thông tin của product
        Category category = categoryRepository.findById(productUpdateDTO.getCategory())
                .orElseThrow(() -> new NotFoundException(productUpdateDTO.getCategory()));
        existedProduct.setCategory(category);
        existedProduct.setName(productUpdateDTO.getName());
        existedProduct.setDescription(productUpdateDTO.getDescription());
        existedProduct.setPrice(productUpdateDTO.getPrice());
        existedProduct = productRepository.save(existedProduct);

        // Cập nhật hoặc thêm variant product
        List<VariantProductDTO> listVariantProductUpdate = productUpdateDTO.getVariantProductsDTOs();
        List<VariantProduct> updatedVariantProducts = new ArrayList<>();
        for (VariantProductDTO variantProductDTO : listVariantProductUpdate) {
            Long variantProductId = variantProductDTO.getId();
            VariantProduct variantProduct;
            if (variantProductRepository.existsById(variantProductId)) {
                // variant product tồn tại trong db thì cập nhật nó
                variantProduct = variantProductRepository.findById(variantProductId).get();
                variantProduct.setColor(variantProductDTO.getColor());
                variantProduct.setMaterial(variantProductDTO.getMaterial());
                variantProduct.setPrice(variantProductDTO.getPrice());
                variantProduct.setSize(variantProductDTO.getSize());
            } else {
                // nếu variant product đó không có trong db thì thực hiện thêm nó vào db
                variantProduct = new VariantProduct();
                variantProduct.setProduct(existedProduct);
                variantProduct.setColor(variantProductDTO.getColor());
                variantProduct.setMaterial(variantProductDTO.getMaterial());
                variantProduct.setPrice(variantProductDTO.getPrice());
                variantProduct.setSize(variantProductDTO.getSize());
            }
            updatedVariantProducts.add(variantProductRepository.save(variantProduct));
        }
        existedProduct.setVariantProducts(updatedVariantProducts);

        // Cập nhật hoặc thêm ảnh
        List<ThumbnailDTO> listThumbnailUpdate = productUpdateDTO.getThumbnailDTOs();
        List<Thumbnail> updatedThumbnails = new ArrayList<>();
        for (ThumbnailDTO thumbnailDTO : listThumbnailUpdate) {
            Long thumbnailId = thumbnailDTO.getId();
            Thumbnail thumbnail;
            if (thumbnailRepository.existsById(thumbnailId)) {
                // Nếu ảnh tồn tại trong db thì thực hiện cập nhật nó
                thumbnail = thumbnailRepository.findById(thumbnailId).get();
                thumbnail.setUrl(thumbnailDTO.getUrl());
            } else {
                // Nếu không có trong db thì thực hiện thêm nó
                thumbnail = new Thumbnail();
                thumbnail.setProduct(existedProduct);
                thumbnail.setUrl(thumbnailDTO.getUrl());
            }
            updatedThumbnails.add(thumbnailRepository.save(thumbnail));
        }
        existedProduct.setThumbnails(updatedThumbnails);

        return productMapper.toUpdateDTO(existedProduct);
    }

    @Transactional
    @Override
    public String delete(long id) throws ProductNotFoundException {
        Optional<Product> existedProduct = productRepository.findById(id);
        if (existedProduct.isPresent()) {
            productRepository.deleteById(id);
            return "Xoá sản phẩm thành công";
        } else {
            throw new ProductNotFoundException("Sản phẩm này không tồn tại!");
        }
    }
}
