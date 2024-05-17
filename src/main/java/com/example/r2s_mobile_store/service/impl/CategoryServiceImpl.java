package com.example.r2s_mobile_store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.r2s_mobile_store.dto.CategoryDTO;
import com.example.r2s_mobile_store.entity.Category;
import com.example.r2s_mobile_store.exception.CategoryNotFoundException;
import com.example.r2s_mobile_store.exception.NotFoundException;
import com.example.r2s_mobile_store.mapper.CategoryMapper;
import com.example.r2s_mobile_store.repository.CategoryRepository;
import com.example.r2s_mobile_store.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    // Constructor DI để Inject các dependency
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Lấy danh sách tất cả các danh mục.
     *
     * @return Danh sách CategoryDTO
     */

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Tìm kiếm danh mục theo ID.
     *
     * @param id ID của danh mục cần tìm kiếm
     * @return Đối tượng CategoryDTO tương ứng với ID
     * @throws Exception Nếu không tìm thấy danh mục
     */

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO findById(long id) throws CategoryNotFoundException {
        return categoryMapper.toDTO(categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Danh mục này không tồn tại!")));
    }

    /**
     * Tạo một danh mục mới.
     *
     * @param categoryDTO Đối tượng CategoryDTO
     * @return Đối tượng CategoryDTO sau khi tạo
     */
    @Transactional
    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        category.setId(0);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    /**
     * Cập nhật danh mục.
     *
     * @param id
     * @param categoryDTO Đối tượng CategoryDTO có chứa id và thông tin của danh mục
     *                    cần được cập nhật
     * @return Đối tượng CategoryDTO sau khi đã cập nhật
     */

    @Transactional
    @Override
    public CategoryDTO update(long id, CategoryDTO categoryDTO) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        category.setName(categoryDTO.getName());
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    /**
     * Cập nhật danh mục.
     *
     * @param id
     * 
     */
    @Transactional
    @Override
    public void delete(long id) throws CategoryNotFoundException {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }
}
