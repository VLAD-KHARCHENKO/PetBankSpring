package com.project.petbankspring.repository;

        import com.project.petbankspring.model.Card;
        import com.project.petbankspring.model.User;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.repository.CrudRepository;

public interface CardRepo extends CrudRepository<Card, Long> {


}
