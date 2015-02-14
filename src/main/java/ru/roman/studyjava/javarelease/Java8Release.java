package ru.roman.studyjava.javarelease;




import java.io.Serializable;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Created by Roman on 13.02.2015.
 */
public class Java8Release {
    public static void main(String[] args) {
        new SomeFunc2() {
            @Override
            public void doMore(Object obj) {
            }
        }.doMe();
    }







    public void myMethod1() {

        // Java 8  ../03/2014

        //     - Lambda Expressions
        //     - Default/defender Methods
        //     - Bulk Data Operations API


        // Новые возможности для многпоточных библиотек
        // Серьезные изменения в систаксисе языка, новое API












        List<Object> objList1 = Arrays.asList(new Object(), new Object(), new Object());

        // Стандартный подход
        //  - если мы хотим многопоточности ее туту нет т к используется стандартный цикл for или while
        //  - часто встречающаяся громоздкая конструкция,
        for(Object obj : objList1) {
            doSomething(obj);
        }

        // Новый подход
        //  - можено обработать объекты многоточно при соотв реализации объекта коллекции
        //  - упрощениие конструкции кода, по аналогии как в скриптовых языках Groovy, Ruby
        objList1.forEach( obj -> doSomething(obj) );




        // аналогичная запись
        objList1.forEach(new java.util.function.Consumer<Object>() {
            @Override
            public void accept(Object obj) {
                doSomething(obj);
            }
        });


















        // Использование лямбда выражения
        // по сути мы как бы передаем анонименый класс, но не видим здесь его тип,
        // который определяется типом параметра метода принимающего функциональный интерфейс
        objList1.forEach( (Object obj) -> { doSomething(obj);} );
        objList1.forEach( (obj) -> { doSomething(obj);} );
        objList1.forEach( obj -> doSomething(obj) );
        // использьзование ссылки на метод
        //  - возможно если параметры функционалного интерфейса такие же как у метода и не требуют рпеобразования
        objList1.forEach(System.out::println);

        Comparator<Integer> c2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return (a < b) ? -1 : ((a > b ) ? 1 : 0);
            }
        };
        Comparator<Integer> c1 = (a, b) -> (a < b) ? -1 : ((a > b ) ? 1 : 0);

        Comparator<Integer> c3 = (Integer a, Integer b) -> {
            return (a < b) ? -1 : ((a > b ) ? 1 : 0);
        };





    }

    public void doSomething(Object obj) {
    }







}

// Functional Interface
//  - SAM Types (Single Abstract Method)
//     - Специальная аннотация для SAM, компилятор проверяет что в интерфейсе только один абстрактный метод
//     - множестро интефейсов типа Runnable уже удовлетворяют этому требованию
//  - пакет java.util.function
//     - Consumer
//     - Supplier
//     - Predicate
//     - Function

// Annotation
@FunctionalInterface                // Ошибка компиляции
interface MyFunction1 {
    void doMe();
    void doMore(Object obj);
}

// Default Method
@FunctionalInterface
interface MyFunction2 {
    default void doMe(){}          // Отлично компилируется
    void doMore(Object obj);
}



class MyClass2 {
    void someMethod() {


        // Capturing Lambda
        // захват значения переменной, переменная должны быть теперь effectively final
        // либо объявляем final либо не присваиваем новое значение


        int i1 = 0;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(i1);
            }
        };

        int i2 = 0;
        Runnable r = () -> {
            System.out.println(i2);
        };


        // Return Value
        // ссылка на Functional Interface в выражении return заменяется на Lambda или ссылку на метод

        class RunnablePrepare {


            public Runnable prepareOld() {
                return new Runnable() {
                    @Override
                    public void run() {
                        //
                    }
                };
            }

            public Runnable prepareNew1() {
                return () -> {
                };
            }

            // !!! но сгененрированный Runnable не может быть сериализован, NotSerializableException
            public Runnable prepareNew2() {
                return System.out::println;
            }

            // Type intersection
            // ZAM - Zero abstract Methods
            // SAM & ZAM1 & ZAM2 & ZAM3
            public Runnable prepareNew3() {
                return (Runnable & Serializable & Cloneable) System.out::println;
            }


            // SerializedLambda
            // Если Lambda Serializable она реально может быть сериализована и передана во внешнюю систему
            // т. о. теперь появилась возможность передать логический код во внешнюю систему, включая
            // значения захваченных переменных если они были.


        }
    }


    // компилятор генерирует для Lambda выражений статические методы
    // а не дополнительные класссы как это происходит с анонимными классами
    public static void lambda$0() {

    }

}








