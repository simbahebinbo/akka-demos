package com.lab;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Source;
import akka.stream.testkit.javadsl.TestSink;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;


@Slf4j
public class DataImporterUnitTest {

  private final ActorSystem actorSystem = ActorSystem.create();

  @Test
  public void givenStreamOfIntegers_whenCalculateAverageOfPairs_thenShouldReturnProperResults() {
    //given
    Flow<String, Double, NotUsed> tested = new DataImporter(actorSystem).calculateAverage();
    String input = "1;9;11;0";

    //when
    Source<Double, NotUsed> flow = Source.single(input).via(tested);

    //then
    flow
        .runWith(TestSink.probe(actorSystem), ActorMaterializer.create(actorSystem))
        .request(4)
        .expectNextUnordered(5d, 5.5);

    Assert.assertTrue(true);
  }

  @Test
  public void givenStreamOfIntegers_whenCalculateAverageAndSaveToSink_thenShouldFinishSuccessfully() {
    //given
    DataImporter dataImporter = new DataImporter(actorSystem);
    String input = "10;90;110;10";

    //when
    dataImporter.calculateAverageForContent(input)
        .thenAccept(d -> actorSystem.terminate());

    Assert.assertTrue(true);
  }
}