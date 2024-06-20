package com.example.r2s_mobile_store.mapper;

import com.example.r2s_mobile_store.dto.ProductDTO;
import com.example.r2s_mobile_store.dto.ProductUpdateDTO;
import com.example.r2s_mobile_store.dto.ThumbnailDTO;
import com.example.r2s_mobile_store.dto.VariantProductDTO;
import com.example.r2s_mobile_store.entity.Category;
import com.example.r2s_mobile_store.entity.Product;
import com.example.r2s_mobile_store.entity.Thumbnail;
import com.example.r2s_mobile_store.entity.VariantProduct;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T18:06:32+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private VariantProductMapper variantProductMapper;
    @Autowired
    private ThumbnailMapper thumbnailMapper;

    @Override
    public Product toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( productDTOToCategory( productDTO ) );
        product.setVariantProducts( variantProductDTOListToVariantProductList( productDTO.getVariantProductsDTOs() ) );
        product.setThumbnails( thumbnailDTOListToThumbnailList( productDTO.getThumbnailDTOs() ) );
        product.setDescription( productDTO.getDescription() );
        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setPrice( productDTO.getPrice() );

        return product;
    }

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setCategory( productCategoryId( product ) );
        productDTO.setVariantProductsDTOs( variantProductListToVariantProductDTOList( product.getVariantProducts() ) );
        productDTO.setThumbnailDTOs( thumbnailListToThumbnailDTOList( product.getThumbnails() ) );
        productDTO.setDescription( product.getDescription() );
        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setPrice( product.getPrice() );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductUpdateDTO productUpdateDTO) {
        if ( productUpdateDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( productUpdateDTOToCategory( productUpdateDTO ) );
        product.setVariantProducts( variantProductDTOListToVariantProductList( productUpdateDTO.getVariantProductsDTOs() ) );
        product.setThumbnails( thumbnailDTOListToThumbnailList( productUpdateDTO.getThumbnailDTOs() ) );
        product.setDescription( productUpdateDTO.getDescription() );
        product.setId( productUpdateDTO.getId() );
        product.setName( productUpdateDTO.getName() );
        product.setPrice( productUpdateDTO.getPrice() );

        return product;
    }

    @Override
    public ProductUpdateDTO toUpdateDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO();

        productUpdateDTO.setCategory( productCategoryId( product ) );
        productUpdateDTO.setVariantProductsDTOs( variantProductListToVariantProductDTOList( product.getVariantProducts() ) );
        productUpdateDTO.setThumbnailDTOs( thumbnailListToThumbnailDTOList( product.getThumbnails() ) );
        productUpdateDTO.setDescription( product.getDescription() );
        productUpdateDTO.setId( product.getId() );
        productUpdateDTO.setName( product.getName() );
        productUpdateDTO.setPrice( product.getPrice() );

        return productUpdateDTO;
    }

    protected Category productDTOToCategory(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( productDTO.getCategory() );

        return category;
    }

    protected List<VariantProduct> variantProductDTOListToVariantProductList(List<VariantProductDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<VariantProduct> list1 = new ArrayList<VariantProduct>( list.size() );
        for ( VariantProductDTO variantProductDTO : list ) {
            list1.add( variantProductMapper.toEntity( variantProductDTO ) );
        }

        return list1;
    }

    protected List<Thumbnail> thumbnailDTOListToThumbnailList(List<ThumbnailDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Thumbnail> list1 = new ArrayList<Thumbnail>( list.size() );
        for ( ThumbnailDTO thumbnailDTO : list ) {
            list1.add( thumbnailMapper.toEntity( thumbnailDTO ) );
        }

        return list1;
    }

    private long productCategoryId(Product product) {
        if ( product == null ) {
            return 0L;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return 0L;
        }
        long id = category.getId();
        return id;
    }

    protected List<VariantProductDTO> variantProductListToVariantProductDTOList(List<VariantProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<VariantProductDTO> list1 = new ArrayList<VariantProductDTO>( list.size() );
        for ( VariantProduct variantProduct : list ) {
            list1.add( variantProductMapper.toDTO( variantProduct ) );
        }

        return list1;
    }

    protected List<ThumbnailDTO> thumbnailListToThumbnailDTOList(List<Thumbnail> list) {
        if ( list == null ) {
            return null;
        }

        List<ThumbnailDTO> list1 = new ArrayList<ThumbnailDTO>( list.size() );
        for ( Thumbnail thumbnail : list ) {
            list1.add( thumbnailMapper.toDTO( thumbnail ) );
        }

        return list1;
    }

    protected Category productUpdateDTOToCategory(ProductUpdateDTO productUpdateDTO) {
        if ( productUpdateDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( productUpdateDTO.getCategory() );

        return category;
    }
}
