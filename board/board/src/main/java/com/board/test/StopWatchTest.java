package com.board.test;

import org.springframework.util.StopWatch;

public class StopWatchTest {
 public static void main(String[] args) {

  // public void doSomething() {
  StopWatch stopWatch = new StopWatch();
  stopWatch.start();

  for(int i=0;i<1000999000;i++){
   i=i+1;
  }

  stopWatch.stop();
  System.out.println("Execution Time: " + stopWatch.getTotalTimeMillis() + " ms");
 }
}
