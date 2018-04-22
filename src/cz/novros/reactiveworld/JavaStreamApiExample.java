package cz.novros.reactiveworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

public class JavaStreamApiExample {

	public static void main(final String[] args) {
		fromArray();
		fromCollection();
		randomInts();
		pipeline();
		reduction();
	}

	private static void fromArray() {
		System.out.println("JavaStreamApiExample.fromArray");
		final int[] array = new int[]{1, 2, 3, 4, 5};
		Arrays.stream(array)
				.forEach(value -> System.out.println("value = " + value));
	}

	private static void fromCollection() {
		System.out.println("JavaStreamApiExample.fromCollection");
		final Collection<Integer> collection = new ArrayList<>();
		collection.add(12);
		collection.add(23);
		collection.add(3);
		collection.stream()
				.forEach(value -> System.out.println("value = " + value));
	}

	private static void randomInts() {
		System.out.println("JavaStreamApiExample.randomInts");
		new Random().ints()
				.limit(5) // Must be limited, or it will be infinite
				.forEach(value -> System.out.println("value = " + value));
	}

	private static void pipeline() {
		System.out.println("JavaStreamApiExample.pipeline");
		IntStream.range(0, 5)
				.map(operand -> operand + 3)
				.filter(value -> value % 2 == 0)
				.limit(2)
				.forEachOrdered(value -> System.out.println("value = " + value));
	}

	private static void reduction() {
		System.out.println("JavaStreamApiExample.reduction");
		final int value =
				IntStream.range(0, 100)
						.sum();
		System.out.println("value = " + value);
	}
}
