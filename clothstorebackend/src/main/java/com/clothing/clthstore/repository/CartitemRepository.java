package com.clothing.clthstore.repository;

import com.clothing.clthstore.model.Cartitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartitemRepository extends JpaRepository<Cartitem, Long> {
}