// Default Methods
//  - теперь интерфейсы могут эволюционировать по мере обновлений JDK за счет добавления методав
//    с новым модификатором default, методы задают реализацию по умолчанию  не ломают старый код т к
//    не требуют обязатльного реализации точнее переобпределения

@FunctionalInterface
interface MyFunction3 {
    default void doMe(){}

    void doMore(Object obj);
}


// При наследование двух интерфейсов с одинаковыми методами компилятор будет ругаться
interface SomeFunc1 extends MyFunction2, MyFunction3 {
}


// но если переопределить Default метод, проблемы не будет
//   - Можно вызывать методы расширенных интерфейсов
//   - можно обращаться к абстрактным методам
interface SomeFunc2 extends MyFunction2, MyFunction3 {
    default void doMe(){

        MyFunction2.super.doMe();
        MyFunction3.super.doMe();
        doMore(null);
    }
}








// Операции с большими объемами данных (Bulk Data Operations)
// - реализация параллельных операций с элементами коллекциями включая использование
//   многопочных библиотек и классов добавленных в Java 7 java.util.concurrency
// - операции с коллекциями упрощаются за счет появления новых API и Lambda

class Java8ReleaseBulkDataOperation {
    public void myMethod2() {



        // Stream API  java.util.stream
        //    - filter / map / reduce-like
        //    - lazy - infinite or traversed once, не известно кол-во элементов
        //    - immutable - коллекция может и не существовать или используется в другом месте
        java.util.stream.Stream<?> stream = new ArrayList<>().stream();


        // Источники для Stream
        //  - коллекции
        //  - генераторы
        Stream<Object> stream1 = Stream.builder().add(new Object()).add(new Object()).add(new Object()).build();
        Stream<Object> stream2 = Stream.concat(stream1, stream);
        Stream<Object> stream3 = Stream.empty();
        Stream<Object> stream4 = Stream.of(new Object(), new Object(), new Object());
        Stream<Object> stream5 = Stream.iterate(new Object(), o -> new Object());

        IntStream intRange1 = IntStream.generate(() -> 1);
        IntStream intRange2 = IntStream.range(1, 5);
        IntStream intRange3 = IntStream.of(1, 2, 3);

        DoubleStream doubleStream1 = new Random().doubles().limit(1000);




        // Intermediate Operations
        //  - все эти операции "lazy", т. е. выполняются на момент выполнения метода,
        // если в последствии в объктах произойдут изменения, Stream не учтет этого

        // Filter - фильтр
        IntStream intRange4 = IntStream.of(1, 2, 3).filter(new IntPredicate() {
            @Override
            public boolean test(int i) {
                return i > 0;
            }
        });
        IntStream intRange5 = IntStream.of(1, 2, 3).filter((i) -> {return i > 0;});
        IntStream intRange6 = IntStream.of(1, 2, 3).filter(i -> i > 0);

        // Map - трансформация
        IntStream intRange7 = IntStream.of(1, 2, 3).map(i -> ++i);
        IntStream intRange8 = DoubleStream.of(1.0, 2.0, 3.0).mapToInt(d -> (int)d);

        // FlatMap - трансформация каждого элемента в набор элементов
        IntStream intRange81 = IntStream.of(1, 2, 3).flatMap(i -> IntStream.of(i, i, i));

        // Peak - выполнение некого действия на каждом элементе
        IntStream intRange9 = IntStream.of(1, 2, 3).peek(i -> i--);


        // distinct - исключени повторяющихся элементов через .equals(..)
        IntStream intRange10 = IntStream.of(1, 2, 3, 3, 3).distinct();

        // distinct - исключени повторяющихся элементов через .equals(..)
        IntStream intRange11 = IntStream.of(3, 1, 2).sorted();

        // limit - ограничение величены
        IntStream intRange12 = IntStream.of(1, 2, 3, 4, 5).limit(3);

        // пропуск указанного кол-ва элементов
        IntStream intRange13 = IntStream.of(1, 1, 2, 3).skip(1);


        IntStream is1 = IntStream.of(1, 2, 3).parallel();
        IntStream is2 = IntStream.of(1, 2, 3).sequential();
        IntStream is3 = IntStream.of(1, 2, 3).sorted();
        IntStream is4 = IntStream.of(1, 2, 3).unordered();

        IntStream is5 = IntStream.of(1, 2, 3).onClose(() -> {System.out.println("closed");});
        DoubleStream ds1 = IntStream.of(1, 2, 3).asDoubleStream();
        LongStream ls1 = IntStream.of(1, 2, 3).asLongStream();

        // Terminal Operations
        //  - операция выполняется на Stream последней, обычно для его преобразования и закрытия
        // - Stream считается closed после Terminal Operation


        // Reducers
        //
        // reduce - перебирает все значения по парно через указанную функцию,
        //    c некоторым начальынм значением по умолчанию, которое вернется если Stream пуст
        int intVal1 = IntStream.of(1, 2, 3).reduce(0, (i1, i2) -> Math.max(i1, i2));
        int intVal2 = IntStream.of(1, 2, 3).reduce(0, Math::max);
        //    без начального значения
        OptionalInt intVal3 = IntStream.of(1, 2, 3).reduce((i1, i2) -> 0);

        long val1 = IntStream.of(1, 2, 3).count();                  // подсчет
        OptionalInt val2 = IntStream.of(1, 2, 3).findAny();         // берет любой, результат не предсказуем, всегда разный
        OptionalInt val3 = IntStream.of(1, 2, 3).findFirst();       // берет первый
        OptionalInt val4 = IntStream.of(1, 2, 3).max();
        OptionalInt val5 = IntStream.of(1, 2, 3).min();
        OptionalDouble val6 = IntStream.of(1, 2, 3).average();      // среднее
        boolean b1 = IntStream.of(1, 2, 3).anyMatch(i -> true);
        boolean b2 = IntStream.of(1, 2, 3).allMatch(i -> true);
        long val7 = IntStream.of(1, 2, 3).summaryStatistics().getCount();
        double val8 = IntStream.of(1, 2, 3).summaryStatistics().getAverage();
        int val9 = IntStream.of(1, 2, 3).summaryStatistics().getMax();
        int val10 = IntStream.of(1, 2, 3).summaryStatistics().getMin();
        long val11 = IntStream.of(1, 2, 3).summaryStatistics().getSum();




        // Collectors
        List<Integer> list1 = IntStream.of(1, 2, 3).mapToObj(i -> new Integer(i)).collect(Collectors.toList());
        List<Integer> list2 = IntStream.of(1, 2, 3).mapToObj(i -> new Integer(i)).collect(Collectors.toCollection(() -> new ArrayList<>()));
        List<Integer> list3 = IntStream.of(1, 2, 3).mapToObj(i -> new Integer(i)).collect(Collectors.toCollection(ArrayList::new));

        Set<Integer> set1 = IntStream.of(1, 2, 3).mapToObj(i -> new Integer(i)).collect(Collectors.toSet());

        Map<Integer, String> map1 = IntStream.of(1, 2, 3).mapToObj(i -> new Integer(i)).collect(Collectors.toMap(
                i -> i,
                i -> Integer.toString(i),
                (String k1, String k2) -> k1 + k2,
                HashMap::new
        ));



        // forEach

        Set<Integer> set = IntStream.range(1, 50)
                .parallel()
                .filter(i -> i % 2 == 0)
                .limit(20)
                .mapToObj(Integer::new)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);


    }


}









