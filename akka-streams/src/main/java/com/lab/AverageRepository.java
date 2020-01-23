package com.lab;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AverageRepository {

  CompletionStage<Double> save(Double average) {
    return CompletableFuture.supplyAsync(() -> {
      log.info("saving average: " + average);
      return average;
    });
  }
}
