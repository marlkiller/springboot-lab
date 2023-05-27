package com.example.springbootlab.portal.resp;

import com.example.springbootlab.portal.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;
interface UserRepository extends JpaRepository<User, Long> {

  long countByName(String lastname);

  /**
   * You're trying to execute a streaming query method without a surrounding transaction that keeps the connection open so that the Stream can actually be consumed; 
   * Make sure the code consuming the stream uses @Transactional or any other way of declaring a (read-only) transaction
   */
  @Query("select u from User u")
  Stream<User> streamAllPaged(Pageable pageable);
}