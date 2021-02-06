package com.project.petbankspring.repository;

        import com.project.petbankspring.model.Card;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.PagingAndSortingRepository;

        import java.util.List;

public interface CardRepo extends PagingAndSortingRepository<Card, Long> {

        @Query("select card from Card card left join card.account account left join card.account.user user where card.account.user.id = :userId")
        List<Card> findAllByUserId(long userId);

       @Query("select max(number) from Card")
         Long findMaxValueByNumber();
}
