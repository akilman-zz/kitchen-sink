package stuff;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
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

    final Map<Boolean, List<File>>
        isDirVsFiles =
        Stream.of(initialFiles).collect(partitioningBy(File::isDirectory));

    final Comparator<File> ascendingComparator = (f1, f2) -> f1.compareTo(f2);

    final List<File> ds = isDirVsFiles.get(true);
    ds.sort(ascendingComparator);

    final List<File> fs = isDirVsFiles.get(false);
    fs.sort(ascendingComparator);

    List<File> result = new ArrayList<File>();
    result.addAll(ds);
    result.addAll(fs);

    // todo
//    assertThat(result.indexOf("./classes"), lessThan(result.indexOf("./src")));
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

  private int [] createRandomIntArray(int size) {
    Random generator = new Random(System.currentTimeMillis());
    return IntStream.generate(() -> generator.nextInt()).limit(size).distinct().toArray();
  }
}
