package by.itacademy.classifierservice.dao.api;

import by.itacademy.classifierservice.model.entity.CurrencyEntity;
import by.itacademy.classifierservice.model.entity.OperationCategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IOperationCategoryStorage extends JpaRepository<OperationCategoryEntity, UUID> {
    List<OperationCategoryEntity> findAllBy(Pageable pageable);
    boolean existsByTitle(String title);
}
