int x = 10;
java.util.function.Function<Integer, Integer> square = n -> n * n;
square.apply(5)
square.apply(x)