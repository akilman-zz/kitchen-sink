package stuff;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * Random code snippets to play with new constructs
 */
public class Chapter1Exercises {

  @Test
  public void testStreamFilter() throws Exception {
    Stream<Person> ps = Stream.of(new Person("foo"), new Person("bar"), new Person("baz"));

    Person[] people = ps.filter(p -> p.name.startsWith("b")).toArray(Person[]::new);
    assertNotNull(people);
    assertEquals(people.length, 2);
    assertEquals(people[0].name, "bar");
    assertEquals(people[1].name, "baz");
  }

  class Person {
    String name;

    Person(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  /**
   * Exercise 2 - part 1
   * @throws Exception
   */
  @Test
  public void testFileFilterOldWay() throws Exception {
    FileFilter filter = new FileFilter() {
      @Override
      public boolean accept(File f) {
        return f.isDirectory();
      }
    };

    File[] fs = new File(".").listFiles(filter);
    assertThat(fs.length, greaterThan(0));
  }

  /**
   * Exercise 2 - part 2
   * @throws Exception
   */
  @Test
  public void testFileFilterNewWay() throws Exception {
    File[] fs = new File(".").listFiles(f -> f.isDirectory());
    assertThat(fs.length, greaterThan(0));
  }

  /**
   * Exercise 3
   * @throws Exception
   */
  @Test
  public void testFilenameFilter() throws Exception {
    File[] fs = new File(".").listFiles((dir, name) -> name.endsWith(".gradle"));
    assertThat(fs.length, greaterThan(0));
  }

  /**
   * Exercise 4
   * @throws Exception
   */
  @Test
  public void testFileSort() throws Exception {
    File[] initialFiles = new File(".").listFiles();

    // create a map of 'isDirectory' to list of files via partition
    final Map<Boolean, List<File>>
        isDirVsFiles =
        Stream.of(initialFiles).collect(partitioningBy(File::isDirectory));

    // sort in ascending order
    final Comparator<File> ascendingComparator = (f1, f2) -> f1.compareTo(f2);

    final List<File> ds = isDirVsFiles.get(true);
    ds.sort(ascendingComparator);

    final List<File> fs = isDirVsFiles.get(false);
    fs.sort(ascendingComparator);

    // combine
    List<File> result = new ArrayList<File>();
    result.addAll(ds);
    result.addAll(fs);

    // to assert, transform into a list of file names
    List<String> combine = result.stream().map(f -> f.getName()).collect(toList());

    assertThat(combine.indexOf("classes"), lessThan(combine.indexOf("src")));
  }

  /**
   * Exercise 5
   * @throws Exception
   */
  @Test
  public void testRunnableWithLambda() throws Exception {
    ExecutorService pool = Executors.newFixedThreadPool(1);
    Future<Integer> future = pool.submit(() -> 1 + 1);
    int result = future.get();
    assertEquals(result, 2);
  }

  /**
   * Exercise 6
   * @return
   */
  @Test
  public void testUncheckedRunnable() throws Exception {
    // create a runnable which always throws an exception, terminating the thread
    Runnable r =
        // uncheck will intercept the exception
        uncheck(() -> {
          throw new Exception();
        });

    Thread t = new Thread(r);

    // configure a handler to determine if the exception actually did terminate the thread
    AtomicBoolean errorOccurred = new AtomicBoolean(false);
    Thread.UncaughtExceptionHandler exceptionHandler =
        (thread, throwable) -> errorOccurred.set(true);
    t.setUncaughtExceptionHandler(exceptionHandler);

    // execute, block until complete
    t.start();
    t.join();

    // ensure no error occurred
    assertFalse(errorOccurred.get());
  }

  interface RunnableEx {
    void run() throws Exception;
  }

  static Runnable uncheck(RunnableEx runner) {
    return () -> {
      try {
        runner.run();
      }
      catch (Exception e)
      {
        // intentionally swallow
      }
    };
  }

  /**
   * Exercise 7
   * @throws Exception
   */
  @Test
  public void testAndThen() throws Exception {
    Runnable first = () -> System.out.println("first runnable");
    Runnable second = () -> System.out.println("second runnable");
    Runnable target = andThen(first, second);

    Thread t = new Thread(target);
    t.start();
    try {
      t.join();
    } catch (InterruptedException e) {
      fail("Something barfed", e);
    }
  }

  static Runnable andThen(Runnable first, Runnable second) {
    return () -> {
      first.run();
      second.run();
    };
  }

  /**
   * Exercise 8
   * @throws Exception
   */
  @Test
  public void testLambdaScoping() throws Exception {
    String[] ns = { "Peter", "Paul", "Mary" };
    List<Callable<String>> cs = new ArrayList<>();
    for (String n : ns) {
      cs.add(() -> n);
    }

    List<String> ss = new ArrayList<>();
    for (Callable<String> c : cs) {
      ss.add(c.call());
    }

    assertEquals(ss.size(), 3);
    assertTrue(ss.contains("Peter"));
    assertTrue(ss.contains("Paul"));
    assertTrue(ss.contains("Mary"));
  }
}
