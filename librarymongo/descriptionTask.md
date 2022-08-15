## Description tasks this module

### Общее

1. Проверить работу с @Transactional
   - 
2. Сделать тесты
   -
   1. Тест на добавление документа с полем DBref которого нет в БД
   +
3. А зачем нам нужны AuthorDao, BookCommentaryDao и GenreDao, если мы храним всё прям в книге? получается дублирование данных
   + Оставил Author и Genre, заменил в Book на DBRef, при запросе Book все равно достается Author целиком
4.

### Author

1. При создании книги сохранить ссылку в Author
   +
2. При удалении Author проверять есть ли ссылки на Book и отменять удаление если есть
   +
3. При обновлении Author проверять список Book если нет то создавать пустой List, что бы не было NullPointer
   +
4. Не работает нормально. Сам автор поменяется. но в книгах останется предыдущее значение.
   + При замене хранения целиком сущности на DBRef изменения в авторе не надо отслеживать в Book

### Genre

1. При создании книги сохранить ссылку в Genre
   +
2. При удалении Genre проверять есть ли ссылку на Book и отменять удаление если есть
   +

### Book

1. При создании книги проверять наличие Author и Genre если нет хотя бы одного то отменять создание
   + Выбрасывается исключение
2. ??? Как обеспечивать сохранность комментариев при обновлении книги???
   + При обновлении книги без инициализации комментариев выдает NullPointerException.
3. При удалении книги удалять комментарии, удалять Book из Author и Genre
   +
4. Скорректировать методы Book по работе с BookCommentary
   -

### Commentary

1. Проверить работу insert и update
   + Добавляется Book в Commentary и Commentary в Book
2. При удалении Commentary удалять из Book
   +
3. Переделать Service без DAO BookCommentary
   + без DAO CommentaryBook не создаются id
      + сделана генерация UUID для id BookCommentary, Сущность BookCommentary не является Mongo Repo, удалены аннотации
   