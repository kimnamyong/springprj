package com.jpa4.controller;

import com.jpa4.entity.Sugang;
import com.jpa4.respository.SugangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sugang")
public class SugangController {

 @Autowired
 SugangRepository sugangRepository;

 @Transactional
 @RequestMapping("test1")
 public List<Sugang> test1() {
  sugangRepository.udateGradeByLectureId(3, "C0", "D+");
  return sugangRepository.findByLectureIdOrderByGradeDesc(3);

 }

 @Transactional
 @RequestMapping("test2")
 public List<Sugang> test2() {
  sugangRepository.udateGradeByLectureId(3, "D+", "C0");
  return sugangRepository.findByLectureIdOrderByGradeDesc(3);
 }

}
