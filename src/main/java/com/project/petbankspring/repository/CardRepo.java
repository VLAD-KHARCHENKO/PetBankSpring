package com.project.petbankspring.repository;

        import com.project.petbankspring.model.Account;
        import com.project.petbankspring.model.Card;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.PagingAndSortingRepository;

        import java.util.List;

public interface CardRepo extends PagingAndSortingRepository<Card, Long> {

//        @Query("select card from Card card left join card.account account left join card.account.user user where card.account.user.id = :userId")
//        List<Card> findAllByUserId(long userId);

    @Query("select card from Card card where card.account.user.id = :userId and card.account.condition=true and card.condition='ACTIVE'")
    List<Card> findAllByUserId(long userId);

       @Query("select max(number) from Card")
         Long findMaxValueByNumber();

      //  @Query("select card from Card card left join card.account account left join card.account.user user where card.account.user.id = :userId")
        Card findByAccountId(long accountId);

        Card findByNumber(long cardNumber);



}
