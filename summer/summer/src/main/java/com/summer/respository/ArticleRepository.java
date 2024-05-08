package com.summer.respository;

import com.summer.domain.Article;

import com.summer.model.Option;
import com.summer.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

 Option[] searchBy = { new Option(0, "검색 없음"), new Option(1, "글쓴이"),
         new Option(2, "제목"), new Option(3, "본문")  };
 Option[] orderBy = { new Option(0, "최근 글"), new Option(1, "오래된 글"),
         new Option(2, "글쓴이") };

 Sort[] sort = {Sort.by(Sort.Direction.DESC, "id"),
         Sort.by(Sort.Direction.ASC, "id"),
         Sort.by(Sort.Direction.ASC, "name") };

 public default List<Article> findAll(Pagination pagination) {
  Pageable pageable =
          PageRequest.of(pagination.getPg() - 1, pagination.getSz(), sort[pagination.getOd()]);

  Page<Article> page;
  int boardId = pagination.getBd();
  String searchText = pagination.getSt();

  switch (pagination.getOd()) {
   case 0: page = this.findByBoardIdAndUserName(boardId, searchText, pageable); break;
   case 1: page = this.findByBoardIdAndTitleContains(boardId, searchText, pageable); break;
   case 2: page = this.findByBoardIdAndBodyContains(boardId, searchText, pageable); break;
   default: page = this.findByBoardId(boardId, pageable); break;
  }

  pagination.setRecordCount((int)page.getTotalElements());
  return page.getContent();
 }

 public Page<Article> findByBoardId(int boardId, Pageable pageable);
 public Page<Article> findByBoardIdAndUserName(int boardId, String name, Pageable pageable);
 public Page<Article> findByBoardIdAndTitleContains(int boardId, String title, Pageable pageable);
 public Page<Article> findByBoardIdAndBodyContains(int boardId, String body, Pageable pageable);

 @Query(value = "SELECT MAX(a.no) FROM Article a WHERE a.board_id = ?1", nativeQuery = true)
 public int findMaxNo(int boardId);

 @Modifying
 @Query(value="UPDATE Article SET title=:title, body=:body, writeTime=CURRENT_TIMESTAMP() WHERE id=:id", nativeQuery = true)
 public void update(@Param("id") int id, @Param("title") String title, @Param("body") String body);

}
