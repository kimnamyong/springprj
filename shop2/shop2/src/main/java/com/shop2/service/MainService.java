package com.shop2.service;

import com.shop2.dto.ItemSearchDto;
import com.shop2.dto.MainItemDto;
import com.shop2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {

 @Autowired
 ItemRepository itemRepository;

 @Transactional(readOnly = true)
 public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
  return itemRepository.getMainItemPage(itemSearchDto, pageable);
 }

}
