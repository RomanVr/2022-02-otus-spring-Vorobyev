package ru.homework.librarymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.librarymvc.domain.BookCommentary;

public interface BookCommentaryRepository extends JpaRepository<BookCommentary, Long> {
}
