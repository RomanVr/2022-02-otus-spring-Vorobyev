package ru.homework.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.library.domain.BookCommentary;

public interface BookCommentaryDao extends JpaRepository<BookCommentary, Long> {
}
