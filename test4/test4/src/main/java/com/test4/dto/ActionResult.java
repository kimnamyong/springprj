package com.test4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionResult {
 boolean success;
 String message;

 public ActionResult(boolean success) {
  this.success = success;
 }
}
